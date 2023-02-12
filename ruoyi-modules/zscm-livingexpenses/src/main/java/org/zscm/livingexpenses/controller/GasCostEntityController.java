package org.zscm.livingexpenses.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import org.zscm.livingexpenses.domain.GasCostEntity;
import org.zscm.livingexpenses.service.IGasCostEntityService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 燃气缴费记录Controller
 * 
 * @author lk
 * @date 2023-02-10
 */
@RestController
@RequestMapping("/GasCostRecord")
public class GasCostEntityController extends BaseController
{
    @Autowired
    private IGasCostEntityService gasCostEntityService;

    /**
     * 查询燃气缴费记录列表
     */
    @RequiresPermissions("LivingExpenses:GasCostRecord:list")
    @GetMapping("/list")
    public TableDataInfo list(GasCostEntity gasCostEntity)
    {
        startPage();
        List<GasCostEntity> list = gasCostEntityService.selectGasCostEntityList(gasCostEntity);
        return getDataTable(list);
    }

    /**
     * 导出燃气缴费记录列表
     */
    @RequiresPermissions("LivingExpenses:GasCostRecord:export")
    @Log(title = "燃气缴费记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GasCostEntity gasCostEntity)
    {
        List<GasCostEntity> list = gasCostEntityService.selectGasCostEntityList(gasCostEntity);
        ExcelUtil<GasCostEntity> util = new ExcelUtil<>(GasCostEntity.class);
        util.exportExcel(response, list, "燃气缴费记录数据");
    }

    /**
     * 获取燃气缴费记录详细信息
     */
    @RequiresPermissions("LivingExpenses:GasCostRecord:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(gasCostEntityService.selectGasCostEntityById(id));
    }

    /**
     * 新增燃气缴费记录
     */
    @RequiresPermissions("LivingExpenses:GasCostRecord:add")
    @Log(title = "燃气缴费记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GasCostEntity gasCostEntity)
    {
        return toAjax(gasCostEntityService.insertGasCostEntity(gasCostEntity));
    }

    /**
     * 修改燃气缴费记录
     */
    @RequiresPermissions("LivingExpenses:GasCostRecord:edit")
    @Log(title = "燃气缴费记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GasCostEntity gasCostEntity)
    {
        return toAjax(gasCostEntityService.updateGasCostEntity(gasCostEntity));
    }

    /**
     * 删除燃气缴费记录
     */
    @RequiresPermissions("LivingExpenses:GasCostRecord:remove")
    @Log(title = "燃气缴费记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gasCostEntityService.deleteGasCostEntityByIds(ids));
    }
}
