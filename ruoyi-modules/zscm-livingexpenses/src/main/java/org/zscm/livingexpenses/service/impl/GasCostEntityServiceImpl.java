package org.zscm.livingexpenses.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zscm.livingexpenses.mapper.GasCostEntityMapper;
import org.zscm.livingexpenses.domain.GasCostEntity;
import org.zscm.livingexpenses.service.IGasCostEntityService;

/**
 * 燃气缴费记录Service业务层处理
 * 
 * @author lk
 * @date 2023-02-10
 */
@Service
public class GasCostEntityServiceImpl implements IGasCostEntityService 
{
    @Autowired
    private GasCostEntityMapper gasCostEntityMapper;

    /**
     * 查询燃气缴费记录
     * 
     * @param id 燃气缴费记录主键
     * @return 燃气缴费记录
     */
    @Override
    public GasCostEntity selectGasCostEntityById(Long id)
    {
        return gasCostEntityMapper.selectGasCostEntityById(id);
    }

    /**
     * 查询燃气缴费记录列表
     * 
     * @param gasCostEntity 燃气缴费记录
     * @return 燃气缴费记录
     */
    @Override
    public List<GasCostEntity> selectGasCostEntityList(GasCostEntity gasCostEntity)
    {
        return gasCostEntityMapper.selectGasCostEntityList(gasCostEntity);
    }

    /**
     * 新增燃气缴费记录
     * 
     * @param gasCostEntity 燃气缴费记录
     * @return 结果
     */
    @Override
    public int insertGasCostEntity(GasCostEntity gasCostEntity)
    {
        gasCostEntity.setCreateTime(DateUtils.getNowDate());
        return gasCostEntityMapper.insertGasCostEntity(gasCostEntity);
    }

    /**
     * 修改燃气缴费记录
     * 
     * @param gasCostEntity 燃气缴费记录
     * @return 结果
     */
    @Override
    public int updateGasCostEntity(GasCostEntity gasCostEntity)
    {
        return gasCostEntityMapper.updateGasCostEntity(gasCostEntity);
    }

    /**
     * 批量删除燃气缴费记录
     * 
     * @param ids 需要删除的燃气缴费记录主键
     * @return 结果
     */
    @Override
    public int deleteGasCostEntityByIds(Long[] ids)
    {
        return gasCostEntityMapper.deleteGasCostEntityByIds(ids);
    }

    /**
     * 删除燃气缴费记录信息
     * 
     * @param id 燃气缴费记录主键
     * @return 结果
     */
    @Override
    public int deleteGasCostEntityById(Long id)
    {
        return gasCostEntityMapper.deleteGasCostEntityById(id);
    }
}
