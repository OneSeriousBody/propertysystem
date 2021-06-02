package com.coderwzt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.Carcharge;
import com.coderwzt.model.Complaint;
import com.coderwzt.model.ComplaintType;
import com.coderwzt.model.Tongji;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coderwzt
 * @since 2020-11-08
 */
public interface IComplaintService extends IService<Complaint> {


    PageInfo<Complaint> findComplaintAll(int page, int pagesise, Complaint complaint);
    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Complaint>
     */
    IPage<Complaint> findListByPage(Integer page, Integer pageCount);


    /**
     * 添加
     *
     * @param complaint
     * @return int
     */
    int add(Complaint complaint);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param complaint
     * @return int
     */
    int updateData(Complaint complaint);

    /**
     * id查询数据
     *
     * @param id id
     * @return Complaint
     */
    Complaint findById(Long id);

    /**
     * 从数据库获取对应投诉类型的数据
     * @return
     */
    List<Tongji> complaintStatistics();
}
