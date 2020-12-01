package com.yanzhen.service.impl;

import com.yanzhen.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: xhh
 * @Date: 2020/11/28 20:42
 * @Version 1.0
 */
@Service
public class MailServiceImpl  {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Async
    public void sendHtmlMail(String to, String subject, String content, String... cc)  {


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        try {
            messageHelper.setSubject(subject);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setText(content, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(messageHelper.getMimeMessage());



    }
}
