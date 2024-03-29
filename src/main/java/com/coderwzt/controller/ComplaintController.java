package com.coderwzt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.*;
import com.coderwzt.service.IComplaintService;
import com.coderwzt.service.IOwnerService;
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
import java.util.Date;
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
@RequestMapping("/complaint")
public class ComplaintController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IComplaintService complaintService;
    @Resource
    private IOwnerService ownerService;

    @RequestMapping("/queryComplaintAll")
    public JsonObject queryComplaintAll(Complaint complaint, String numbers,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "15") Integer limit){


        PageInfo<Complaint> pageInfo=complaintService.findComplaintAll(page,limit,complaint);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }

    @RequestMapping("/queryComplaintAll2")
    public JsonObject queryComplaintAll2(Complaint complaint, HttpServletRequest request,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "15") Integer limit){
        //获取当前得登录用户
        Userinfo userinfo= (Userinfo) request.getSession().getAttribute("user");
        String username=userinfo.getUsername();
        //根据username获取登录账号得业主id
        Owner owner=ownerService.queryOwnerByName(username);
        complaint.setOwnerId(owner.getId());
        PageInfo<Complaint> pageInfo=complaintService.findComplaintAll(page,limit,complaint);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }






    @ApiOperation(value = "新增")
    @RequestMapping("/add")
    public R add(@RequestBody Complaint complaint,HttpServletRequest request)
    {
        //获取当前得登录用户
        Userinfo userinfo= (Userinfo) request.getSession().getAttribute("user");
        String username=userinfo.getUsername();
        //根据username获取登录账号得业主id
        Owner owner=ownerService.queryOwnerByName(username);
        complaint.setOwnerId(owner.getId());
        complaint.setStatus(0);
        complaint.setComDate(new Date());
        int num=complaintService.add(complaint);
        if(num>0){
            return  R.ok();
        }
        return R.fail("失败啦");
    }

    @ApiOperation(value = "删除")
    @RequestMapping("/deleteByIds")
    public R deleteByIds(String ids){
       List<String> list=Arrays.asList(ids.split(","));
       for(String id:list){
           complaintService.delete(Long.parseLong(id));
       }
       return R.ok();
    }

    @ApiOperation(value = "更新")
    @RequestMapping("/update")
    public R update(Integer id){
         Complaint complaint=new Complaint();
         complaint.setId(id);
         complaint.setClr(1);
         complaint.setHandleDate(new Date());
         complaint.setStatus(1);
//         complaint.setClr()
        int num= complaintService.updateData(complaint);
        if(num>0){
            return R.ok();
        }else{
            return R.fail("处理失败");
        }
    }

    /**
     * 获取统计的数据
     * @return
     */
    @PostMapping("/complaintStatistics")
    public List<Tongji> complaintStatistics() {
        List<Tongji> complaintStatistics = complaintService.complaintStatistics();
        return complaintStatistics;
    }




}
