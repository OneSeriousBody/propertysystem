package com.yanzhen.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.dao.ParkingMapper;
import com.yanzhen.model.Building;
import com.yanzhen.model.Carcharge;
import com.yanzhen.model.Parking;
import com.yanzhen.model.PropertyType;
import com.yanzhen.service.IParkingService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kappy
 * @since 2020-10-28
 */
@Service
public class ParkingServiceImpl extends ServiceImpl<ParkingMapper, Parking> implements IParkingService {

    @Autowired
    private ParkingMapper parkingDao;

    @Autowired
    private PropertyTypeServiceImpl propertyTypeService;

    @Autowired
    private CarchargeServiceImpl carchargeService;

    @Override
    public PageInfo<Parking> findParkAll(int page, int pageSize, String numbers) {
        // 开启分页
        PageHelper.startPage(page,pageSize);
        //查询的结果集
        List<Parking> list=parkingDao.queryParkAll(numbers);
        PageInfo<Parking> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public IPage<Parking> findListByPage(Integer page, Integer pageCount){
        IPage<Parking> wherePage = new Page<>(page, pageCount);
        Parking where = new Parking();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Parking parking){
        return baseMapper.insert(parking);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Parking parking){
        return baseMapper.updateById(parking);
    }

    @Override
    public Parking findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public List<Parking> queryParkingAll() {
        return null;
    }

    @Override
    public List<Parking> queryParkingByStatus() {
        return parkingDao.queryParkAllByStatus();
    }

    @Override
    public void relationHouseholder(Parking parking) {
        // 设置当前的停车场已经有人了
        parking.setStatus(1);
        this.updateById(parking);
        // 创建一个车位收费实体类
        Carcharge carcharge = new Carcharge();
        Date date = new Date();
        Date overDate = DateUtils.addMonths(date, parking.getParkUseMonth());
        carcharge.setPayDate(date);
        // 设置停车位使用了几个月
        carcharge.setEndDate(overDate);
        // 设置状态为未交费
        carcharge.setStatus(0);
        // 设置当前的用户id
        carcharge.setOwnerId(parking.getOwnerId());
        carcharge.setRemarks("无");
        // 设置停车费对应的车位id
        carcharge.setParkId(parking.getId());
        carcharge.setType("停车费");
        // 从PropertyType获取停车费用一个月多少钱
        PropertyType propertyType = propertyTypeService.getById(4);
        // 获取停车费
        Double price = propertyType.getPrice();
        Double totalPrice = price * parking.getParkUseMonth();
        // 设置停车费用
        carcharge.setMoney(totalPrice);
        // 加入到数据库
        carchargeService.add(carcharge);
    }


}
