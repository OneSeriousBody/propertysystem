package com.yanzhen.controller;

import com.alipay.api.internal.util.AlipaySignature;
import com.yanzhen.config.AlipayConfig;
import com.yanzhen.service.AlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: xhh
 * @Date: 2020/11/27 12:20
 * @Version 1.0
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    AlipayService alipayService;


    @GetMapping("/goPay")
    public void payMent(HttpServletResponse response, HttpServletRequest request, Integer id) {
        try {
            alipayService.carChargePay(response,request, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/alipayReturnNotice")
    @ResponseBody
    public String alipayReturnNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {



        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }

            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        ModelAndView mv = new ModelAndView("/login");

        if(signVerified) {

            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");


            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");


            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");


//            orderService.updateStatus(out_trade_no);

            mv.addObject("out_trade_no", out_trade_no);
            mv.addObject("oid", "1111");
            mv.addObject("trade_no", trade_no);
            mv.addObject("total_amount", total_amount);
            mv.addObject("productName", "精品");

        }else {
            log.info("支付, 验签失败...");
        }
        response.sendRedirect(request.getContextPath()+"/index.html");

        return "success";
    }


    @RequestMapping(value = "/alipayNotifyNotice")
    @ResponseBody
    public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

        log.info("支付成功, 进入异步通知接口...");


        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }

            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名


        if(signVerified) {//验证成功

            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){

            }else if (trade_status.equals("TRADE_SUCCESS")){

                System.out.println("nihaohoasjjsos");
//                orderService.updateStatus(out_trade_no);
//
//                Order order = orderService.queryByCode(out_trade_no);

            }
            log.info("支付成功...");
        }else {//验证失败
            log.info("支付, 验签失败...");
        }
        return "success";
    }

}
