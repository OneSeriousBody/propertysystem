package com.coderwzt.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderwzt.dao.ComplaintTypeMapper;
import com.coderwzt.model.ComplaintType;
import com.coderwzt.service.IComplaintTypeService;
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
public class ComplaintTypeServiceImpl extends ServiceImpl<ComplaintTypeMapper, ComplaintType> implements IComplaintTypeService {

    @Override
    public IPage<ComplaintType> findListByPage(Integer page, Integer pageCount){
        IPage<ComplaintType> wherePage = new Page<>(page, pageCount);
        ComplaintType where = new ComplaintType();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public List<ComplaintType> queryType() {
        return baseMapper.selectList(null);
    }

    @Override
    public int add(ComplaintType complaintType){
        return baseMapper.insert(complaintType);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(ComplaintType complaintType){
        return baseMapper.updateById(complaintType);
    }

    @Override
    public ComplaintType findById(Long id){
        return  baseMapper.selectById(id);
    }
}
