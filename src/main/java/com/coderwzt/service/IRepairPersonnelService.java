package com.coderwzt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.Records;
import com.coderwzt.model.RepairPersonnel;

/**
 * @Author: coderwzt
 * @Date: 2020/11/27 21:52
 * @Version 1.0
 */
public interface IRepairPersonnelService extends IService<RepairPersonnel> {
    /**
     * 获得维修人员信息
     * @param page
     * @param limit
     * @return
     */
    PageInfo<RepairPersonnel> findAllPersonnel(Integer page, Integer limit);
}
