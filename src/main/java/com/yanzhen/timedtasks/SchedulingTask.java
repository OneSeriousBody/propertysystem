package com.yanzhen.timedtasks;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanzhen.model.*;
import com.yanzhen.service.impl.*;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 设置定时任务
 * @Author: xhh
 * @Date: 2020/11/28 23:48
 * @Version 1.0
 */
@Component
public class SchedulingTask {


    @Autowired
    private BroadbandServiceImpl broadbandService;

    @Autowired
    private PropertyTypeServiceImpl propertyTypeService;

    @Autowired
    private OwnerServiceImpl ownerService;

    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private CarchargeServiceImpl carchargeService;

    @Autowired
    private ParkingServiceImpl parkingService;

    @Value("${loginIndex}")
    private String loginIndex;


    /**
     * 每天零点判断是否有宽带需要自动续费
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void automaticRenewal() {
        QueryWrapper<Broadband> queryWrapper = new QueryWrapper<>();
        Date date = DateUtils.addDays(new Date(), +1);
        // 格式化时间
        String format = DateFormatUtils.format(date, "yyyy-MM-dd");
        // 查询出即将过期且需要自动续费的宽带列表
        List<Broadband> list = broadbandService.list(queryWrapper.eq("flag", 1).eq("over_time", format));
        list.forEach(broadband -> {
            broadband.setStatus(2);
            // 将之前的记录设置为过期状态
            broadbandService.updateById(broadband);
            Broadband newBroadband = new Broadband();
            Integer ownerId = broadband.getOwnerId();
            Integer typeId = broadband.getTypeId();
            PropertyType propertyType = propertyTypeService.findById(typeId.longValue());
            newBroadband.setOwnerId(ownerId);
            newBroadband.setStatus(0);
            newBroadband.setFlag(1);
            newBroadband.setTypeId(typeId);
            newBroadband.setName(propertyType.getName());
            // 设置价格
            newBroadband.setPrice(propertyType.getPrice());
            // 设置创建时间
            newBroadband.setCreateTime(broadband.getOverTime());
            // 设置过期时间为上次过期时间+1
            newBroadband.setOverTime(DateUtils.addMonths(broadband.getOverTime(), +1));
            broadbandService.save(newBroadband);

        });

    }

    /**
     * 每天零点判断是否有宽带或者停车位过期的，如果有过期的发短信提醒业主
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void automaticCancelBroadbandAndPark() {

        QueryWrapper<Broadband> queryWrapper = new QueryWrapper<>();
        String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        List<Broadband> broadbands = broadbandService.list(queryWrapper.eq("over_time", format));
        broadbands.forEach(broadband -> {
            broadband.setStatus(2);
            // 更新宽带的状态为过期状态
            broadbandService.updateById(broadband);
            Owner owner = ownerService.getById(broadband.getOwnerId());
            // 发送宽带已经过期了，让业主进行续费。
            mailService.sendHtmlMail(owner.getEmail(), "宽带过期","您的宽带已经过期，如需办理，可前往物业重新办理",  null);
        });

        QueryWrapper<Carcharge> carchargeQueryWrapper = new QueryWrapper<>();

        // 查找过期的停车位
        List<Carcharge> carchargeList = carchargeService.list(carchargeQueryWrapper.eq("end_time", format));
        carchargeList.forEach(carcharge -> {
            // 更新停车场的订单状态为已经过期
            carcharge.setStatus(2);
            Owner owner = ownerService.getById(carcharge.getOwnerId());

            carchargeService.updateById(carcharge);
            Integer parkId = carcharge.getParkId();
            Parking parking = parkingService.getById(parkId);
            parking.setStatus(0);
            parking.setOwnerId(null);
            // 更新停车的位置为未使用
            parkingService.updateById(parking);
            // 发送停车位已经过期了，让业主进行续费的停球。
            mailService.sendHtmlMail(owner.getEmail(), "停车位过期","您的停车位已经过期，如需办理，可前往物业重新办理",  null);
        });

    }


}
