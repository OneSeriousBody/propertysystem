package com.coderwzt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.coderwzt.dao.BroadbandMapper;
import com.coderwzt.model.Broadband;
import com.coderwzt.model.Complaint;
import com.coderwzt.model.PropertyType;
import com.coderwzt.service.IBroadbandService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: coderwzt
 * @Date: 2020/11/28 22:29
 * @Version 1.0
 */
@Service
public class BroadbandServiceImpl extends ServiceImpl<BroadbandMapper, Broadband> implements IBroadbandService {

    @Autowired
    private PropertyTypeServiceImpl propertyTypeService;

    @Override
    public void addBroadband(Broadband broadband) {
        // 获取宽带办理的时间
        Integer broadbandNum = broadband.getBroadbandNum();
        Date date = new Date();
        Date overTime = DateUtils.addMonths(date, broadbandNum);
        broadband.setCreateTime(date);
        // 设置宽带的过期时间
        broadband.setOverTime(overTime);
        // 获取对应的宽带类型对应的价格
        PropertyType propertyType = propertyTypeService.getById(broadband.getTypeId());
        // 设置宽带对应的价格
        broadband.setPrice(propertyType.getPrice() * broadbandNum);
        // 设置办理的宽带业务名称
        broadband.setName(propertyType.getName());
        // 判断是否需要自动续费
        String broadbandFlagTitle = broadband.getFlagTitle();
        if (StringUtils.equalsIgnoreCase("on", broadbandFlagTitle)) {
            broadband.setFlag(1);
        } else {
            broadband.setFlag(0);
        }
        // 设置状态为未付款
        broadband.setStatus(0);
        this.save(broadband);


    }

    @Override
    public Broadband getLateBroadband(Integer id) {
        QueryWrapper<Broadband> queryWrapper = new QueryWrapper<>();
        String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        List<Broadband> broadbands = this.list(queryWrapper.eq("owner_id", id).ge("over_time", format).orderByDesc("over_time"));
        Broadband broadband = new Broadband();
        if (!CollectionUtils.isEmpty(broadbands)) {
            broadband = broadbands.get(0);
        }

        return broadband;
    }

    @Override
    public PageInfo<Broadband> queryBroadbandByOwnerId(Integer page, Integer limit, Broadband broadband) {
        PageHelper.startPage(page,limit);
        // 从数据库找到对应的业主宽带数据
        List<Broadband> list = this.list(new QueryWrapper<Broadband>().eq("owner_id", broadband.getOwnerId()));
        PageInfo<Broadband> pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
