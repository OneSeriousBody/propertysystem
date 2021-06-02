package com.coderwzt.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.coderwzt.dao.CarchargeMapper;
import com.coderwzt.model.Carcharge;
import com.coderwzt.service.ICarchargeService;
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
public class CarchargeServiceImpl extends ServiceImpl<CarchargeMapper, Carcharge> implements ICarchargeService {

    @Autowired
    private CarchargeMapper carchargeDao;


    @Override
    public PageInfo<Carcharge> findCarchargeAll(int page, int pagesise, Carcharge carcharge) {
        // 开启分页
        PageHelper.startPage(page,pagesise);
        // 从数据库查询对应的数据。
        List<Carcharge> list=carchargeDao.queryCarChargeAll(carcharge);
        PageInfo<Carcharge> pageInfo=new PageInfo(list);
        return pageInfo;
    }

    @Override
    public IPage<Carcharge> findListByPage(Integer page, Integer pageCount){
        IPage<Carcharge> wherePage = new Page<>(page, pageCount);
        Carcharge where = new Carcharge();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Carcharge carcharge){
        return baseMapper.insert(carcharge);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Carcharge carcharge){
        return baseMapper.updateById(carcharge);
    }

    @Override
    public Carcharge findById(Long id){
        return  baseMapper.selectById(id);
    }
}
