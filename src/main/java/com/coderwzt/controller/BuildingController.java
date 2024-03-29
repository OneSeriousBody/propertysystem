package com.coderwzt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.Building;
import com.coderwzt.service.IBuildingService;
import com.coderwzt.util.JsonObject;
import com.coderwzt.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
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
@RequestMapping("/building")
public class BuildingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IBuildingService buildingService;

    /**
     * 查询全部房屋
     * @param page
     * @param limit
     * @param numbers
     * @return
     */
    @RequestMapping("/queryBuildAll")
    public JsonObject queryBuildAll(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "15") Integer limit,
                                    String numbers){
        JsonObject object=new JsonObject();
        PageInfo<Building> pageInfo= buildingService.findBuildAll(page,limit,numbers);
        object.setCode(0);
        object.setMsg("ok");
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        return object;
    }

    @RequestMapping("/queryBuild")
    public List<Building> queryBuild(){
        PageInfo<Building> pageInfo= buildingService.findBuildAll(1,100,null);
        return pageInfo.getList();
    }


    /**
     * 新增建筑
     * @param building
     * @return
     */
    @ApiOperation(value = "新增")
    @RequestMapping("/add")
    public R add(@RequestBody Building building){
        int num= buildingService.add(building);
        if(num>0){
            return R.ok();
        }else{
            return R.fail("添加失败");
        }

    }

    /**
     * 删除建筑
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除")
    @RequestMapping("/deleteByIds")
    public R delete(String  ids){
        List<String> list= Arrays.asList(ids.split(","));
        //遍历遍历进行删除
        for(String id:list){
            buildingService.delete(Long.parseLong(id));
        }
        return R.ok();
    }

    /**
     * 更新建筑
     * @param building
     * @return
     */
    @ApiOperation(value = "更新")
    @RequestMapping("/update")
    public R update(@RequestBody Building building){
        int num= buildingService.updateData(building);
        if(num>0){
            return R.ok();
        }else{
            return R.fail("修改失败");
        }
    }



}
