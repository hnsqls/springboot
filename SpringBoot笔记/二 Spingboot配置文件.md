# SpringBoot项目的配置文件

# 一 统一配置概述

​	springboot项目的所有有关配置的属性(端口号、项目根路径、数据库连接信息等等)都在到一个固定位置和命名的配置文件（application.properties或application.yml）中！

功能配置参数说明：

[https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties)

总结：

- 集中式管理配置。统一在一个文件完成程序功能参数设置和自定义参数声明 。
- 位置：resources文件夹下，必须命名application  后缀 .properties / .yaml /  .yml 。
- 如果同时存在application.properties | application.yml(.yaml) , properties的优先级更高。



# 二 属性配置文件的使用

1. 创建

位置 resource/applicaiton.properties 

2. 编写

```.properties
# application.properties 为统一配置文件
# 内部包含: 固定功能的key,自定义的key
# 此处的配置信息,我们都可以在程序中@Value等注解读取

# 固定的key
# 启动端口号
server.port=80 

# 自定义
spring.jdbc.datasource.driverClassName=com.mysql.cj.jdbc.driver
spring.jdbc.datasource.url=jdbc:mysql:///springboot_01
spring.jdbc.datasource.username=root
spring.jdbc.datasource.password=root
```

固定的key的规定：[https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties)

自定义的key就是使用@Value（"${xxx}"） 来取值。

1. 读取配置文件

```java
package com.atguigu.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataSourceProperties {

    @Value("${spring.jdbc.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.jdbc.datasource.url}")
    private String url;

    @Value("${spring.jdbc.datasource.username}")
    private String username;

    @Value("${spring.jdbc.datasource.password}")
    private String password;

    // 生成get set 和 toString方法
    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DataSourceProperties{" +
                "driverClassName='" + driverClassName + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
```

这种配置文件的方式是key=value。key是唯一的。为了避免key重复，这种配置文件推荐使用多层命名方法命名key。

# 三 Yaml配置文件的使用

**语法说明**

1. 数据结构用树形结构呈现，通过缩进来表示层级，
2. 连续的项目（集合）通过减号 ” - ” 来表示
3. 键值结构里面的key/value对用冒号 ” : ” 来分隔。
4. YAML配置文件的扩展名是yaml 或 yml

```yaml
# Yaml文件，有层次，可继承，易读，支持复杂类型
server:
  port: 80
  servlet:
    context-path: /boot


# 集合
ls:
  name: hnsqls
  age: 18
  techno:
    - Java
    - C++
    - SSM
    - Springboot
```

# 四 **读取yaml 文件数据**

方式一 ： @Value（“${xxx}“）xxx是继承后的完整的key。而且还只能注入单个数据，List数据就不能读入。

方式二：批量配置读取：@ConfigurationProperties(prefix="前缀") 通用的前缀。但是只能匹配前缀后的一层。

```java
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
```



# 五 多环境配置和使用

1. 需求

    在Spring Boot中，可以使用多环境配置来根据不同的运行环境（如开发、测试、生产）加载不同的配置。SpringBoot支持多环境配置让应用程序在不同的环境中使用不同的配置参数，例如数据库连接信息、日志级别、缓存配置等。

    以下是实现Spring Boot多环境配置的常见方法：

    1. 属性文件分离：将应用程序的配置参数分离到不同的属性文件中，每个环境对应一个属性文件。例如，可以创建`application-dev.properties`、`application-prod.properties`和`application-test.properties`等文件。在这些文件中，可以定义各自环境的配置参数，如数据库连接信息、端口号等。然后，在`application.properties`中通过`spring.profiles.active`属性指定当前使用的环境。Spring Boot会根据该属性来加载对应环境的属性文件，覆盖默认的配置。
    2. YAML配置文件：与属性文件类似，可以将配置参数分离到不同的YAML文件中，每个环境对应一个文件。例如，可以创建`application-dev.yml`、`application-prod.yml`和`application-test.yml`等文件。在这些文件中，可以使用YAML语法定义各自环境的配置参数。同样，通过`spring.profiles.active`属性指定当前的环境，Spring Boot会加载相应的YAML文件。
    3. 命令行参数(动态)：可以通过命令行参数来指定当前的环境。例如，可以使用`--spring.profiles.active=dev`来指定使用开发环境的配置。

    通过上述方法，Spring Boot会根据当前指定的环境来加载相应的配置文件或参数，从而实现多环境配置。这样可以简化在不同环境之间的配置切换，并且确保应用程序在不同环境中具有正确的配置。
    
2. 多环境配置（基于方式b实践）

    > 创建开发、测试、生产三个环境的配置文件

    application-dev.yml（开发）

```YAML
spring:
  jdbc:
    datasource:
      driverClassName: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql:///dev
      username: root
      password: root
```

application-test.yml（测试）

```YAML
spring:
  jdbc:
    datasource:
      driverClassName: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql:///test
      username: root
      password: root
```

application-prod.yml（生产）

```YAML
spring:
  jdbc:
    datasource:
      driverClassName: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql:///prod
      username: root
      password: root
```
2. 环境激活

```YAML
spring:
  profiles:
    active: dev
```

**注意 :**

如果设置了spring.profiles.active，并且和application有重叠属性，以active设置优先。

如果设置了spring.profiles.active，和application无重叠属性，application设置依然生效！

