package com.coderwzt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderwzt.model.Building;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coderwzt
 * @since 2020-11-08
 */
@Component("buildingDao")
public interface BuildingMapper extends BaseMapper<Building> {

    List<Building> queryBuildAll(@Param("numbers") String numbers);

}
