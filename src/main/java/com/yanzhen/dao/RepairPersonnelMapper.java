package com.yanzhen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanzhen.model.Building;
import com.yanzhen.model.RepairPersonnel;
import org.springframework.stereotype.Component;

/**
 * @Author: xhh
 * @Date: 2020/11/27 21:51
 * @Version 1.0
 */
@Component("repairPersonnelDao")
public interface RepairPersonnelMapper extends BaseMapper<RepairPersonnel> {
}
