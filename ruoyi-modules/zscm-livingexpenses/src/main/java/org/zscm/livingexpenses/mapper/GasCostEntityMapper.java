package org.zscm.livingexpenses.mapper;

import java.util.List;
import org.zscm.livingexpenses.domain.GasCostEntity;

/**
 * 燃气缴费记录Mapper接口
 * 
 * @author lk
 * @date 2023-02-10
 */
public interface GasCostEntityMapper 
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
     * 删除燃气缴费记录
     * 
     * @param id 燃气缴费记录主键
     * @return 结果
     */
    public int deleteGasCostEntityById(Long id);

    /**
     * 批量删除燃气缴费记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGasCostEntityByIds(Long[] ids);
}
