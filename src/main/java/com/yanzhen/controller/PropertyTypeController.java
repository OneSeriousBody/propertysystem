package com.yanzhen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.yanzhen.model.PropertyInfo;
import com.yanzhen.model.PropertyType;
import com.yanzhen.service.IPropertyTypeService;
import com.yanzhen.util.JsonObject;
import com.yanzhen.util.R;
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
 * @author kappy
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

    @RequestMapping("/queryAllBroadband")
    public JsonObject queryAllBroadband(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "15") Integer limit){
        PageInfo<PropertyType> pageInfo =  propertyTypeService.queryAllBroadband(page, limit);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());
    }


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

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<PropertyType> findListByPage(@RequestParam Integer page,
                                              @RequestParam Integer pageCount){
        return propertyTypeService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public PropertyType findById(@PathVariable Long id){
        return propertyTypeService.findById(id);
    }

}