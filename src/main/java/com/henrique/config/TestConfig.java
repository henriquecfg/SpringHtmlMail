package com.henrique.config;


import com.henrique.email.FalseMailService;
import com.henrique.email.MailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public MailService mailService(){
        return new FalseMailService();
    }
}
