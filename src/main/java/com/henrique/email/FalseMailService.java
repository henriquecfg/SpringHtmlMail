package com.henrique.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;


public class FalseMailService extends AbstractMailService {

    private static final Logger LOG = LoggerFactory.getLogger(FalseMailService.class);

    @Override
    public void sendMail(SimpleMailMessage msg){
        LOG.info("Simulating mail .....");
        LOG.info(msg.toString());
        LOG.info("Mail sent");
    }

    @Override
    public void sendHtmlMail(MimeMessage msg){
        LOG.info("Simulating mail .....");
        LOG.info(msg.toString());
        LOG.info("Mail sent");
    }

}
