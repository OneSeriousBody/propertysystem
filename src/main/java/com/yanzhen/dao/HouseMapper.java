package com.yanzhen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanzhen.model.House;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("houseDao")
public interface HouseMapper extends BaseMapper<House> {

    /**
     * 查询
     */

    List<House> findHouseAll(@Param("numbers") String numbers);



}
