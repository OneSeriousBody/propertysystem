package com.yanzhen.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.dao.BuildingMapper;
import com.yanzhen.dao.RepairPersonnelMapper;
import com.yanzhen.model.Building;
import com.yanzhen.model.Notice;
import com.yanzhen.model.RepairPersonnel;
import com.yanzhen.service.IBuildingService;
import com.yanzhen.service.IRepairPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @Author: xhh
 * @Date: 2020/11/27 21:53
 * @Version 1.0
 */
@Service
public class RepairPersonnelServiceImpl extends ServiceImpl<RepairPersonnelMapper, RepairPersonnel> implements IRepairPersonnelService {

    @Autowired
    private RepairPersonnelMapper repairPersonnelDao;


    @Override
    public PageInfo<RepairPersonnel> findAllPersonnel(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<RepairPersonnel> repairPersonnels = this.baseMapper.selectList(null);
        PageInfo<RepairPersonnel> pageInfo = new PageInfo(repairPersonnels);

        return pageInfo;
    }
}
