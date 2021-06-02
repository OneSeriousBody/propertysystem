package com.coderwzt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.*;
import com.coderwzt.service.IOwnerService;
import com.coderwzt.service.IRepairService;
import com.coderwzt.service.IRepairtypeService;
import com.coderwzt.service.impl.MailServiceImpl;
import com.coderwzt.util.JsonObject;
import com.coderwzt.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
 * @since 2020-10-28
 */
@Api(tags = {""})
@RestController
@RequestMapping("/repair")
public class RepairController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IRepairService repairService;
    @Resource
    private IRepairtypeService repairtypeService;

    @Resource
    private IOwnerService ownerService;

    @Resource
    private MailServiceImpl mailService;

    @Value("${loginIndex}")
    private String loginIndex;

    @RequestMapping("/queryRepairAll")
    public JsonObject queryRepairAll(Repair repair,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "15") Integer limit){


        PageInfo<Repair> pageInfo=repairService.findRepairAll(page,limit,repair);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }

    @PostMapping("/addOrEditPrice")
    public R addOrEditPrice(@RequestBody Repair repair) {
        repair.setStatus(2);
        boolean flag = this.repairService.updateById(repair);
        if (flag) {
            return R.ok();
        }
        return R.fail("修改失败");

    }

    @PostMapping("/urgingPayment")
    public R urgingPayment(Integer ownerId){
        Owner owner = ownerService.getById(ownerId);
        mailService.sendHtmlMail(owner.getEmail(), "待缴费通知", "您还有一项费用代缴，请点击链接进行缴费，<a href="+ loginIndex +">链接</a>，如果已缴费，请忽略该邮件", null);
        return R.ok();

    }


    @RequestMapping("/queryRepairAll2")
    public JsonObject queryRepairAll2(Repair repair, HttpServletRequest request,
                                     @RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "15") Integer limit){


        //获取当前得登录用户
        Userinfo userinfo= (Userinfo) request.getSession().getAttribute("user");
        String username=userinfo.getUsername();
        //根据username获取登录账号得业主id
        Owner owner=ownerService.queryOwnerByName(username);
        repair.setOwnerId(owner.getId());
        PageInfo<Repair> pageInfo=repairService.findRepairAll(page,limit,repair);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }

    @RequestMapping("/queryAll")
    public List<Repairtype> queryAll(){
       return repairtypeService.findList();
    }


    @RequestMapping("/deleteByIds")
    public R deleteByIds(String ids){
       List<String> list= Arrays.asList(ids.split(","));
       for(String id:list){
           repairService.delete(Long.parseLong(id));
       }

       return R.ok();
    }

    @ApiOperation(value = "新增")
    @RequestMapping("/add")
    public R add(@RequestBody Repair repair,HttpServletRequest request)
    {
        //获取当前得登录用户
        Userinfo userinfo= (Userinfo) request.getSession().getAttribute("user");
        String username=userinfo.getUsername();
        //根据username获取登录账号得业主id
        Owner owner=ownerService.queryOwnerByName(username);
        repair.setOwnerId(owner.getId());
        repair.setStatus(0);
        repair.setComDate(new Date());
        int num=repairService.add(repair);
        if(num>0){
            return  R.ok();
        }
        return R.fail("失败啦");
    }

    @PostMapping("/choosePersonnel")
    public R choosePersonnel(@RequestBody Repair repair) {
        repairService.choosePersonnel(repair);
        return R.ok();
    }


    @ApiOperation(value = "更新")
    @RequestMapping("/update")
    public R update(Integer id){
         Repair repair=new Repair();
         repair.setId(id);
         repair.setStatus(1);
         repair.setHandleDate(new Date());
         int num=repairService.updateData(repair);
         return R.ok();
    }

    /**
     * 统计分析
     */
    @RequestMapping("/queryTongJi")
    public List<Tongji> queryTongji(){
        return repairService.queryTongji();
    }



}
