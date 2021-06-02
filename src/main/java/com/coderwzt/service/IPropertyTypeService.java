package com.coderwzt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.PropertyType;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coderwzt
 * @since 2020-11-08
 */
public interface IPropertyTypeService extends IService<PropertyType> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<PropertyType>
     */
    IPage<PropertyType> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param propertyType
     * @return int
     */
    int add(PropertyType propertyType);

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
     * @param propertyType
     * @return int
     */
    int updateData(PropertyType propertyType);

    /**
     * id查询数据
     *
     * @param id id
     * @return PropertyType
     */
    PropertyType findById(Long id);


    List<PropertyType> findAll();

    /**
     * 分页查找手术价格数据
     * @return
     * @param page
     * @param limit
     */
    PageInfo<PropertyType> findPropertyTypeAll(Integer page, Integer limit);

    /**
     * 查询所有宽带类型的数据
     * @param page
     * @param limit
     * @return
     */
    PageInfo<PropertyType> queryAllBroadband(Integer page, Integer limit);

    /**
     * 查询所有基础类型的数据
     * @param page
     * @param limit
     * @return
     */
    PageInfo<PropertyType> queryAllBase(Integer page, Integer limit);
}
