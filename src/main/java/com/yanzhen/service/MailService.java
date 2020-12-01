package com.yanzhen.service;

import javax.mail.MessagingException;

/**
 * @Author: xhh
 * @Date: 2020/11/28 20:41
 * @Version 1.0
 */
public interface MailService {

    /**
     * 发送HTML邮件
     *
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param cc      抄送地址
     * @throws MessagingException 邮件发送异常
     */
    void sendHtmlMail(String to, String subject, String content, String... cc) throws MessagingException;

}
