package com.coderwzt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.coderwzt.dao.ComplaintMapper;
import com.coderwzt.model.Carcharge;
import com.coderwzt.model.Complaint;
import com.coderwzt.model.ComplaintType;
import com.coderwzt.model.Tongji;
import com.coderwzt.service.IComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coderwzt
 * @since 2020-11-08
 */
@Service
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements IComplaintService {

    @Autowired
    private ComplaintMapper complaintDao;

    @Override
    public PageInfo<Complaint> findComplaintAll(int page, int pagesise, Complaint complaint) {
        PageHelper.startPage(page,pagesise);
        List<Complaint> list=complaintDao.queryComplaintAll(complaint);
        PageInfo<Complaint> pageInfo=new PageInfo(list);
        return pageInfo;
    }

    @Override
    public IPage<Complaint> findListByPage(Integer page, Integer pageCount){
        IPage<Complaint> wherePage = new Page<>(page, pageCount);
        Complaint where = new Complaint();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }



    @Override
    public int add(Complaint complaint){
        return baseMapper.insert(complaint);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Complaint complaint){
        return baseMapper.updateById(complaint);
    }

    @Override
    public Complaint findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public List<Tongji> complaintStatistics() {

        return complaintDao.getCountGroupByComId();

    }
}
