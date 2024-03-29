package com.coderwzt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.coderwzt.model.Notice;
import com.coderwzt.model.Owner;
import com.coderwzt.model.PropertyType;
import com.coderwzt.service.INoticeService;
import com.coderwzt.service.IOwnerService;
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
@RequestMapping("/notice")
public class NoticeController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private INoticeService noticeService;

    @Resource
    private IOwnerService ownerService;

    @Resource
    private MailServiceImpl mailService;

    @Value("${loginIndex}")
    private String loginIndex;

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public R add(@RequestBody Notice notice){
        // 将公告添加到数据库
        int num = noticeService.add(notice);
        if (num > 0) {
            // 获取所有业主信息
            List<Owner> list = ownerService.list();
            list.forEach(owner -> {
                // 发送邮件给业主，通知有新的公告。
                mailService.sendHtmlMail(owner.getEmail(), "新公告", "您还有一个新的公告未看，请点击链接前往官网观看，<a href="+ loginIndex +">链接</a>", null);
            });
            return R.ok();
        }

        return R.fail("新增失败");

    }

    @ApiOperation(value = "删除")
    @PostMapping("deleteByIds")
    public R delete(String  ids){
        List<String> list= Arrays.asList(ids.split(","));
        //遍历遍历进行删除
        for(String id:list){
            noticeService.delete(Long.parseLong(id));
        }
        return R.ok();
    }

    @ApiOperation(value = "更新")
    @PostMapping("/edit")
    public R update(@RequestBody Notice notice){
        int num = noticeService.updateData(notice);
        if (num > 0) {
            return R.ok();
        }
        return R.fail("修改失败");

    }

    @GetMapping("/findAllNotice")
    public JsonObject findAllNotice(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "15") Integer limit) {
        // 查找全局数据。
        PageInfo<Notice> pageInfo =  noticeService.findNoticeAll(page, limit);

        // 将查询出来的数据返回到前端。
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }


}
