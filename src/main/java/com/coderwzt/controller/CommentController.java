package com.coderwzt.controller;

import com.github.pagehelper.PageInfo;
import com.coderwzt.model.Comment;
import com.coderwzt.model.Complaint;
import com.coderwzt.model.Owner;
import com.coderwzt.model.Userinfo;
import com.coderwzt.service.impl.CommentServiceImpl;
import com.coderwzt.service.impl.OwnerServiceImpl;
import com.coderwzt.util.JsonObject;
import com.coderwzt.util.R;
import com.coderwzt.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: coderwzt
 * @Date: 2020/11/30 19:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private OwnerServiceImpl ownerService;

    /**
     * 查询所有评论列表
     * @param request
     * @param comment
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/list")
    public JsonObject getComment(HttpServletRequest request, Comment comment,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "15") Integer limit) {
        // 判断当前的请求是否是管理员发起的
        Boolean flag = SessionUtils.isAdmin(request);
        // 如果不是管理员，只查询当前业主的信息
        if (!flag) {
            String username= SessionUtils.getUserInfo(request).getUsername();
            //根据username获取登录账号得业主id
            Owner owner = ownerService.queryOwnerByName(username);
            comment.setOwnerId(owner.getId());
        }
        // 获取评论数据
        PageInfo<Comment> pageInfo = commentService.getCommentList(page, limit, comment);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 新增评论
     * @param comment
     * @return
     */
    @PostMapping("/add")
    public R addComment(@RequestBody Comment comment, HttpServletRequest request) {
        String username= SessionUtils.getUserInfo(request).getUsername();
        //根据username获取登录账号得业主id
        Owner owner = ownerService.queryOwnerByName(username);
        // 设置评论的业主id
        comment.setOwnerId(owner.getId());
        // 将评论的数据添加到数据库
        boolean flag = commentService.addComment(comment);
        if (flag) {
            return R.ok();
        }
        return R.fail("新增失败");

    }

    /**
     * 修改评论
     * @param comment
     * @return
     */
    @PostMapping("/edit")
    public R editComment(@RequestBody Comment comment) {

        boolean flag = commentService.updateById(comment);
        if (flag) {
            return R.ok();
        }
        return R.fail("修改失败");
    }

    /**
     * 删除评论
     * @param ids
     * @return
     */
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        List<String> list= Arrays.asList(ids.split(","));
        commentService.removeByIds(list);
        return R.ok();
    }





}
