package com.pj.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;

import com.pj.core.controller.BaseController;
import com.pj.core.domain.R;
import com.pj.core.page.TableDataInfo;
import com.pj.domain.GenTable;
import com.pj.domain.GenTableColumn;
import com.pj.service.IGenTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 *
 *   
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/tool/gen")
public class GenController extends BaseController {

    private final IGenTableService genTableService;


    /**
     * 修改代码生成业务
     *
     * @param tableId 表ID
     */
    @SaCheckPermission("tool:gen:query")
    @GetMapping(value = "/{tableId}")
    public R<Map<String, Object>> getInfo(@PathVariable Long tableId) {
        GenTable table = genTableService.selectGenTableById(tableId);
        List<GenTable> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list = genTableService.selectGenTableColumnListByTableId(tableId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return R.ok(map);
    }


    /**
     * 查询数据表字段列表
     *
     * @param tableId 表ID
     */
    @SaCheckPermission("tool:gen:list")
    @GetMapping(value = "/column/{tableId}")
    public TableDataInfo<GenTableColumn> columnList(@PathVariable("tableId") Long tableId) {
        TableDataInfo<GenTableColumn> dataInfo = new TableDataInfo<>();
        List<GenTableColumn> list = genTableService.selectGenTableColumnListByTableId(tableId);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
    }

    /**
     * 导入表结构（保存）
     *
     * @param tables 表名串
     */
    @SaCheckPermission("tool:gen:import")
//    @Log(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    public R<Void> importTableSave(String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return R.ok();
    }
//
//    /**
//     * 修改保存代码生成业务
//     */
//    @SaCheckPermission("tool:gen:edit")
//    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public R<Void> editSave(@Validated @RequestBody GenTable genTable) {
//        genTableService.validateEdit(genTable);
//        genTableService.updateGenTable(genTable);
//        return R.ok();
//    }

//    /**
//     * 删除代码生成
//     *
//     * @param tableIds 表ID串
//     */
//    @SaCheckPermission("tool:gen:remove")
//    @Log(title = "代码生成", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{tableIds}")
//    public R<Void> remove(@PathVariable Long[] tableIds) {
//        genTableService.deleteGenTableByIds(tableIds);
//        return R.ok();
//    }

    /**
     * 预览代码
     *
     * @param tableId 表ID
     */
    @SaCheckPermission("tool:gen:preview")
    @GetMapping("/preview/{tableId}")
    public R<Map<String, String>> preview(@PathVariable("tableId") Long tableId) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return R.ok(dataMap);
    }

    /**
     * 生成代码（下载方式）
     *
     * @param tableName 表名
     */
    @SaCheckPermission("tool:gen:code")
//    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     *
     * @param tableName 表名
     */
    @SaCheckPermission("tool:gen:code")
//    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    public R<Void> genCode(@PathVariable("tableName") String tableName) {
        genTableService.generatorCode(tableName);
        return R.ok();
    }


    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IoUtil.write(response.getOutputStream(), false, data);
    }
}
