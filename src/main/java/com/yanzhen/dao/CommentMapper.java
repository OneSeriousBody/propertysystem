package com.yanzhen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanzhen.model.Comment;
import org.springframework.stereotype.Component;

/**
 * @Author: xhh
 * @Date: 2020/11/30 19:01
 * @Version 1.0
 */
@Component("commentDao")
public interface CommentMapper extends BaseMapper<Comment> {
}
