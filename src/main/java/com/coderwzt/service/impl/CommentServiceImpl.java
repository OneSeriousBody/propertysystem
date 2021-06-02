package com.coderwzt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.coderwzt.dao.CommentMapper;
import com.coderwzt.model.Comment;
import com.coderwzt.model.Complaint;
import com.coderwzt.model.Repair;
import com.coderwzt.model.RepairPersonnel;
import com.coderwzt.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: coderwzt
 * @Date: 2020/11/30 19:02
 * @Version 1.0
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    private RepairPersonnelServiceImpl repairPersonnelService;

    @Autowired
    private RepairServiceImpl repairService;

    @Override
    public PageInfo<Comment> getCommentList(Integer page, Integer limit, Comment comment) {
        // 开启分页
        PageHelper.startPage(page, limit);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        // 判断业主id是否为空，如果不为空的话，则将业主id作为sql的查询条件
        if (comment.getOwnerId() != null) {
            queryWrapper.eq("owner_id", comment.getOwnerId());
        }
        // 判断要查询的数据是好评、中评还是差评
        if (comment.getScore() != null) {
            Double score = comment.getScore();
            if (score <= 2.0) {
                queryWrapper.le("score", score);
            } else if (score <= 4.0) {
                queryWrapper.between("score", 2.0, 4.0);
            } else {
                queryWrapper.gt("score", 4.0);
            }

        }
        // 判断维修人员的id是否为空，如果不为空的话，则将维修人员的id作为sql的查询条件
        if (comment.getRepairPersonnelId() != null) {
            queryWrapper.eq("repair_personnel_id", comment.getRepairPersonnelId());
        }
        List<Comment> list = this.list(queryWrapper);
        List<Comment> commentList = list.stream().map(comment1 -> {
            // 获取评论对应的维修人员id
            Integer repairPersonnelId = comment1.getRepairPersonnelId();
            // 获取对应的维修人员
            RepairPersonnel repairPersonnel = repairPersonnelService.getById(repairPersonnelId);
            // 获取维修的id
            Integer repairId = comment1.getRepairId();
            Repair repair = repairService.findById(repairId.longValue());
            // 设置前维修的内容
            comment1.setRepairContent(repair.getRemarks());
            // 设置维修的人员姓名
            comment1.setRepairPersonnelName(repairPersonnel.getName());
            return comment1;
        }).collect(Collectors.toList());
        PageInfo<Comment> pageInfo=new PageInfo(commentList);
        return pageInfo;
    }

    @Override
    public boolean addComment(Comment comment) {
        comment.setCreateTime(new Date());
        return this.save(comment);
    }
}
