package com.lijie.finalproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lijie.finalproject.")
public class J2eeFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(J2eeFinalProjectApplication.class, args);
    }

}
