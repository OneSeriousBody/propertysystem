package com.coderwzt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.Owner;
import com.coderwzt.model.Parking;
import com.coderwzt.service.IParkingService;
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
@RestController
@RequestMapping("/parking")
public class ParkingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IParkingService parkingService;

    @RequestMapping("/queryParkAll")
    public JsonObject queryParkAll(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "15") Integer limit,
                                    String numbers){
        // 查询所有停车位信息
        PageInfo<Parking> pageInfo= parkingService.findParkAll(page,limit,numbers);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }

    /**
     * 关联到对应的户主
     * @param parking
     * @return
     */
    @PostMapping("/relationHouseholder")
    public R relationHouseholder(@RequestBody Parking parking) {

        // 关联户主
        parkingService.relationHouseholder(parking);
        return R.ok();
    }

    @ApiOperation(value = "新增")
    @RequestMapping("/add")
    public R add(@RequestBody Parking parking){
        if(parking.getOwnerId()!=null){//关联到了户主
           parking.setStatus(1);
        }else{
            parking.setStatus(0);
        }
        int num= parkingService.add(parking);
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
            parkingService.delete(Long.parseLong(id));
        }
        return R.ok();
    }

    @ApiOperation(value = "更新")
    @RequestMapping("/update")
    public R update(@RequestBody Parking parking){
        Parking park=new Parking();
        if(parking.getOwnerId()!=null){//关联到了户主
            park.setStatus(1);
        }else{
            park.setStatus(0);
        }
        park.setId(parking.getId());
        park.setNumbers(parking.getNumbers());
        park.setRemarks(parking.getRemarks());
        park.setOwnerId(parking.getOwnerId());
        int num= parkingService.updateData(park);
        if(num>0){
            return R.ok();
        }else{
            return R.fail("修改失败");
        }
    }






}
