package com.coderwzt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.*;
import com.coderwzt.service.IHouseService;
import com.coderwzt.service.IOwnerService;
import com.coderwzt.service.IPropertyInfoService;
import com.coderwzt.service.IPropertyTypeService;
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
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coderwzt
 * @since 2020-11-08
 */
@Api(tags = {""})
@RestController
@RequestMapping("/propertyinfo")
public class PropertyInfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IPropertyInfoService propertyInfoService;

    @Resource
    private IHouseService houseService;

    @Resource
    private IOwnerService ownerService;

    @Resource
    private IPropertyTypeService propertyTypeService;

    @RequestMapping("/queryPropertyAll")
    public JsonObject queryPropertyAll(PropertyInfo propertyInfo, String numbers,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "15") Integer limit){
        if(numbers!=null){
            House house=new House();
            house.setNumbers(numbers);
            propertyInfo.setHouse(house);
        }

        PageInfo<PropertyInfo> pageInfo=propertyInfoService.findPropertyInfoAll(page,limit,propertyInfo);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }


    @RequestMapping("/queryPropertyAll2")
    public JsonObject queryPropertyAll2(PropertyInfo propertyInfo, HttpServletRequest request,
                                       @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "15") Integer limit){
        Userinfo userinfo= (Userinfo) request.getSession().getAttribute("user");
        String username=userinfo.getUsername();
        //根据username获取登录账号得业主id
         Owner owner=ownerService.queryOwnerByName(username);
//        Integer userId=owner.getId();
//        carcharge.setOwnerId(userId);
          Integer houId= owner.getHouseId();
          propertyInfo.setHouseId(houId);

        PageInfo<PropertyInfo> pageInfo=propertyInfoService.findPropertyInfoAll(page,limit,
                propertyInfo);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }








    @ApiOperation(value = "删除")
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        List<String> list= Arrays.asList(ids.split(","));
        for(String id:list){
            Long idLong=new Long(id);
            propertyInfoService.delete(idLong);
        }
        return R.ok();
    }


    @ApiOperation(value = "更新")
    @RequestMapping("/update")
    public R update(Integer id){
        PropertyInfo propertyInfo =new PropertyInfo();
        propertyInfo.setId(id);
        propertyInfo.setStatus(1);
        int num=propertyInfoService.updateData(propertyInfo);
        if(num>0){
            return R.ok();
        }
        return R.fail("失败");
    }


}
