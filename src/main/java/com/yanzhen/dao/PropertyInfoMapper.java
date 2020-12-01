package com.yanzhen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.model.PropertyInfo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kappy
 * @since 2020-11-08
 */
@Component("propertyInfoDao")
public interface PropertyInfoMapper extends BaseMapper<PropertyInfo> {

    /**
     * 根据登记时间和房子id获取当前记录信息
     */
    void deleteByHouIdAndTime( @Param("houId") Integer houId, @Param("endDate") String onTime);

    List<PropertyInfo> queryListAll(PropertyInfo propertyInfo);
}
