package com.yogo.fm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-18
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
@MapperScan(value = {"com.yogo.fm.mapper.system","com.yogo.fm.mapper.operation"})
public class YogoAdminApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(YogoAdminApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(YogoAdminApplication.class);
    }
}
