# MyBlog Notes (from 0 --> 1)

 - **Introduction**: It is a blog project with two front-end systems who are responsible for the blog page (frontstage system), management page (backstage system). There are two corresponding systems at the back end, which are the specific implementations of the two front-end systems. 
 - **Techniques**: SpringBoot, MybatisPlus, Spring Security, EasyExcel, Swagger2, redis, Echarts, Vue, ElementUI.
 - **Multi-modules development**: There are common codes when developing two frontend systems. As a result, considering the reusability of code and developing efficiency, there are 3 submodules in the project - frontstage module, backstage module and shared module.
 - **About frontend code**: Since it is my first authentic project to practise real-life development process of a industrial-grade java project, my focus will be on backend development and its interaction with frontend. The frontend code is given and will be directly used in the project.

## Preparation 

 - **Developing tool**: IDEA
 - **Database**: MySQL
 - **Module Mngmt**: Maven 3.9.3

## Project Construction

#### 1. Creating parent module
- Creating new Maven project "MyBlog" in IDEA, and put it  in D disk .
- Modify maven configuration

*Parent Module*

![image](https://github.com/LavaXD/MyBlog/assets/103249988/79657bb4-a0ed-4783-b17d-fe9db44a042d)

*Parent Module Pom file configuration*

```xml
<?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.js</groupId>
    <artifactId>MyBlog</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>

    <modules>
        <module>Shared</module>
        <module>BackStage</module>
        <module>FrontStage</module>
    </modules>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>1.8</java.version>
    </properties>


    <!--version control of dependencies-->
    <dependencyManagement>
        <dependencies>

            <!-- SpringBoot dependency conf-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.5.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!--fastjson-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>1.2.33</version>
            </dependency>

            <!--jwt-->
            <dependency>
                <groupId>io.jsonwebtoken</groupId>
                <artifactId>jjwt</artifactId>
                <version>0.9.0</version>
            </dependency>

            <!--mybatisPlus-->
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>3.4.3</version>
            </dependency>

            <!--ali cloud OSS-->
            <dependency>
                <groupId>com.aliyun.oss</groupId>
                <artifactId>aliyun-sdk-oss</artifactId>
                <version>3.10.2</version>
            </dependency>

            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>easyexcel</artifactId>
                <version>3.0.5</version>
            </dependency>

            <dependency>
                <groupId>io.springfox</groupId>
                <artifactId>springfox-swagger2</artifactId>
                <version>2.9.2</version>
            </dependency>
            <dependency>
                <groupId>io.springfox</groupId>
                <artifactId>springfox-swagger-ui</artifactId>
                <version>2.9.2</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <!--maven version-->
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <!--jdk version-->
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                    <encoding>${project.build.sourceEncoding}</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>
    </project>
```


#### 2. Creating submodules
*Three Submodules*

![image](https://github.com/LavaXD/MyBlog/assets/103249988/cd7a1bc3-4a5a-4efb-b2f0-d886eee44ab9)

*Shared Pom file*

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>MyBlog</artifactId>
        <groupId>com.js</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>Shared</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <!--junit-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!--SpringSecurity-->
        <!--<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>-->
        <!--redis-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <!--fastjson-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
        </dependency>
        <!--jwt-->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
        </dependency>
        <!--mybatisPlus-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
        </dependency>
        <!--mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <!--ali cloud OSS-->
        <dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-sdk-oss</artifactId>
        </dependency>

        <!--AOP-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>easyexcel</artifactId>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
        </dependency>

    </dependencies>
</project>
```

*Frontstage & Backstage Pom Files*

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>MyBlog</artifactId>
        <groupId>com.js</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>FrontStage</artifactId>

    <!--using dependencies from shared module-->
    <dependencies>
        <dependency>
            <groupId>com.js</groupId>
            <artifactId>Shared</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

</project>
```

## EasyCode Plugin


## Database praparation 
##### 1. Creating application.yml under resources package under Frontstage Module

![image](https://github.com/LavaXD/MyBlog/assets/103249988/7ca1a705-d3c1-4487-849d-e7116c8be5c2)

*JDBC connection code - MyBatisPlus integration*
```yml
server:
  port: 7777

spring:
  #DB connection conf
  datasource:
    url: jdbc:mysql://localhost:3306/sg_blog?characterEncoding=utf-8
    username: root
    password: zhongqing922
    driver-class-name: com.mysql.cj.jdbc.Driver

  servlet:
    #file uploading
    multipart:
      #max size of single file
      max-file-size: 20MB
      #total size of all the files in HTTP request
      max-request-size: 20MB

mybatis-plus:
  configuration:
    #log: for printing log
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      logic-delete-field: delFlag
      logic-delete-value: 1
      logic-not-delete-value: 0
      id-type: auto
```

##### 2. Test Connection 

![image](https://github.com/LavaXD/MyBlog/assets/103249988/849d460c-7c9b-436d-be77-b88fc5abb5d8)

##### 3. Run SQL script files 

![image](https://github.com/LavaXD/MyBlog/assets/103249988/50be3461-113d-4d92-b430-97d34b2fa436)

## Code praparation
>focus on one table - "article" - to test connection 

![image](https://github.com/LavaXD/MyBlog/assets/103249988/4574dd1f-6f13-4847-bdde-7d287d91ec91)

##### 1. Frontstage module
- Launch class

![image](https://github.com/LavaXD/MyBlog/assets/103249988/568ac692-f131-4671-b6da-f39628c4c387)

```java
package com.js;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.js.mapper")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
    }

}
```

- Controller for article table (for connection test)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/a2f3aac9-8a2a-4435-867a-067295bb7496)

```java
package com.js.controller;

import com.js.domain.entity.Article;
import com.js.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    //ArticleService interface from shared module
    private ArticleService articleService;

    @GetMapping("/list")
    //Article is the entity class from shared module
    public List<Article> test(){
        //inquire all the data from DB
        return articleService.list();
    }
}

```

##### 2. Shared module 
- Create article entity class, mapper for article, service and its implementation for article 

*Article entity class - using EasyCode to generate*

```java
package com.js.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Article table entity
 *
 * @author js
 * @since 2023-09-26 21:54:05
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_article")
public class Article extends Model<Article> {

    @TableId
    private Long id;

    private String title;

    private String content;

    private String summary;

    private Long categoryId;

    private String thumbnail;

    private String isTop;

    private String status;

    private Long viewCount;

    private String isComment;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    
    private Integer delFlag;
}
```

*Article Mapper*
```java
package com.js.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.js.domain.entity.Article;

public interface ArticleMapper extends BaseMapper<Article> {
}
```

*Article Service*

```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.entity.Article;

public interface ArticleService extends IService<Article> {
}
```

*ArticleServiceImpl*

```java
package com.js.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.domain.entity.Article;
import com.js.mapper.ArticleMapper;
import com.js.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {
}
```

## Milestone Test
- Run the launch class "BlogApplication" in Frontstage module to test connection

![image](https://github.com/LavaXD/MyBlog/assets/103249988/e144eb43-7b40-428d-b6d8-757d595cd283)

- Input the following web link to test connection
> http://localhost:7777/article/list

- Result from browser indicate connection successful
![image](https://github.com/LavaXD/MyBlog/assets/103249988/bc083dd6-0670-49f1-9575-1e83c45ce195)
