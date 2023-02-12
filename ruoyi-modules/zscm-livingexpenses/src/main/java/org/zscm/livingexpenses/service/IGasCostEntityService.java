package org.zscm.livingexpenses.service;

import java.util.List;
import org.zscm.livingexpenses.domain.GasCostEntity;

/**
 * 燃气缴费记录Service接口
 * 
 * @author lk
 * @date 2023-02-10
 */
public interface IGasCostEntityService 
{
    /**
     * 查询燃气缴费记录
     * 
     * @param id 燃气缴费记录主键
     * @return 燃气缴费记录
     */
    public GasCostEntity selectGasCostEntityById(Long id);

    /**
     * 查询燃气缴费记录列表
     * 
     * @param gasCostEntity 燃气缴费记录
     * @return 燃气缴费记录集合
     */
    public List<GasCostEntity> selectGasCostEntityList(GasCostEntity gasCostEntity);

    /**
     * 新增燃气缴费记录
     * 
     * @param gasCostEntity 燃气缴费记录
     * @return 结果
     */
    public int insertGasCostEntity(GasCostEntity gasCostEntity);

    /**
     * 修改燃气缴费记录
     * 
     * @param gasCostEntity 燃气缴费记录
     * @return 结果
     */
    public int updateGasCostEntity(GasCostEntity gasCostEntity);

    /**
     * 批量删除燃气缴费记录
     * 
     * @param ids 需要删除的燃气缴费记录主键集合
     * @return 结果
     */
    public int deleteGasCostEntityByIds(Long[] ids);

    /**
     * 删除燃气缴费记录信息
     * 
     * @param id 燃气缴费记录主键
     * @return 结果
     */
    public int deleteGasCostEntityById(Long id);
}
