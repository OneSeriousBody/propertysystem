package com.yanzhen.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yanzhen.config.AlipayConfig;
import com.yanzhen.model.Broadband;
import com.yanzhen.model.Carcharge;
import com.yanzhen.model.Records;
import com.yanzhen.service.AlipayService;
import com.yanzhen.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@Service
@Slf4j
public class AlipayServiceImpl implements AlipayService {


    @Autowired
    private CarchargeServiceImpl carchargeService;

    @Autowired
    private BroadbandServiceImpl broadbandService;

    @Autowired
    private RecordsServiceImpl recordsService;


    @Override
    public void carChargePay(HttpServletResponse response, HttpServletRequest request, Integer id) throws IOException {
        //商户订单号，后台可以写一个工具类生成一个订单号，必填
//        String order_number = new String(UUID.randomUUID().toString());
        String orderNumber = id.toString() + "," + Constants.PAY_CAR;
        //通过id获取停车费的实体类.
        Carcharge carcharge = carchargeService.findById(id.longValue());
        // 设置订单金额
        String total_amount = String.valueOf(carcharge.getMoney());
        //订单名称，必填
        String subject = new String("支付车位费");
        String bizContent = "{\"out_trade_no\":\"" + orderNumber + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"id\":\"" + id + "\","
                + "\"flag\":\"" + Constants.PAY_CAR+ "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";
        goPay(response, request, bizContent);
    }

    @Override
    public void payBroadband(HttpServletResponse response, HttpServletRequest request, Integer id) {
        //商户订单号，后台可以写一个工具类生成一个订单号，必填
        String orderNumber = id.toString() + "," + Constants.PAY_BROADBAND;
        //付款金额，从前台获取，必填
        Broadband broadband = broadbandService.getById(id);
        String total_amount = String.valueOf(broadband.getPrice());
        //订单名称，必填
        String subject = new String("支付宽带费");
        // 设置订单的一些基本属性
        String bizContent = "{\"out_trade_no\":\"" + orderNumber + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"id\":\"" + id + "\","
                + "\"flag\":\"" + Constants.PAY_BROADBAND+ "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";
        goPay(response, request, bizContent);
    }

    @Override
    public void payProperty(HttpServletResponse response, HttpServletRequest request, Integer id) {
        //商户订单号，后台可以写一个工具类生成一个订单号，必填
        String orderNumber = id.toString() + "," + Constants.PAY_PROPERTY;
        //付款金额，从前台获取，必填
        Records records = recordsService.getById(id);
        String total_amount = String.valueOf(records.getPrice());
        //订单名称，必填
        String subject = new String("支付水电费");
        String bizContent = "{\"out_trade_no\":\"" + orderNumber + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"id\":\"" + id + "\","
                + "\"flag\":\"" + Constants.PAY_PROPERTY+ "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";
        goPay(response, request, bizContent);
    }

    public void goPay(HttpServletResponse response, HttpServletRequest request, String bizContent){
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        aliPayRequest.setReturnUrl(AlipayConfig.return_url);
        aliPayRequest.setBizContent(bizContent);

        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(aliPayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //输出,跳转到支付宝支付页面.
        out.println(result);
    }




}