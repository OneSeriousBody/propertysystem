package com.yanzhen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.dao.RepairMapper;
import com.yanzhen.model.*;
import com.yanzhen.service.IRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kappy
 * @since 2020-10-28
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements IRepairService {

    @Autowired
    private RepairMapper repairDao;

    @Autowired
    private RepairPersonnelServiceImpl repairPersonnelService;

    @Autowired
    private CommentServiceImpl commentService;

    @Override
    public PageInfo<Repair> findRepairAll(int page, int pagesise, Repair repair) {
        PageHelper.startPage(page,pagesise);
        List<Repair> list=repairDao.queryRepairAll(repair);
        for (int i = 0; i < list.size(); i++) {
            Integer personnelId = list.get(i).getPersonnelId();
            if (personnelId != null) {
                RepairPersonnel repairPersonnel = repairPersonnelService.getById(personnelId);
                QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
                Comment comment = commentService.getOne(queryWrapper.eq("repair_id", list.get(i).getId()));
                if (comment != null) {
                    list.get(i).setCommentId(comment.getId());
                }
                list.get(i).setPersonnelName(repairPersonnel.getName());
                list.get(i).setPersonnelPhone(repairPersonnel.getPhone());
            }
        }

        PageInfo<Repair> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Repair> queryList() {
        return repairDao.queryRepairAll(null);
    }


    @Override
    public IPage<Repair> findListByPage(Integer page, Integer pageCount){
        IPage<Repair> wherePage = new Page<>(page, pageCount);
        Repair where = new Repair();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Repair repair){
        return baseMapper.insert(repair);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Repair repair){
        return baseMapper.updateById(repair);
    }

    @Override
    public Repair findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public List<Tongji> queryTongji() {
        return repairDao.queryTongji();
    }

    @Override
    public void choosePersonnel(Repair repair) {

        repair.setStatus(1);
        this.updateById(repair);
    }
}
