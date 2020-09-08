package com.example.qos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.qos.mapper")
@SpringBootApplication
class TosApplication {

    public static void main(String[] args) {
        SpringApplication.run(TosApplication.class, args);
    }

}
