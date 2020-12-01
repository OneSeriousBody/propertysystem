package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.model.Broadband;

import com.yanzhen.model.Complaint;
import com.yanzhen.model.Owner;
import com.yanzhen.model.Userinfo;
import com.yanzhen.service.impl.BroadbandServiceImpl;
import com.yanzhen.service.impl.OwnerServiceImpl;
import com.yanzhen.util.JsonObject;
import com.yanzhen.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xhh
 * @Date: 2020/11/28 22:30
 * @Version 1.0
 */
@RequestMapping("/broadband")
@RestController
public class BroadbandController {

    @Resource
    private BroadbandServiceImpl broadbandService;

    @Resource
    private OwnerServiceImpl ownerService;

    @RequestMapping("/queryAll")
    public JsonObject queryBroadband(HttpServletRequest request,
                                         @RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "15") Integer limit){
        //获取当前得登录用户
        Userinfo userinfo= (Userinfo) request.getSession().getAttribute("user");
        String username=userinfo.getUsername();
        //根据username获取登录账号得业主id
        Owner owner=ownerService.queryOwnerByName(username);
        Broadband broadband = new Broadband();
        broadband.setOwnerId(owner.getId());
        PageInfo<Broadband> pageInfo=broadbandService.queryBroadbandByOwnerId(page,limit,broadband);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }

    @PostMapping("/addBroadband")
    public R addBroadband(@RequestBody Broadband broadband) {
        broadbandService.addBroadband(broadband);
        return R.ok();
    }

}
