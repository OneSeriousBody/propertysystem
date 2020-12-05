package com.yanzhen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yanzhen.model.ComplaintType;
import com.yanzhen.service.IComplaintTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("/complainttype")
public class ComplaintTypeController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IComplaintTypeService complaintTypeService;


    @RequestMapping("/queryAll")
    public List<ComplaintType> queryAll(){
       return  complaintTypeService.queryType();
    }

    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody ComplaintType complaintType){
        return complaintTypeService.add(complaintType);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return complaintTypeService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody ComplaintType complaintType){
        return complaintTypeService.updateData(complaintType);
    }



}
