package com.coderwzt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderwzt.model.Broadband;
import com.coderwzt.model.Parking;
import com.coderwzt.service.impl.BroadbandServiceImpl;
import com.coderwzt.service.impl.MailServiceImpl;
import com.coderwzt.service.impl.ParkingServiceImpl;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

@SpringBootTest
class PropertysystemApplicationTests {

    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private BroadbandServiceImpl broadbandService;

    @Autowired
    private ParkingServiceImpl parkingService;

    @Test
    void contextLoads() throws MessagingException {
//
        mailService.sendHtmlMail("714617106@qq.com", "hello" ,"您还有一项费用代缴，请点击链接进行缴费，<a href=\"https://xuhehe.com\">链接</a>，如果已缴费，请忽略该邮件", null );
    }


    @Test
    public void test() {
        Parking parking = parkingService.getById(4);
        parking.setStatus(0);
        parking.setOwnerId(null);
        parkingService.updateById(parking);
//        QueryWrapper<Broadband> queryWrapper = new QueryWrapper<>();
//        Date date = DateUtils.addDays(new Date(), -1);
//        date = DateUtils.addMonths(date, +2);
//        String format = DateFormatUtils.format(date, "yyyy-MM-dd");
//        // 查询出即将过期且需要自动续费的宽带列表
//        List<Broadband> list = broadbandService.list(queryWrapper.eq("flag", 1).eq("over_time", format));
//        System.out.println(list.size());
    }

}
