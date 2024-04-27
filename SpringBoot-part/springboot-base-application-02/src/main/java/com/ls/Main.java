package com.ls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//1.配置类，2.自动导入其他配置类。3.扫包注解生效的范围
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}
