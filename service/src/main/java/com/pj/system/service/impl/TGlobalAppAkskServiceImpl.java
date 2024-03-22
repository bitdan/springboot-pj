package com.pj.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pj.core.domain.PageQuery;
import com.pj.core.page.TableDataInfo;
import com.pj.system.domain.TGlobalAppAksk;
import com.pj.system.domain.bo.TGlobalAppAkskBo;
import com.pj.system.domain.vo.TGlobalAppAkskVo;
import com.pj.system.mapper.TGlobalAppAkskMapper;
import com.pj.system.service.ITGlobalAppAkskService;
import com.pj.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 全局应用AKSKService业务层处理
 *
 * @author linger
 * @date 2024-03-12
 */
@RequiredArgsConstructor
@Service
public class TGlobalAppAkskServiceImpl implements ITGlobalAppAkskService {

    private final TGlobalAppAkskMapper baseMapper;

    /**
     * 查询全局应用AKSK
     */
    @Override
    public TGlobalAppAkskVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询全局应用AKSK列表
     */
    @Override
    public TableDataInfo<TGlobalAppAkskVo> queryPageList(TGlobalAppAkskBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TGlobalAppAksk> lqw = buildQueryWrapper(bo);
        Page<TGlobalAppAkskVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询全局应用AKSK列表
     */
    @Override
    public List<TGlobalAppAkskVo> queryList(TGlobalAppAkskBo bo) {
        LambdaQueryWrapper<TGlobalAppAksk> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TGlobalAppAksk> buildQueryWrapper(TGlobalAppAkskBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlobalAppAksk> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppCode()), TGlobalAppAksk::getAppCode, bo.getAppCode());
        lqw.eq(StringUtils.isNotBlank(bo.getAk()), TGlobalAppAksk::getAk, bo.getAk());
        lqw.eq(StringUtils.isNotBlank(bo.getSk()), TGlobalAppAksk::getSk, bo.getSk());
        lqw.eq(StringUtils.isNotBlank(bo.getSkMac()), TGlobalAppAksk::getSkMac, bo.getSkMac());
        lqw.eq(bo.getStatus() != null, TGlobalAppAksk::getStatus, bo.getStatus());
        lqw.eq(bo.getCreateDate() != null, TGlobalAppAksk::getCreateDate, bo.getCreateDate());
        lqw.eq(bo.getExpiryDate() != null, TGlobalAppAksk::getExpiryDate, bo.getExpiryDate());
        lqw.eq(StringUtils.isNotBlank(bo.getExtend1()), TGlobalAppAksk::getExtend1, bo.getExtend1());
        lqw.eq(StringUtils.isNotBlank(bo.getExtend2()), TGlobalAppAksk::getExtend2, bo.getExtend2());
        lqw.eq(StringUtils.isNotBlank(bo.getExtend3()), TGlobalAppAksk::getExtend3, bo.getExtend3());
        return lqw;
    }

    /**
     * 新增全局应用AKSK
     */
    @Override
    public Boolean insertByBo(TGlobalAppAkskBo bo) {
        TGlobalAppAksk add = BeanUtil.toBean(bo, TGlobalAppAksk.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改全局应用AKSK
     */
    @Override
    public Boolean updateByBo(TGlobalAppAkskBo bo) {
        TGlobalAppAksk update = BeanUtil.toBean(bo, TGlobalAppAksk.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TGlobalAppAksk entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除全局应用AKSK
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
