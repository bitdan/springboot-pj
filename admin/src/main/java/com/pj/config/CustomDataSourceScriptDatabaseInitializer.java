package com.pj.config;


import com.pj.common.DistributeProcessorLockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.config.SortedResourcesFactoryBean;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 描述：自定义数据库初始化行为
 * <p>
 * 初始化数据依据t_db_version的版本决定是否执行增量脚本，增量脚本目录存储位置(resources/db)
 * <p>
 *
 * @version 1.0
 * @create 2022/6/29 18:49
 */
@Component
@Slf4j
public class CustomDataSourceScriptDatabaseInitializer extends SqlDataSourceScriptDatabaseInitializer {
    private final ApplicationContext applicationContext;
    private DistributeProcessorLockService distributeProcessorLockService;

    private final String lockName = "DATA_SOURCE_INIT_LOCK";

    public CustomDataSourceScriptDatabaseInitializer(ApplicationContext applicationContext, DataSource dataSource, SqlInitializationProperties properties, DistributeProcessorLockService distributeProcessorLockService) {
        super(dataSource, properties);
        this.applicationContext = applicationContext;
        this.distributeProcessorLockService = distributeProcessorLockService;
    }

    @Override
    public boolean initializeDatabase() {
        boolean result = super.initializeDatabase();

        try {
            while (true) {
                if (distributeProcessorLockService.lock(lockName)) {
                    customInitializeDatabase(this.getDataSource());
                    break;
                }
            }
        } catch (IllegalStateException ex) {
            log.warn("Failed to execute custom initialize database.", ex);
        } finally {
            log.info("****************************************");
            try {
                distributeProcessorLockService.unlock(lockName);
            } catch (Exception e) {
                log.warn("DistributeProcessorLockService unlock failed.", e);
            }
        }
        return result;
    }


    public void customInitializeDatabase(DataSource dataSource) {

        //获取数据增量脚本
        Resource[] resources = doGetResources();
        List<Resource> resourceList = Arrays.asList(resources);
        resourceList.sort(Comparator.comparing(Resource::getFilename));

        //根据当前DB版本，自动增量更新
        int currentDbVersion = getCurrentDbVersion(dataSource);
        for (Resource resource : resourceList) {
            if (StringUtils.isNotEmpty(resource.getFilename())) {
                //初始化脚本,
                if (resource.getFilename().contains("init_db_version")) {
                    //过滤初始化init_db_version.sql脚本
                    continue;
                }
                int resourceVersion = Integer.parseInt(resource.getFilename().replace(".sql", ""));
                if (resourceVersion > currentDbVersion) {
                    runScripts(resource, dataSource, resourceVersion);
                }
            }
        }
    }

    private void runScripts(Resource resource, DataSource dataSource, int version) {

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setContinueOnError(false);
        populator.setSeparator(";");
        populator.addScript(resource);

        String sql = "update t_db_version set value=" + version + ";";
        ByteArrayResource updateVersionResource = new ByteArrayResource(sql.getBytes(StandardCharsets.UTF_8));
        populator.addScript(updateVersionResource);

        try {
            DatabasePopulatorUtils.execute(populator, dataSource);
        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
            log.error("Run sql scripts, scripts {} ......", resource.getFilename());
        }

        log.info("Run sql scripts, scripts {} ......", resource.getFilename());
    }


    private int getCurrentDbVersion(DataSource dataSource) {
        try {
            Connection connection = DataSourceUtils.getConnection(dataSource);
            Statement stmt = null;
            ResultSet rs = null;
            try {
                String sql = "select value from t_db_version where name='version'";
                stmt = connection.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                return rs.getInt("value");
            } finally {
                close(stmt, rs);
                DataSourceUtils.releaseConnection(connection, dataSource);
            }

        } catch (ScriptException var8) {
            throw var8;
        } catch (Throwable var9) {
            throw new UncategorizedScriptException("Failed to execute database script", var9);
        }
    }

    private Resource[] doGetResources() {
        String dbSqlPath = "classpath:db/*.sql";
        try {
            SortedResourcesFactoryBean factory = new SortedResourcesFactoryBean(applicationContext,
                    Collections.singletonList(dbSqlPath));
            factory.afterPropertiesSet();
            return factory.getObject();
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to load resources from " + dbSqlPath, ex);
        }
    }

    private void close(Statement stat, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            log.warn("Failed to release sql resultSet.", e);
        }
        try {
            if (stat != null) {
                stat.close();
            }
        } catch (SQLException e) {
            log.warn("Failed to release sql statement.", e);
        }
    }
}
