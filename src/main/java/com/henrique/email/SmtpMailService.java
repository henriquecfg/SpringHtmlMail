package com.henrique.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpMailService extends AbstractMailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(SimpleMailMessage msg){
        mailSender.send(msg);
    }

    @Override
    public void sendHtmlMail(MimeMessage msg){
        javaMailSender.send(msg);
    }
}
