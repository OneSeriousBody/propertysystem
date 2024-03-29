package com.coderwzt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.*;
import com.coderwzt.service.IOwnerService;
import com.coderwzt.service.IUserinfoService;
import com.coderwzt.service.impl.HouseServiceImpl;
import com.coderwzt.util.JsonObject;
import com.coderwzt.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author coderwzt
 * @since 2020-11-08
 */
@Api(tags = {""})
@RestController
@RequestMapping("/owner")
public class OwnerController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IOwnerService ownerService;

    @Resource
    private IUserinfoService userinfoService;

    @Resource
    private HouseServiceImpl houseService;


    @RequestMapping("/queryOwnerAll")
    public JsonObject queryOwnerAll(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "15") Integer limit,
                                    Owner owner){
        PageInfo<Owner> pageInfo= ownerService.findOwnerAll(page,limit,owner);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }





    @RequestMapping("/queryAll")
    @Transactional
    public List queryAll(){
        PageInfo<Owner> pageInfo= ownerService.findOwnerAll(1,100,null);
        return pageInfo.getList();
    }


    @ApiOperation(value = "新增")
    @RequestMapping("/add")
    @Transactional
    public R add(@RequestBody Owner owner){
        //md5加密
//        String md5Password = DigestUtils.md5DigestAsHex("123456".getBytes());
        owner.setPassword("123456");//默认密码123456
        int num= ownerService.add(owner);
        House house = new House();
        Integer houseId = owner.getHouseId();
        house.setId(houseId);
        house.setIntoDate(owner.getIntoDate());
        house.setStatus(1);
        houseService.updateById(house);
        //同步添加到用户信息
        Userinfo user=new Userinfo();
        user.setPassword("123456");//默认密码
        user.setRemarks(owner.getRemarks());
        user.setType(0);
        user.setUsername(owner.getUsername());
        userinfoService.add(user);


        if(num>0){
            return R.ok();
        }else{
            return R.fail("添加失败");
        }

    }

    @ApiOperation(value = "删除")
    @RequestMapping("/deleteByIds")
    public R delete(String  ids){
        List<String> list= Arrays.asList(ids.split(","));
        //遍历遍历进行删除
        for(String id:list){
            ownerService.delete(Long.parseLong(id));
        }
        return R.ok();
    }

    @ApiOperation(value = "更新")
    @RequestMapping("/update")
    public R update(@RequestBody Owner owner){
        int num= ownerService.updateData(owner);
        if(num>0){
            return R.ok();
        }else{
            return R.fail("修改失败");
        }
    }


}
