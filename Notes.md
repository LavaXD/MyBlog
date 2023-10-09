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

## 2. Blog FrontStage - Hot article list 

#### 2.1 Requirement analysis 
- The function I am focusing here is the hot article list in a article page
- I need to find information about the top 10 most viewed articles and show the title of the article and the number of views. Users should be allowed to click to jump to specific article details for browsing.
- Note: Draft and deleted articles can not be shown. Sort in descending order by the number of views

#### 2.2 Interface Design
- Consistent with interface that have been desigend in frontend development

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

#### 2.9. Bean Copy Util 
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

  
## 3. Blog FrontStage - Category Listings 
#### 3.1 Requirement analysis
- There is a category tab in the mian page, user can view all the articles in a certain category by clicking corresponding category
- Only exhibit the category that has at least one article
- Only exhibit the articles that are in normal status (completed)
#### 3.2 Interface design
- Consistent with interface that have been desigend in frontend development

![image](https://github.com/LavaXD/MyBlog/assets/103249988/ca0b9886-8f6a-47b3-9efd-a7c92c020768)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/685c5bd6-f3f4-45bb-8768-588740c7a2f2)

#### 3.3 Code implementation 

- Create category entity, service, serviceImpl, dao (mapper) under shared module 

![image](https://github.com/LavaXD/MyBlog/assets/103249988/715a67ee-8149-4e1f-9d6d-4d3a5c692a9c)

_Category entity_

```java
package com.js.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("category")
public class Category  {
    
@TableId
    private Long id;

    

    private String name;
    

    private Long pid;
    

    private String description;
    

    private String status;
    

    private Long createBy;
    

    private Date createTime;
    

    private Long updateBy;
    

    private Date updateTime;
    

    private Integer delFlag;
    
}
```

_Category service_

```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;


public interface CategoryService extends IService<com.js.domain.entity.Category> {

    ResponseResult getCategoryList();
}
```

_Category serviceImpl_

```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.Utils.BeanCopyUtil;
import com.js.constants.SystemConstants;
import com.js.domain.ResponseResult;
import com.js.domain.entity.Article;
import com.js.domain.vo.CategoryVo;
import com.js.mapper.CategoryMapper;
import com.js.service.ArticleService;
import com.js.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, com.js.domain.Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {

        //inquire article table and get articles with normal status
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);

        //get distinct category id
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());


        //inquire category table
        List<com.js.entity.Category> categories = listByIds(categoryIds);

        categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        //encapsulate vo
        List<CategoryVo> categoryVos = BeanCopyUtil.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }
}
```

_Category dao (mapper)_

```java
package com.js.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * (Category)data access layer
 *
 * @author js
 * @since 2023-10-02 19:41:57
 */
public interface CategoryMapper extends BaseMapper<com.js.domain.entity.Category> {

}
```

_Category vo_

```java
package com.js.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {

    private Long id;
    private String name;
}
```

- Create category controller under frontstage module

![image](https://github.com/LavaXD/MyBlog/assets/103249988/258d6de4-469f-4559-9822-da7992cbbcb3)

_Category controller_

```java
package com.js.controller;


import com.js.domain.ResponseResult;
import com.js.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();
    }

}
```

- Input the following link in brower according to controller, result indicate successful 
>http://localhost:7777/category/getCategoryList

![image](https://github.com/LavaXD/MyBlog/assets/103249988/690b7c31-0b1b-483b-a482-445c2e45caaa)


## 4. Blog FrontStage - Paging query 

#### 4.1 Requirement analysis
- Home page: Exhibit all articles 
- Category page: Exhibit corresponding articles under specific category 
- Only exhibit the articles whose status is normal (completed)
- Those articles that are pinned must be shown at the top
#### 4.2 Interface design
- Consistent with interface that have been desigend in frontend development

![image](https://github.com/LavaXD/MyBlog/assets/103249988/3da02424-7250-418b-b231-f0ab0dd948c8)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/4356875e-1374-4923-8fda-2a80f7b9622e)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/90ab1051-0cb9-4292-a588-1700c95befe5)

#### 4.3 Code implementation (detailed procedure)
1. In articleController class, create a function called articleList

2. Since controller class can not directly operate database, it calls a function from service class

![image](https://github.com/LavaXD/MyBlog/assets/103249988/ac3e8121-f5b8-4452-bd66-8aae6b3d987e)

- Create a ArticleList function with parameters in service class 

![image](https://github.com/LavaXD/MyBlog/assets/103249988/f26f30f4-3c11-4cad-9b2a-399708b6fadf)

3. Implement the "articleList" function in serviceImpl

![image](https://github.com/LavaXD/MyBlog/assets/103249988/312f1433-3b4a-470a-b30c-68efecf6cdf4)

4. In serviceImpl class, clearify the to-do list according to the requirement list

![image](https://github.com/LavaXD/MyBlog/assets/103249988/a5a69a5b-3881-4683-b438-a94d28f3fdfd)

5. Creating vo class for articleList
- when creating articleList vo class, its attributes should be the same as the interface design from frontend 

![image](https://github.com/LavaXD/MyBlog/assets/103249988/e8c35cf9-4af6-4af8-a17c-367cb0e2fdd9)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/8d0549fc-e26d-4d8e-94da-cf34fec5d7d5)

6. Creating vo class for page
- since there are two more attributes in the demand: rows, total
- So we need another vo class called pageVo

![image](https://github.com/LavaXD/MyBlog/assets/103249988/d3be232f-4c46-457d-a5c7-0a82266aa9e2)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/cea80260-c3e1-40f8-9c65-f111419eedc9)

7. Implement the coding logic one by one 

![image](https://github.com/LavaXD/MyBlog/assets/103249988/53f01031-91b2-4628-bfd8-f8b8e4cfa890)

8. Pagination inteceptor

![image](https://github.com/LavaXD/MyBlog/assets/103249988/70f4ad81-d577-4749-b858-3c69e9dc46e7)

9. Input the following web link to test
>http://localhost:7777/article/articleList?pageNum=1&pageSize=10

![image](https://github.com/LavaXD/MyBlog/assets/103249988/8beae168-ac14-45d7-b346-8ad407c9e20b)

>http://localhost:7777/article/articleList?pageNum=1&pageSize=10&categoryId=2

![image](https://github.com/LavaXD/MyBlog/assets/103249988/0d6ef7ce-1b8c-4741-8b6f-673d109eed46)

10. FastJason configuration (manage time frame)

```java
//----------------------------------------------------- FastJson Config ---------------------------------------------------
    @Bean//use @Bean import fastJsonHttpMessageConvert
    public HttpMessageConverter fastJsonHttpMessageConverters() {
        //1.define Convert instance
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        SerializeConfig.globalInstance.put(Long.class, ToStringSerializer.instance);

        fastJsonConfig.setSerializeConfig(SerializeConfig.globalInstance);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return converter;
    }

    @Override
    //config msg converter
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //add msg converter
        converters.add(fastJsonHttpMessageConverters());
    }
```
**Result**

![image](https://github.com/LavaXD/MyBlog/assets/103249988/049c8a6d-e9aa-4dbc-a184-65636740ab85)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/20729c68-0980-4a9f-b5a8-c74173da97d5)

## 5. Blog FrontStage - Detailed content of article

#### 5.1 Requirement analysis 

- When user click on button "Read more", the web page should jump to another page that shows the detailed content of the article
- Note: it is required to show the categoryName in the detailed content

#### 5.2 Interface design 
- Response format 
```json
{
  "code": 200,
  "data": {
    "categoryId": "1",
    "categoryName": "java",
    "content": "detailed article content",
    "createTime": "2022-01-23 23:20:11",
    "id": "1",
    "isComment": "0",
    "title": "java language",
    "viewCount": "114"
  },
  "msg": "operation success"
}
```

- HTTP request format
<table>
  <tr>
    <td>
   
   **Request Format** </td>
    <td>**Example**</td>
    <td>**Description**</td>
  </tr>
 <tr>
    <td>Path parameter form</td>
    <td>/articles/{id}</td>
    <td>Parameter itself as a part of the URL</td>
  </tr>
 <tr>
    <td>Query parameter form</td>
    <td>/articles?id=1&num=1</td>
    <td>With symbols like "?" "&" "=" in the URL to add filter conditions</td>
  </tr>
</table>
- In the following service requests, Path parameter form will be used 

#### 5.3 Code implementation 
1. ArticleController

```java
@GetMapping("/{id}")
    //Path parameter form is used here, so @PathVariable is used
    public ResponseResult getArticleDetail(@PathVariable("id")Long id){

        return articleService.getArticleDetail(id);
    }
```
2. ArticleDetailVo

```java
package com.js.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {

    private Long id;

    private String title;

    private String content;

    private String summary;

    private Long categoryId;

    private String categoryName;

    private String thumbnail;

    private Long viewCount;

    private Date createTime;


}
```
3. ArticleService & ServiceImpl

![image](https://github.com/LavaXD/MyBlog/assets/103249988/1e593653-12dc-4438-a345-79481aacbcda)

```java
//------------------------------------------------- ArticleDetail -------------------------------------------------------
    @Override
    public ResponseResult getArticleDetail(Long id) {

        //inquire article by articleId
        Article article = getById(id);

        //convert to vo
        ArticleDetailVo articleDetailVo = BeanCopyUtil.copyBean(article, ArticleDetailVo.class);

        //inquire categoryName by categoryId
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category != null){
            articleDetailVo.setCategoryName(category.getName());
        }

        //encapsulation and return
        return ResponseResult.okResult(articleDetailVo);
    }
```

4. Test

![image](https://github.com/LavaXD/MyBlog/assets/103249988/96a60300-ceec-4856-b893-2f851724bead)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/1b6d8940-e754-4499-8eec-d402412f7533)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/4b800894-f852-4dbb-9a9e-c673300dd0f4)

## 6. Blog FrontStage - Friend-link function
#### 6.1 Requirement analysis 
- Show friend link in friend-link page
- Approval is needed before a friend link is shown

#### 6.2 Interface design

![image](https://github.com/LavaXD/MyBlog/assets/103249988/f0cb0540-12c9-4caf-9e41-410e1bd5f9ca)

<table>
 <tr>
  <td>
   
   **Request form**
  </td>
  <td>
   
   **Request path**
  </td>
 </tr>
 <tr>
  <td>Get</td>
  <td>/link/GetAllLink</td>
 </tr>
</table>

```json
{
  "code": 200,
  "data": [
    {
      "address": "https://www.baidu.com",
      "description": "sda",
      "id": "1",
      "logo": "picture url1",
      "name": "sda"
    },
    {
      "address": "https://www.qq.com",
      "description": "dada",
      "id": "2",
      "logo": "picture url2",
      "name": "sda"
    }
  ],
  "msg": "operation success"
}
```

#### 6.3 Code implementation
1. Create _LinkController_

```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/getAllLink")
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }
}
```

2. Create _LinkService_

```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.entity.Link;


public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
```

3. Create _LinkEntity_

```java
package com.js.domain.entity;


import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("link")
public class Link  {
    
@TableId
    private Long id;


    private String name;
    

    private String logo;
    

    private String description;
    

    private String address;
    

    private String status;
    

    private Long createBy;
    

    private Date createTime;
    

    private Long updateBy;
    

    private Date updateTime;
    

    private Integer delFlag;
    
}
```


4. Create _LinkVo_

```java
package com.js.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkVo {

    private Long id;


    private String name;


    private String logo;


    private String description;


    private String address;

}
```


5. Create _LinkServiceImpl_

```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.Utils.BeanCopyUtil;
import com.js.constants.SystemConstants;
import com.js.domain.ResponseResult;
import com.js.domain.entity.Link;
import com.js.domain.vo.LinkVo;
import com.js.mapper.LinkMapper;
import com.js.service.LinkService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {

        //inquire all the friend link that have been approved
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);

        //convert to vo
        List<LinkVo> linkVos = BeanCopyUtil.copyBeanList(links, LinkVo.class);

        //encapsulation and return
        return ResponseResult.okResult(linkVos);
    }
}

```

6. Test

- Input the following links to test

>http://localhost:7777/link/getAllLink

![image](https://github.com/LavaXD/MyBlog/assets/103249988/1b2a619e-c09a-4844-84af-ed24438412ce)

>http://localhost:8080/#/Friendslink

![image](https://github.com/LavaXD/MyBlog/assets/103249988/d712a608-a2ac-4502-bdb7-2ce27c70b00a)

## 7. Blog FrontStage - Log in function

#### 7.1 Requirement analysis
- Given that the login function of both frontstage & backstage require certifiate anthority, so **SpringSecurity** is used to implement this function
- Some of the functions can only be used after logging in
  
![image](https://github.com/LavaXD/MyBlog/assets/103249988/59cdb749-a62a-483a-9bd2-5ac09caa7def)

#### 7.2 Interface design
<table>
 <tr>
  <td>
    
   **Request method**
  </td>
  <td>
   
   **Request path**
  </td>
 </tr>
 <tr>
  <td>
   POST
  </td>
  <td>
   /login
  </td>
 </tr>
</table>

- request body:

```json
{
    "userName":"sg",
    "password":"1234"
}
```
- response format

```json
{
    "code": 200,
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0ODBmOThmYmJkNmI0NjM0OWUyZjY2NTM0NGNjZWY2NSIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY0Mzg3NDMxNiwiZXhwIjoxNjQzOTYwNzE2fQ.ldLBUvNIxQCGemkCoMgT_0YsjsWndTg5tqfJb77pabk",
        "userInfo": {
            "avatar": "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F3bf9c263bc0f2ac5c3a7feb9e218d07475573ec8.gi",
            "email": "23412332@qq.com",
            "id": 1,
            "nickName": "sg333",
            "sex": "1"
        }
    },
    "msg": "operation success"
}
```

#### 7.3 Procedure analysis

- Log in
 1. define login interface
    - call _ProviderManager_'s function to cerfify, and generate **jwt** if certification successful
    - store user information into **redis**
 2. define _UserDetailsService_
     - inquire database in this serviceImpl
     - configure _passwordEncoder_ as _BCryptoPasswordEncoder_
- Validation
 1. define **jwt** cerfification filter
    - get token
    - get _userId_ by parsing token
    - get user information from redis
    - store _SecurityContextHolder_
   
#### 7.4 Preparation

- **Corresponding dependencies**

```xml
<!--For jaxb defaulf implementation that are removed in higher JDK versions-->
<dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.1</version>
        </dependency>
<!--SpringSecurity initializer-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!--redis-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
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
```

- **Utils and configurations**

- FastJsonRedisSerializer under shared module
```java
package com.js.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.util.Assert;
import java.nio.charset.Charset;

/**
 * Redis with FastJson Serializer
 * @author 35238
 * @date 2023/7/22 0022 21:12
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T>{

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    static
    {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public FastJsonRedisSerializer(Class<T> clazz)
    {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException
    {
        if (t == null)
        {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException
    {
        if (bytes == null || bytes.length <= 0)
        {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);

        return JSON.parseObject(str, clazz);
    }


    protected JavaType getJavaType(Class<?> clazz)
    {
        return TypeFactory.defaultInstance().constructType(clazz);
    }
}
```

- RedisConfig under shared module

```java
package com.js.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    @SuppressWarnings(value = { "unchecked", "rawtypes" })
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory)
    {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
```

- RedisCache under Utils

```java
package com.js.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 35238
 * @date 2023/7/22 0022 21:18
 */
@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class RedisCache {
    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value)
    {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit)
    {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout)
    {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit)
    {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean deleteObject(final String key)
    {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public long deleteObject(final Collection collection)
    {
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存List数据
     *
     * @param key 缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList)
    {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(final String key)
    {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set
     *
     * @param key 缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet)
    {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext())
        {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public <T> Set<T> getCacheSet(final String key)
    {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap)
    {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(final String key)
    {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value)
    {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey)
    {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 删除Hash中的数据
     *
     * @param key
     * @param hkey
     */
    public void delCacheMapValue(final String key, final String hkey)
    {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hkey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys)
    {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern)
    {
        return redisTemplate.keys(pattern);
    }
}
```
- WebUtils under Util

```java
package com.js.Utils;

import org.springframework.web.context.request.RequestContextHolder;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author 35238
 * @date 2023/7/22 0022 21:19
 */
public class WebUtils {
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string 待渲染的字符串
     * @return null
     */
    public static void renderString(HttpServletResponse response, String string) {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void setDownLoadHeader(String filename, ServletContext context, HttpServletResponse response) throws UnsupportedEncodingException {
        String mimeType = context.getMimeType(filename);//获取文件的mime类型
        response.setHeader("content-type",mimeType);
        String fname= URLEncoder.encode(filename,"UTF-8");
        response.setHeader("Content-disposition","attachment; filename="+fname);

//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setCharacterEncoding("utf-8");
    }
}
```
 
- JwtUtil under Util

```java
package com.js.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 * @author 35238
 * @date 2023/7/22 0022 21:18
 */
public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL = 72*60 * 60 *1000L;// 60 * 60 *1000  一个小时
    //设置秘钥明文
    public static final String JWT_KEY = "huanfqc";

    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("HF")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    public static void main(String[] args) throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjYWM2ZDVhZi1mNjVlLTQ0MDAtYjcxMi0zYWEwOGIyOTIwYjQiLCJzdWIiOiJzZyIsImlzcyI6InNnIiwiaWF0IjoxNjM4MTA2NzEyLCJleHAiOjE2MzgxMTAzMTJ9.JVsSbkP94wuczb4QryQbAke3ysBDIL5ou8fWsbt_ebg";
        Claims claims = parseJWT(token);
        System.out.println(claims);
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
```
#### 7.5 Download & launch Redis

- Redis is a memory-level NoSQL database with a key-value storage structure, that is, a database that runs in memory
  - it supports multiple data storage formats
  - it supports persistence and clustering
 
- Download (Windows x64)
> https://github.com/tporadowski/redis/releases

- Client launching command
> redis-cli.exe

Open installation directory: 
- Server launching command
> redis-server.exe redis.windows.conf


#### 7.6 Code implementaion - login
1. _User_ class under domain under shared module

```java
package com.js.domain.entity;


import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User  {
    
@TableId
    private Long id;


    private String userName;
    

    private String nickName;
    

    private String password;
    

    private String type;
    

    private String status;
    

    private String email;
    

    private String phonenumber;
    

    private String sex;
    

    private String avatar;
    

    private Long createBy;
    

    private Date createTime;
    

    private Long updateBy;
    

    private Date updateTime;
    

    private Integer delFlag;
    
}
```
2. _UserMapper_ under Mapper package under shared module

```java
package com.js.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.js.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserMapper extends BaseMapper<User> {

}
```

3._UserDetailsServiceImpl_ under impl under shared module
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.js.domain.entity.LoginUser;
import com.js.domain.entity.User;
import com.js.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        //inquire user info by username
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);

        //check if user is found, throw exception if not
        if(Objects.isNull(user)){
            throw new RuntimeException("User does not exist!");
        }

        //return user info
        //TODO inquire authority info encapsulation

        return new LoginUser(user);
    }
}
```
4. _LoginUser_ under domain under shared module
```java
package com.js.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
```
5. _BlogUserLoginVo_ under domain under shared module

```java
package com.js.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUserLoginVo {

    private String token;
    private UserInfoVo userInfoVo;
}
```

6. _UserInfoVo_ under domain under shared module

```java
package com.js.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserInfoVo {

    private Long id;

    private String nickName;

    private String avatar;

    private String sex;

    private String email;
}
```

7. _BlogLoginController_ under controller under frontstage module

```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.domain.entity.User;
import com.js.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("login")
    public ResponseResult login(@RequestBody User user){

        return blogLoginService.login(user);


    }
}
```

8. _BlogLoginService_ interface under Service package under shared module

```java
package com.js.service;

import com.js.domain.ResponseResult;
import com.js.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);
}
```

9.  _BlogLoginService_ under Impl package under shared module
```java
package com.js.service.impl;

import com.js.Utils.BeanCopyUtil;
import com.js.Utils.JwtUtil;
import com.js.Utils.RedisCache;
import com.js.domain.ResponseResult;
import com.js.domain.entity.LoginUser;
import com.js.domain.entity.User;
import com.js.domain.vo.BlogUserLoginVo;
import com.js.domain.vo.UserInfoVo;
import com.js.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //check if certification passed
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("Username or password incorrect!");
        }

        //get userId, generate token with userId
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString(); // cast Long to String
        String jwt = JwtUtil.createJWT(userId);

        //store userInfo into redis
        redisCache.setCacheObject("bloglogin:"+userId, loginUser);

        //convert user to userInfoVo using BeanCopy util
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(loginUser.getUser(), UserInfoVo.class);

        //encapsulate token and userInfo and return
        BlogUserLoginVo userLoginVo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(userLoginVo);

    }
}
```

10.  _SecurityConfig_ under config under frontstage module
```java
package com.js.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //close csrf
                .csrf().disable()
                //not through Session to get SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // for login interface, allow anonymous visit
                .antMatchers("/login").anonymous()
                // apart from above requests, no need of authentication
                .anyRequest().permitAll();


        http.logout().disable();
        //allow cors
        http.cors();
    }
}
```
#### 7.7 Test Login
1. Open redis

>  redis-server.exe redis.windows.conf

![image](https://github.com/LavaXD/MyBlog/assets/103249988/89ed2eab-4d96-480d-b9e3-f61f7ee6389b)

2. Open blog vue

> npm run dev

![image](https://github.com/LavaXD/MyBlog/assets/103249988/4d868c36-6ac1-4022-827a-62549af29e04)

3. Use **Postman** to test with request address and request body

> http://localhost:7777/login

```json
{
    "userName":"sg",
    "password":"1234"
}
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/28830111-b6f2-458a-a177-d67c6ea12893)

4. Test frontend page

> http://localhost:8080/#/Login?login=1

![image](https://github.com/LavaXD/MyBlog/assets/103249988/c6ee30ab-a2c9-4f99-9b18-d6261eec05d9)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/9c238bfe-8ca9-460e-9f72-4291604c4cab)

#### 7.8 Code implementation - verification filter
- Implement the verification function using jwt authentication filter to verify the user's login status
  
1. Create filter.JwtAuthenticationTokenFilter under frontstage module

```java
package com.js.filter;

import com.alibaba.fastjson.JSON;
import com.js.domain.entity.LoginUser;
import com.js.domain.ResponseResult;
import com.js.enums.AppHttpCodeEnum;
import com.js.Utils.JwtUtil;
import com.js.Utils.RedisCache;
import com.js.Utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@Component
//frontstage authentication filter, OncePerRequestFilter is a class provided by springsecurity
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    //RedisCache is an util to operate redis
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Get the value of token from request head
        String token = request.getHeader("token");
        //check if token is taken or no
        if(!StringUtils.hasText(token)){
            //if no token then this interface does not need login
            filterChain.doFilter(request,response);
            return;
        }

        //parse userID
        //JwtUtil is an util to parse token, to transfer encryted password to simple password
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            //throw exception if token expired or modified
            e.printStackTrace();
            //respond exception to frontend that login is needed
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        String userid = claims.getSubject();

        //get user using userID in redis，blogin is the prefix
        LoginUser loginUser = redisCache.getCacheObject("bloglogin:" + userid);
        //if no user, meaning login expired, need login
        if(Objects.isNull(loginUser)){
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }

        //store loginUser object store into SecurityContextHolder(class provided by Security)
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);

    }
}
```
2. Add the above filter into SpringSecurity filter chain - _SecurityConfig_

![image](https://github.com/LavaXD/MyBlog/assets/103249988/84dcb78f-2591-411b-af9a-60278b064a5a)

#### 7.9 Test verification filter

- Use Postman to test Get request after "link/getAllLink" is added to the authentication list

![image](https://github.com/LavaXD/MyBlog/assets/103249988/e6ca8965-7fe7-4145-b704-5831d2580f19)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/8a865382-375e-4d98-b504-1fe96c82f3c4)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/b870fe85-f228-43df-92ca-d88793ebaaad)







