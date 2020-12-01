package com.yanzhen.service;

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
}