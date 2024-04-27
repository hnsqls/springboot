package com.ls.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 读取配置数据的方式
 * 方式一： @Value
 *      作用在属性上 @Value{"${全key}"}
 *      缺点一：要写全key
 *      缺点二：只能注入简单类
 *  方式二：@ConfigurationProperties(prefix= "共同前缀")
 *       然后根据属性名相同赋值、
 *       可以赋值list集合
 */
@Data
@Component
@ConfigurationProperties(prefix = "ls.user")
public class User {
//    @Value("${ls.user.username}")
    private String username;
//    @Value("${ls.user.password}")
    private int age;
//    @Value("${ls.user.gfs}")
    private List<String> gfs;

}
