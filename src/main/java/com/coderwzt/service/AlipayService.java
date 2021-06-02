package com.coderwzt.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AlipayService {

   /**
    * 车位费缴费
    * @param response
    * @param request
    * @param id
    * @throws IOException
    */
   void  carChargePay(HttpServletResponse response, HttpServletRequest request, Integer id) throws IOException;

   /**
    * 支付宽带费
    * @param response
    * @param request
    * @param id
    */
   void payBroadband(HttpServletResponse response, HttpServletRequest request, Integer id);

   /**
    * 支付物业费
    * @param response
    * @param request
    * @param id
    */
   void payProperty(HttpServletResponse response, HttpServletRequest request, Integer id);

   /**
    * 支付维修费
    * @param response
    * @param request
    * @param id
    */
    void payRepair(HttpServletResponse response, HttpServletRequest request, Integer id);
}
