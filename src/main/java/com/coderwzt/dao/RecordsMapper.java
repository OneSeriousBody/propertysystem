package com.coderwzt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderwzt.model.RecordVo;
import com.coderwzt.model.Records;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("recordsDao")
public interface RecordsMapper extends BaseMapper<Records> {


   //查询所有的记录信息
    List<RecordVo> queryRecordsAll(RecordVo recordVo);

//    根据房子id和类型id获取最后一次记录信息
      Records queryByHouIdAndTypeId(@Param("houId") Integer houId, @Param("typeId") Integer typeId);

}
