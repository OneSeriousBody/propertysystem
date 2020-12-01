package com.yanzhen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.dao.CommentMapper;
import com.yanzhen.model.Comment;
import com.yanzhen.model.Complaint;
import com.yanzhen.model.Repair;
import com.yanzhen.model.RepairPersonnel;
import com.yanzhen.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xhh
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
        PageHelper.startPage(page,limit);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        if (comment.getOwnerId() != null) {
            queryWrapper.eq("owner_id", comment.getOwnerId());
        }
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
        if (comment.getRepairPersonnelId() != null) {
            queryWrapper.eq("repair_personnel_id", comment.getRepairPersonnelId());
        }
        List<Comment> list = this.list(queryWrapper);
        List<Comment> commentList = list.stream().map(comment1 -> {
            Integer repairPersonnelId = comment1.getRepairPersonnelId();
            RepairPersonnel repairPersonnel = repairPersonnelService.getById(repairPersonnelId);
            Integer repairId = comment1.getRepairId();
            Repair repair = repairService.findById(repairId.longValue());
            comment1.setRepairContent(repair.getRemarks());
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
