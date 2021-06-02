package com.coderwzt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.Building;
import com.coderwzt.model.Userinfo;
import com.coderwzt.service.IUserinfoService;
import com.coderwzt.util.JsonObject;
import com.coderwzt.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coderwzt
 * @since 2020-10-28
 */
@Api(tags = {""})
@RestController
@RequestMapping("/userinfo")
public class UserinfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IUserinfoService userinfoService;

    @RequestMapping("/queryUserInfoAll")
    public JsonObject queryUserInfoAll(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "15") Integer limit,
                                    Userinfo userinfo){
        JsonObject object=new JsonObject();
        PageInfo<Userinfo> pageInfo= userinfoService.findUserinfoAll(page,limit,userinfo);
        object.setCode(0);
        object.setMsg("ok");
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        return object;
    }

    @ApiOperation(value = "删除")
    @RequestMapping("/deleteByIds")
    public R delete(String  ids){
        List<String> list= Arrays.asList(ids.split(","));
        //遍历遍历进行删除
        for(String id:list){
            userinfoService.delete(Long.parseLong(id));
        }
        return R.ok();
    }


    @ApiOperation(value = "新增")
    @RequestMapping("/add")
    public R add(@RequestBody Userinfo userinfo){
        userinfoService.add(userinfo);
        return R.ok();
    }


    @ApiOperation(value = "更新")
    @RequestMapping("/update")
    public R update(String oldPwd,String newPwd,Integer id){
        //根据id获取当前的数据记录
        Userinfo user=userinfoService.findById(new Long(id));
        user.setPassword(newPwd);
        userinfoService.updateData(user);
        return R.ok();

    }


}
