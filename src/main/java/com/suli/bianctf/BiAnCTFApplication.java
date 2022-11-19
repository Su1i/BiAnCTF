package com.suli.bianctf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.suli.bianctf.mapper")
public class BiAnCTFApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiAnCTFApplication.class, args);
    }

}
