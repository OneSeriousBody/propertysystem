package com.yanzhen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.yanzhen.model.Carcharge;
import com.yanzhen.model.Owner;
import com.yanzhen.model.Parking;
import com.yanzhen.model.Userinfo;
import com.yanzhen.service.ICarchargeService;
import com.yanzhen.service.IOwnerService;
import com.yanzhen.service.IParkingService;
import com.yanzhen.service.impl.MailServiceImpl;
import com.yanzhen.util.JsonObject;
import com.yanzhen.util.R;
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
import javax.websocket.Session;
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
@RequestMapping("/carcharge")
public class CarchargeController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ICarchargeService carchargeService;

    @Resource
    private IParkingService parkingService;

    @Resource
    private IOwnerService ownerService;

    @Resource
    private MailServiceImpl mailService;

    @Value("${loginIndex}")
    private String loginIndex;


    @RequestMapping("/queryCarchargeAll")
    public JsonObject queryCarchargeAll(Carcharge carcharge,String numbers,
                                       @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "15") Integer limit){
       if(numbers!=null){
           Parking parking=new Parking();
           parking.setNumbers(numbers);
           carcharge.setParking(parking);
       }

        PageInfo<Carcharge> pageInfo=carchargeService.findCarchargeAll(page,limit,carcharge);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }


    @RequestMapping("/queryCarchargeAll2")
    public JsonObject queryCarchargeAll2(Carcharge carcharge, HttpServletRequest request,
                                        @RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "15") Integer limit){

        Userinfo userinfo= (Userinfo) request.getSession().getAttribute("user");
        String username=userinfo.getUsername();
        //根据username获取登录账号得业主id
        Owner owner=ownerService.queryOwnerByName(username);
        Integer userId=owner.getId();
        carcharge.setOwnerId(userId);
        PageInfo<Carcharge> pageInfo=carchargeService.findCarchargeAll(page,limit,carcharge);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }

    @PostMapping("/urgingPayment")
    public R urgingPayment(Integer ownerId){
        Owner owner = ownerService.getById(ownerId);
        mailService.sendHtmlMail(owner.getEmail(), "待缴费通知", "您还有一项费用代缴，请点击链接进行缴费，<a href="+ loginIndex +">链接</a>，如果已缴费，请忽略该邮件", null);
        return R.ok();

    }



    @ApiOperation(value = "新增")
    @RequestMapping("/initData")
    public R InitData(@RequestBody Carcharge carcharge){
        /**
         * 遍历所有得已在使用得车位信息
         */
        List<Parking> parkingList=parkingService.queryParkingByStatus();
        for(Parking park:parkingList){
            carcharge.setStatus(0);
            carcharge.setOwnerId(park.getOwnerId());
            carcharge.setType("停车费");
            carcharge.setParkId(park.getId());
            carchargeService.add(carcharge);
        }
       return R.ok();

    }

    @ApiOperation(value = "删除")
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        List<String> list= Arrays.asList(ids.split(","));
        for(String id:list){
           Long idLong=new Long(id);
           carchargeService.delete(idLong);
        }
        return R.ok();
    }

    @ApiOperation(value = "更新")
    @RequestMapping("/update")
    public R update(Integer id){
        Carcharge carcharge =new Carcharge();
        carcharge.setId(id);
        carcharge.setStatus(1);
        int num=carchargeService.updateData(carcharge);
        if(num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Carcharge> findListByPage(@RequestParam Integer page,
                                           @RequestParam Integer pageCount){
        return carchargeService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Carcharge findById(@PathVariable Long id){
        return carchargeService.findById(id);
    }

}
