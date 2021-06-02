package com.coderwzt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@MapperScan("com.coderwzt.dao")
public class PropertysystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertysystemApplication.class, args);
    }

}
