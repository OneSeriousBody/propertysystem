package com.coderwzt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderwzt.model.Building;
import com.coderwzt.model.RepairPersonnel;
import org.springframework.stereotype.Component;

/**
 * @Author: coderwzt
 * @Date: 2020/11/27 21:51
 * @Version 1.0
 */
@Component("repairPersonnelDao")
public interface RepairPersonnelMapper extends BaseMapper<RepairPersonnel> {
}
