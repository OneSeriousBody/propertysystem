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

        parking.setStatus(1);
        this.updateById(parking);
        Carcharge carcharge = new Carcharge();
        Date date = new Date();
        Date overDate = DateUtils.addMonths(date, parking.getParkUseMonth());
        carcharge.setPayDate(date);
        carcharge.setEndDate(overDate);
        carcharge.setStatus(0);
        carcharge.setOwnerId(parking.getOwnerId());
        carcharge.setRemarks("无");
        carcharge.setParkId(parking.getId());
        carcharge.setType("停车费");
        PropertyType propertyType = propertyTypeService.getById(4);
        Double price = propertyType.getPrice();
        Double totalPrice = price * parking.getParkUseMonth();
        carcharge.setMoney(totalPrice);
        carchargeService.add(carcharge);
    }


}
