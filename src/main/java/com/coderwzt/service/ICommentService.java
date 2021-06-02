package com.coderwzt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.Comment;

/**
 * @Author: coderwzt
 * @Date: 2020/11/30 19:02
 * @Version 1.0
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 获取评论列表
     *
     * @param page
     * @param limit
     * @param comment
     * @return
     */
    PageInfo<Comment> getCommentList(Integer page, Integer limit, Comment comment);

    /**
     * 新增评论
     * @param comment
     * @return
     */
    boolean addComment(Comment comment);
}
