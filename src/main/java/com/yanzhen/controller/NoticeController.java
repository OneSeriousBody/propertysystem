package com.yanzhen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.yanzhen.model.Notice;
import com.yanzhen.model.Owner;
import com.yanzhen.model.PropertyType;
import com.yanzhen.service.INoticeService;
import com.yanzhen.service.IOwnerService;
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
        int num = noticeService.add(notice);
        if (num > 0) {
            return R.ok();
        }
        List<Owner> list = ownerService.list();
        list.forEach(owner -> {
            mailService.sendHtmlMail(owner.getEmail(), "新公告", "您还有一个新的公告未看，请点击链接前往官网观看，<a href="+ loginIndex +">链接</a>", null);
        });
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
        PageInfo<Notice> pageInfo =  noticeService.findPropertyTypeAll(page, limit);

        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Notice> findListByPage(@RequestParam Integer page,
                                        @RequestParam Integer pageCount){
        return noticeService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Notice findById(@PathVariable Long id){
        return noticeService.findById(id);
    }

}
