package com.yanzhen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.yanzhen.model.Records;
import com.yanzhen.model.RepairPersonnel;

/**
 * @Author: xhh
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
