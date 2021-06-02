package com.coderwzt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderwzt.model.Comment;
import org.springframework.stereotype.Component;

/**
 * @Author: coderwzt
 * @Date: 2020/11/30 19:01
 * @Version 1.0
 */
@Component("commentDao")
public interface CommentMapper extends BaseMapper<Comment> {
}
