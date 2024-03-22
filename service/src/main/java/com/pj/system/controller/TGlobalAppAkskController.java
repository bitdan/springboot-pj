package com.pj.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.pj.annotation.RepeatSubmit;
import com.pj.core.controller.BaseController;
import com.pj.core.domain.PageQuery;
import com.pj.core.domain.R;
import com.pj.core.page.TableDataInfo;
import com.pj.core.validate.AddGroup;
import com.pj.core.validate.EditGroup;
import com.pj.system.domain.bo.TGlobalAppAkskBo;
import com.pj.system.domain.vo.TGlobalAppAkskVo;
import com.pj.system.service.ITGlobalAppAkskService;
import com.pj.utils.ExcelUtil;
import com.pj.utils.LoginHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;


/**
 * 全局应用AKSK
 *
 * @author linger
 * @date 2024-03-12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/globalAppAksk")
public class TGlobalAppAkskController extends BaseController {

    private final ITGlobalAppAkskService iTGlobalAppAkskService;

    /**
     * 查询全局应用AKSK列表
     */
//@SaCheckPermission("system:globalAppAksk:list")
    @GetMapping("/list")
    public TableDataInfo<TGlobalAppAkskVo> list(TGlobalAppAkskBo bo, PageQuery pageQuery) {
        return iTGlobalAppAkskService.queryPageList(bo, pageQuery);
    }

    @GetMapping("/getUsername")
    public String getUsername() {
        return LoginHelper.getUsername();
    }


    /**
     * 导出全局应用AKSK列表
     */
    @SaCheckPermission("system:globalAppAksk:export")
//    @Log(title = "全局应用AKSK", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TGlobalAppAkskBo bo, HttpServletResponse response) {
        List<TGlobalAppAkskVo> list = iTGlobalAppAkskService.queryList(bo);
        ExcelUtil.exportExcel(list, "全局应用AKSK", TGlobalAppAkskVo.class, response);
    }

    /**
     * 获取全局应用AKSK详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:globalAppAksk:query")
    @GetMapping("/{id}")
    public R<TGlobalAppAkskVo> getInfo(@NotNull(message = "主键不能为空")
                                       @PathVariable Long id) {
        return R.ok(iTGlobalAppAkskService.queryById(id));
    }

    /**
     * 新增全局应用AKSK
     */
    @SaCheckPermission("system:globalAppAksk:add")
//    @Log(title = "全局应用AKSK", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TGlobalAppAkskBo bo) {
        return toAjax(iTGlobalAppAkskService.insertByBo(bo));
    }

    /**
     * 修改全局应用AKSK
     */
    @SaCheckPermission("system:globalAppAksk:edit")
//    @Log(title = "全局应用AKSK", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TGlobalAppAkskBo bo) {
        return toAjax(iTGlobalAppAkskService.updateByBo(bo));
    }

    /**
     * 删除全局应用AKSK
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:globalAppAksk:remove")
//    @Log(title = "全局应用AKSK", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iTGlobalAppAkskService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
