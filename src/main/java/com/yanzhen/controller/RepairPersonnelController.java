package com.yanzhen.controller;

import com.github.pagehelper.PageInfo;
import com.yanzhen.model.Notice;
import com.yanzhen.model.RepairPersonnel;
import com.yanzhen.service.impl.RepairPersonnelServiceImpl;
import com.yanzhen.util.JsonObject;
import com.yanzhen.util.R;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: xhh
 * @Date: 2020/11/27 21:56
 * @Version 1.0
 */
@RestController
@RequestMapping("/repairPersonnel")
public class RepairPersonnelController {

    @Autowired
    private RepairPersonnelServiceImpl repairPersonnelService;

    @GetMapping("/findAllPersonnel")
    public JsonObject findAllNotice(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "15") Integer limit) {
        PageInfo<RepairPersonnel> pageInfo =  repairPersonnelService.findAllPersonnel(page, limit);

        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public R add(@RequestBody RepairPersonnel repairPersonnel){
        boolean flag = repairPersonnelService.save(repairPersonnel);
        if (flag) {
            return R.ok();
        }
        return R.fail("新增失败");
    }

    @ApiOperation(value = "删除")
    @PostMapping("deleteByIds")
    public R delete(String  ids){
        List<String> list= Arrays.asList(ids.split(","));
        repairPersonnelService.removeByIds(list);
        return R.ok();
    }

    @ApiOperation(value = "更新")
    @PostMapping("/edit")
    public R update(@RequestBody RepairPersonnel repairPersonnel){
        boolean flag = repairPersonnelService.updateById(repairPersonnel);
        if (flag) {
            return R.ok();
        }
        return R.fail("修改失败");

    }


}
