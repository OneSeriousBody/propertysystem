package com.coderwzt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.Carcharge;
import com.coderwzt.model.Owner;
import com.coderwzt.model.Parking;
import com.coderwzt.model.Userinfo;
import com.coderwzt.service.ICarchargeService;
import com.coderwzt.service.IOwnerService;
import com.coderwzt.service.IParkingService;
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
import javax.websocket.Session;
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


    /**
     * 查询全部停车费用列表
     * @param carcharge
     * @param numbers
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/queryCarchargeAll")
    public JsonObject queryCarchargeAll(Carcharge carcharge,String numbers,
                                       @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "15") Integer limit){
        // 是否要查找对应的车位号
        if(numbers!=null){
            Parking parking=new Parking();
            parking.setNumbers(numbers);
            carcharge.setParking(parking);
        }
       // 查询停车费用
        PageInfo<Carcharge> pageInfo = carchargeService.findCarchargeAll(page,limit,carcharge);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }

    /**
     * 查询对应业主的停车费用列表
     * @param carcharge
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/queryCarchargeAll2")
    public JsonObject queryCarchargeAll2(Carcharge carcharge, HttpServletRequest request,
                                        @RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "15") Integer limit){
        // 从session获取用户的登录数据
        Userinfo userinfo= (Userinfo) request.getSession().getAttribute("user");
        String username=userinfo.getUsername();
        //根据username获取登录账号得业主id
        Owner owner=ownerService.queryOwnerByName(username);
        Integer userId=owner.getId();
        carcharge.setOwnerId(userId);
        // 查询对应业主的停车费用列表
        PageInfo<Carcharge> pageInfo=carchargeService.findCarchargeAll(page,limit,carcharge);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }

    /**
     * 发送短信给对应的用户通知其进行缴费
     * @param ownerId
     * @return
     */
    @PostMapping("/urgingPayment")
    public R urgingPayment(Integer ownerId){
        Owner owner = ownerService.getById(ownerId);
        mailService.sendHtmlMail(owner.getEmail(), "待缴费通知", "您还有一项费用代缴，请点击链接进行缴费，<a href="+ loginIndex +">链接</a>，如果已缴费，请忽略该邮件", null);
        return R.ok();

    }


    /**
     * 删除数据
     * @param ids
     * @return
     */
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        List<String> list= Arrays.asList(ids.split(","));
        for(String id:list){
           Long idLong=new Long(id);
           carchargeService.delete(idLong);
        }
        return R.ok();
    }

    /**
     * 更新数据
     * @param id
     * @return
     */
    @RequestMapping("/update")
    public R update(Integer id){
        Carcharge carcharge =new Carcharge();
        carcharge.setId(id);
        // 设置缴费状态为已经缴费
        carcharge.setStatus(1);
        int num=carchargeService.updateData(carcharge);
        if(num>0){
            return R.ok();
        }
        return R.fail("失败");
    }



}
