package com.ls;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ls.mapper")  //扫描mapper接口，配合配置类中mapper.xml生成代理类，并放入root容器
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}
