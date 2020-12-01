package com.yanzhen.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.dao.NoticeMapper;
import com.yanzhen.model.Notice;
import com.yanzhen.model.PropertyType;
import com.yanzhen.service.INoticeService;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {



    @Override
    public IPage<Notice> findListByPage(Integer page, Integer pageCount){
        IPage<Notice> wherePage = new Page<>(page, pageCount);
        Notice where = new Notice();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Notice notice){
        notice.setFbdate(new Date());
        return baseMapper.insert(notice);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Notice notice){
        return baseMapper.updateById(notice);
    }

    @Override
    public Notice findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public PageInfo<Notice> findPropertyTypeAll(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Notice> notices = this.baseMapper.selectList(null);
        PageInfo<Notice> pageInfo = new PageInfo(notices);

        return pageInfo;
    }
}
