package com.coderwzt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.PropertyInfo;
import com.coderwzt.model.PropertyType;
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
@RequestMapping("/propertyType")
public class PropertyTypeController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IPropertyTypeService propertyTypeService;


    @RequestMapping("/queryAll")
    public JsonObject queryList(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "15") Integer limit){
        PageInfo<PropertyType> pageInfo =  propertyTypeService.findPropertyTypeAll(page, limit);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 查询宽带套餐
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/queryAllBroadband")
    public JsonObject queryAllBroadband(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "15") Integer limit){
        PageInfo<PropertyType> pageInfo =  propertyTypeService.queryAllBroadband(page, limit);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());
    }


    /**
     * 查询水电费
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/queryAllBase")
    public JsonObject queryAllBase(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "15") Integer limit){
        PageInfo<PropertyType> pageInfo =  propertyTypeService.queryAllBase(page, limit);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());
    }



    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public R add(@RequestBody PropertyType propertyType){
        int num = propertyTypeService.add(propertyType);
        if (num > 0) {
            return R.ok();
        }
        return R.fail("新增失败");
    }

    @ApiOperation(value = "删除")
    @PostMapping("deleteByIds")
    public R delete(String ids){
        List<String> list= Arrays.asList(ids.split(","));
        //遍历遍历进行删除
        for(String id:list){
            propertyTypeService.delete(Long.parseLong(id));
        }
        return R.ok();
    }

    @ApiOperation(value = "更新")
    @PostMapping("/edit")
    public R update(@RequestBody PropertyType propertyType){
        int num = propertyTypeService.updateData(propertyType);
        if (num > 0) {
            return R.ok();
        }
        return R.fail("更新失败");
    }



}
