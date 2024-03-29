package com.coderwzt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderwzt.model.Complaint;
import com.coderwzt.model.Tongji;
import org.apache.ibatis.annotations.Select;
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
@Component("complaintDao")
public interface ComplaintMapper extends BaseMapper<Complaint> {

    List<Complaint> queryComplaintAll(Complaint complaint);

    @Select("select count(complaint.id) as counts, complaint_type.`name` as `name`  FROM complaint, complaint_type where complaint_type.id = com_id  GROUP BY com_id")
    List<Tongji> getCountGroupByComId();

}
