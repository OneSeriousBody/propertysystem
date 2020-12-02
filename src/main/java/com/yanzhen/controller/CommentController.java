package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.model.Comment;
import com.yanzhen.model.Complaint;
import com.yanzhen.model.Owner;
import com.yanzhen.model.Userinfo;
import com.yanzhen.service.impl.CommentServiceImpl;
import com.yanzhen.service.impl.OwnerServiceImpl;
import com.yanzhen.util.JsonObject;
import com.yanzhen.util.R;
import com.yanzhen.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xhh
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

    @GetMapping("/list")
    public JsonObject getComment(HttpServletRequest request, Comment comment,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "15") Integer limit) {

        Boolean flag = SessionUtils.isAdmin(request);
        // 如果不是管理员，只查询当前业主的信息
        if (!flag) {
            String username= SessionUtils.getUserInfo(request).getUsername();
            //根据username获取登录账号得业主id
            Owner owner = ownerService.queryOwnerByName(username);
            comment.setOwnerId(owner.getId());
        }
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
        comment.setOwnerId(owner.getId());
        boolean flag = commentService.addComment(comment);
        if (flag) {
            return R.ok();
        }
        return R.fail("新增失败");

    }

    @PostMapping("/edit")
    public R editComment(@RequestBody Comment comment) {

        boolean flag = commentService.updateById(comment);
        if (flag) {
            return R.ok();
        }
        return R.fail("修改失败");
    }

    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        List<String> list= Arrays.asList(ids.split(","));
        commentService.removeByIds(list);
        return R.ok();
    }





}
