package com.jx.tdmp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
@MapperScan("com.jx.tdmp.mapper")
public class TdmpApplication {
    @RequestMapping("/hello")
    public String index(){
        return "Hello World， Spring boot is good";
    }

    public static void main(String[] args) {

        SpringApplication.run(TdmpApplication.class, args);
    }

}
