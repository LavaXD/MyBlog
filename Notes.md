# MyBlog Notes (from 0 --> 1)

 - **Introduction**: It is a blog project with two front-end systems who are responsible for the blog page (frontstage system), management page (backstage system). There are two corresponding systems at the back end, which are the specific implementations of the two front-end systems. 
 - **Techniques**: SpringBoot, MybatisPlus, Spring Security, EasyExcel, Swagger2, redis, Echarts, Vue, ElementUI.
 - **Multi-modules development**: There are common codes when developing two frontend systems. As a result, considering the reusability of code and developing efficiency, there are 3 submodules in the project - frontstage module, backstage module and shared module.
 - **About frontend code**: Since it is my first authentic project to practise real-life development process of a industrial-grade java project, my focus will be on backend development and its interaction with frontend. The frontend code is given and will be directly used in the project.

## Preparation 

 - **Developing tool**: IDEA
 - **Database**: MySQL
 - **Module Mngmt**: Maven 3.9.3

## 1. Project Construction

#### 1.1 Creating parent module
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


#### 1.2 Creating submodules
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


#### 1.3 Database preparation 
##### 1.3.1 application.yml
- Creating application.yml under resources package under Frontstage Module

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

##### 1.3.2 Test Connection 

![image](https://github.com/LavaXD/MyBlog/assets/103249988/849d460c-7c9b-436d-be77-b88fc5abb5d8)

##### 1.3.3 Run SQL script files 

![image](https://github.com/LavaXD/MyBlog/assets/103249988/50be3461-113d-4d92-b430-97d34b2fa436)

#### 1.4 Code preparation
>focus on one table - "article" - to test connection 

![image](https://github.com/LavaXD/MyBlog/assets/103249988/4574dd1f-6f13-4847-bdde-7d287d91ec91)

##### 1.4.1 Frontstage module
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

##### 1.4.2 Shared module 
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

#### 1.5 Milestone Test (MyBatisPlus)
- Run the launch class "BlogApplication" in Frontstage module to test connection

![image](https://github.com/LavaXD/MyBlog/assets/103249988/e144eb43-7b40-428d-b6d8-757d595cd283)

- Input the following web link to test connection
> http://localhost:7777/article/list

- Result from browser indicate connection successful
![image](https://github.com/LavaXD/MyBlog/assets/103249988/bc083dd6-0670-49f1-9575-1e83c45ce195)

## 2. Hot article list 

#### 2.1 Requirement analysis 
- The function I am focusing here is the hot article list in a article page
- I need to find information about the top 10 most viewed articles and show the title of the article and the number of views. Users should be allowed to click to jump to specific article details for browsing.
- Note: Draft and deleted articles can not be shown. Sort in descending order by the number of views

#### 2.2 Interface Design
#### 2.3 Unify response format 
- Code preparation
- In the shared module, create AppHttpCodeEnum class for further usage by frontstage and backstage. Its function is to encapsulate "code" and "message"

![image](https://github.com/LavaXD/MyBlog/assets/103249988/ef30e750-57a3-49f7-9cac-236ffcbba998)


```java
package com.js.enums;
public enum AppHttpCodeEnum {
    // success
    SUCCESS(200,"operation success"),
    // login
    NEED_LOGIN(401,"require login"),
    NO_OPERATOR_AUTH(403,"no operation authority"),
    SYSTEM_ERROR(500,"system error"),
    USERNAME_EXIST(501,"username already existed"),
    PHONENUMBER_EXIST(502,"phone number already existed"), EMAIL_EXIST(503, "email already existed"),
    REQUIRE_USERNAME(504, "require username"),
    LOGIN_ERROR(505,"The user name or password is incorrect");
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
```

- In the shared module, create _ResponseResult_ class as the unified response format class

![image](https://github.com/LavaXD/MyBlog/assets/103249988/54ef5703-71ad-4fca-a2dc-a283805ebc7c)

```java
package com.js.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.js.enums.AppHttpCodeEnum;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public ResponseResult(){
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
    }

    public ResponseResult(Integer code, T data){
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, T data, String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public ResponseResult(String msg, Integer code){
        this.code = code;
        this.msg = msg;
    }

    public static ResponseResult errorResult(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.error(code, msg);
    }
    public static ResponseResult okResult() {
        ResponseResult result = new ResponseResult();
        return result;
    }
    public static ResponseResult okResult(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.ok(code, null, msg);
    }

    public static ResponseResult okResult(Object data) {
        ResponseResult result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getMsg());
        if(data!=null) {
            result.setData(data);
        }
        return result;
    }

    public static ResponseResult errorResult(AppHttpCodeEnum enums){
        return setAppHttpCodeEnum(enums,enums.getMsg());
    }

    public static ResponseResult errorResult(AppHttpCodeEnum enums, String msg){
        return setAppHttpCodeEnum(enums,msg);
    }

    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums){
        return okResult(enums.getCode(),enums.getMsg());
    }

    private static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums, String msg){
        return okResult(enums.getCode(),msg);
    }

    public ResponseResult<?> error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }

    public ResponseResult<?> ok(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
```

#### 2.4 Basic Code Implementation

- Create hotArticleList function in ArticleController class, and use a corresponding function in ArticleService to return a ResponseResult
_ArticleController_

![image](https://github.com/LavaXD/MyBlog/assets/103249988/fabe6d11-7f37-4c97-ae19-c38df1c1e5af)

```java
 @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        //inquire hot article list, return responseResult
        ResponseResult result = articleService.hotArticleList(); // use a function in service
        return result;
    }
```

- Create function in ArticleService and its implementation in ArticleServiceImpl

_ArticleService_
```java
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();
}
```

_ArticleServiceImpl_

```java
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {
    @Override
    //inquire hot articles, return ResponseResult
    public ResponseResult hotArticleList() {

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //it has to be a completed, formal article
        queryWrapper.eq(Article::getStatus,0);

        //order by view count
        queryWrapper.orderByDsc(Article::getViewCount);

        //max hot articles amount: 10
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();

        return ResponseResult.okResult(articles);
    }
}
```

#### 2.5 Test unified response result
Input the following web link to test
> http://localhost:7777/article/hotArticleList

![image](https://github.com/LavaXD/MyBlog/assets/103249988/4eaa7a2f-b657-4eb5-a130-1319b3d655f2)

Test successful.

#### 2.6 Cross-origin issue (CORS)
- Create WebConfig class to solve cross-domain issue with following code under shared module

```java
package com.js.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        // config cross-domain path
        registry.addMapping("/**")
                // allowed request pattern
                .allowedOriginPatterns("*")
                // allow cookie or no
                .allowCredentials(true)
                // allowed request method
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // allowed header
                .allowedHeaders("*")
                // allowed time
                .maxAge(3600);
    }
}
```

#### 2.7 Initialize frontend project 
1. Download Node.js into the computer
> https://cowtransfer.com/s/d76a70e4b02c4f

2. Create a new folder and download the frontend code using the following link
>https://cowtransfer.com/s/6a767bb5059c4d

![image](https://github.com/LavaXD/MyBlog/assets/103249988/bee49fc7-f11a-4558-9cf8-d92020efb48f)

3. Open cmd as administrator,use the following command to run the frontend project

```xml
d:
cd /SpringBootBlogWeb/js-blog-vue
npm install
npm run dev
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/32fe5f4d-2c06-4f6c-9598-4cfff5001a8e)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/ea55b661-5c64-4299-aacd-c7ed482dfbc7)

4. Access to frontend project

>http://localhost:8080/#/DetailArticle?aid=1

![image](https://github.com/LavaXD/MyBlog/assets/103249988/3c2431e4-8488-4612-a40e-58367f7fb197)

5. Coordination between frontend and backend 

![image](https://github.com/LavaXD/MyBlog/assets/103249988/a9b797f5-c79d-4bdc-be2d-21040c305bf5)

#### 2.7 VO Optimization
- Reason: when return a list of 10 articles as hot articles from database to frontstage web page, all columns/attributes of an article are returned, which is not neccessary for a request like hot article list.
- Expected: use a new article class to encapsulate data that frontstage content needs, ignore those it does not need
1. create HotArticleVo under domain package of shared module

![image](https://github.com/LavaXD/MyBlog/assets/103249988/01521020-6756-4083-a59e-c548f27813b1)

```java
package com.js.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo {

    private Long id;
    private String title;
    private Long viewCount;
}
```

2. Modify the return value of ResponseReult, use bean copy to change article to articleVo that encapsulate certain attributes (id, title, viewcount)
```java
 //bean copy
        List<HotArticleVo> articleVos =new ArrayList<>();
        for(Article article:articles){
            HotArticleVo articleVo = new HotArticleVo();
            BeanUtils.copyProperties(article,articleVo);
            articleVos.add(articleVo);
        }

        return ResponseResult.okResult(articleVos);
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/b477b6f2-11e0-4a68-959a-6143cc2c722a)

#### 2.8 Constant management
- Reason: It is not allowed in real projects to use literal value, constant definition is required to enhance the maintainability of the project.

1. Create SystemConstants class to manage constants in a centralized way.

```java
package com.js.constants;

public class SystemConstants {

    //article is script
    public static final int ARTICLE_STATUS_DRAFT = 1;

    //article is completed
    public static final int ARTICLE_STATUS_NORMAL = 0;
}
```

2. Directly use constant definition to indicate completed article

![image](https://github.com/LavaXD/MyBlog/assets/103249988/18eaada0-ae9e-489a-828f-4d091face773)

## 3. Bean Copy Util 
- Bean copy will be universally used when it comes to interface implementations. As a result, encapsulate the bean copy method into a class for further usage.

- Create a BeanCopyUtil class under Utils package of shared module
  
![image](https://github.com/LavaXD/MyBlog/assets/103249988/5d55b8d9-7879-48f2-acc0-280911c5d6eb)


```java
package com.js.Utils;

import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtil {

    private BeanCopyUtil(){

    }

    //Application of Generics
    public static <V> V copyBean(Object source, Class<V> clas){

        V result = null;

        try {

            //create targeted instance using class
            result = clas.newInstance();

            //realize attributes copy
            BeanUtils.copyProperties(source,result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //return result
        return result;
    }

    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clas){
        return list.stream()
                .map(o -> copyBean(o,clas))
                .collect(Collectors.toList());

    }
}
```

- Modify the copy bean proccess in ArticleServiceImpl, use the CopyBeanList method created

  ![image](https://github.com/LavaXD/MyBlog/assets/103249988/81b15246-728b-4d3c-bed9-b12272871a7f)

  




