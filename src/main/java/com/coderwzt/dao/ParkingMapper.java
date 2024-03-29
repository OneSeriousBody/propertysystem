package com.coderwzt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderwzt.model.Building;
import com.coderwzt.model.Parking;
import org.apache.ibatis.annotations.Param;
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
@Component("parkingDao")
public interface ParkingMapper extends BaseMapper<Parking> {

     //分页查询车位信息
     List<Parking> queryParkAll(@Param("numbers") String numbers);

     //查询使用的车位信息
     List<Parking> queryParkAllByStatus();


}
