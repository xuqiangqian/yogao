package com.yogo.fm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@EnableTransactionManagement
@SpringBootApplication
@RestController
public class YogoAppApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(YogoAppApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(YogoAppApplication.class);
    }

    @RequestMapping("/")
    public String test(){
        return "test";
    }
}
