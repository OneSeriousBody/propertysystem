package com.yanzhen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.dao.PropertyTypeMapper;
import com.yanzhen.model.PropertyInfo;
import com.yanzhen.model.PropertyType;
import com.yanzhen.service.IPropertyTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kappy
 * @since 2020-11-08
 */
@Service
public class PropertyTypeServiceImpl extends ServiceImpl<PropertyTypeMapper, PropertyType> implements IPropertyTypeService {

    @Override
    public IPage<PropertyType> findListByPage(Integer page, Integer pageCount){
        IPage<PropertyType> wherePage = new Page<>(page, pageCount);
        PropertyType where = new PropertyType();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }



    @Override
    public int add(PropertyType propertyType){
        return baseMapper.insert(propertyType);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(PropertyType propertyType){
        return baseMapper.updateById(propertyType);
    }

    @Override
    public PropertyType findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public List<PropertyType> findAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public PageInfo<PropertyType> findPropertyTypeAll(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<PropertyType> list = findAll();
        PageInfo<PropertyType> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo<PropertyType> queryAllBroadband(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        QueryWrapper<PropertyType> propertyTypeQueryWrapper = new QueryWrapper<>();
        propertyTypeQueryWrapper.eq("type", 1);
        List<PropertyType> list = this.list(propertyTypeQueryWrapper);
        PageInfo<PropertyType> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo<PropertyType> queryAllBase(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        QueryWrapper<PropertyType> propertyTypeQueryWrapper = new QueryWrapper<>();
        propertyTypeQueryWrapper.eq("type", 0);
        List<PropertyType> list = this.list(propertyTypeQueryWrapper);
        PageInfo<PropertyType> pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
