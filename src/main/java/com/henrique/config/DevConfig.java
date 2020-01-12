package com.henrique.config;


import com.henrique.email.MailService;
import com.henrique.email.SmtpMailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public MailService mailService(){
        return new SmtpMailService();
    }
}
