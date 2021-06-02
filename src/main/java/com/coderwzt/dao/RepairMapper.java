package com.coderwzt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderwzt.model.Repair;
import com.coderwzt.model.Tongji;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coderwzt
 * @since 2020-10-28
 */
@Component("repairDao")
public interface RepairMapper extends BaseMapper<Repair> {

    List<Repair> queryRepairAll(Repair repair);

    //统计处理
    List<Tongji> queryTongji();

}
