package com.pj.system.service;

import com.pj.core.domain.PageQuery;
import com.pj.core.page.TableDataInfo;
import com.pj.system.domain.bo.TGlobalAppAkskBo;
import com.pj.system.domain.vo.TGlobalAppAkskVo;

import java.util.Collection;
import java.util.List;

/**
 * 全局应用AKSKService接口
 *
 * @author linger
 * @date 2024-03-12
 */
public interface ITGlobalAppAkskService {

    /**
     * 查询全局应用AKSK
     */
    TGlobalAppAkskVo queryById(Long id);

    /**
     * 查询全局应用AKSK列表
     */
    TableDataInfo<TGlobalAppAkskVo> queryPageList(TGlobalAppAkskBo bo, PageQuery pageQuery);

    /**
     * 查询全局应用AKSK列表
     */
    List<TGlobalAppAkskVo> queryList(TGlobalAppAkskBo bo);

    /**
     * 新增全局应用AKSK
     */
    Boolean insertByBo(TGlobalAppAkskBo bo);

    /**
     * 修改全局应用AKSK
     */
    Boolean updateByBo(TGlobalAppAkskBo bo);

    /**
     * 校验并批量删除全局应用AKSK信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
