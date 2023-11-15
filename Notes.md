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


## 8. Blog Frontstage - Exception Handler
#### 8.1 Authentication & authorization exception handler
- For now the default response format provided by spring security is used when authentication and authorization fail, which is not consistent
with the interface requirement. As a result, it is necessary to implement **_AuthenticationEntryPoint_** and **_AccessDeniedHandler_**

1. _AuthenticationEntryPointImpl_ under shared module
```java
package com.js.handler;

import com.alibaba.fastjson.JSON;
import com.js.Utils.WebUtils;
import com.js.domain.ResponseResult;
import com.js.enums.AppHttpCodeEnum;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        e.printStackTrace();
        ResponseResult result = null;

        //check which exception is
        if(e instanceof BadCredentialsException){
            result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        } else if (e instanceof InsufficientAuthenticationException){
            result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        } else {
            result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }

        //respond to front stage
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
```

2. _AccessDeniedHandlerImpl_ under shared module

```java
package com.js.handler;

import com.alibaba.fastjson.JSON;
import com.js.Utils.WebUtils;
import com.js.domain.ResponseResult;
import com.js.enums.AppHttpCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

        e.printStackTrace();
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);

        //respond to front stage
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
```

3. Add configurations of above impl classes in _SecurityConfig_ under Frontstage module

```java
@Autowired
AuthenticationEntryPoint authenticationEntryPoint;

@Autowired
AccessDeniedHandler accessDeniedHandler;

//config exception handler into security
http.exceptionHandling()
	.authenticationEntryPoint(authenticationEntryPoint)
	.accessDeniedHandler(accessDeniedHandler);
```
4. Test with **Postman**

> Post Request: http://localhost:7777/login
> Get Request: http://localhost:7777/link/getAllLink

![image](https://github.com/LavaXD/MyBlog/assets/103249988/ba06a769-d128-4e97-9e81-95f24a3a962c)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/703e558d-4219-460c-bbac-60b4cc1a7b75)

#### 8.2 Unified Execption Handling 

1. _SystemException_ class under exception package under shared module

```java
package com.js.exception;

import com.js.enums.AppHttpCodeEnum;

public class SystemException extends RuntimeException {
    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());

        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}
```
2. _GlobalExceptionHandler_ class under handler package under shared module

```java
package com.js.handler.exception;

import com.js.domain.ResponseResult;
import com.js.enums.AppHttpCodeEnum;
import com.js.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){

        //print exception info
        log.error("Exception detected! {}",e);

        //get msg from exception object, enacap and return
        return ResponseResult.errorResult(e.getCode(),e.getMsg());

    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(SystemException e){

        //print exception info
        log.error("Exception detected! {}",e);

        //get msg from exception object, enacap and return
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);

    }
}
```

3. Update _BlogLoginController_ with empty username exception 

```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.domain.entity.User;
import com.js.enums.AppHttpCodeEnum;
import com.js.exception.SystemException;
import com.js.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("login")
    public ResponseResult login(@RequestBody User user){

        //if username is null, then throw exception that username is required
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);


    }
}
```

## 9. Blog Frontstage - LOGOUT

#### 9.1 Interface design
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>POST</td>
		<td>/logout</td>
		<td>require 'token'</td>
	</tr>
</table>

```json
{
    "code": 200,
    "msg": "operation success"
}
```
#### 9.2 Procedure
- delete user info from redis

#### 9.3 Code Impl

- in _BlogLoginServiceImpl_

```java
@Override
    public ResponseResult logout() {
        //get token to parse userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        //get userId from token
        Long userId = loginUser.getUser().getId();

        //delete user info from redis
        redisCache.deleteObject("bloglogin:"+userId);
        return ResponseResult.okResult();
    }
```

- in _BlogLoginController_

```java
@PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
```

- Test
1. open redis 
```json
d:
cd /redis
redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/8027856a-ae97-4971-9af7-d1e743059d0e)


2. test in **Postman**

![image](https://github.com/LavaXD/MyBlog/assets/103249988/85943f8b-fd10-4da3-b3b5-9722747b1e2f)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/99b84885-41aa-47a4-8744-b72764dd8aac)

## 10. Blog Frontstage - Comment List

#### 10.1 Comment Table

![image](https://github.com/LavaXD/MyBlog/assets/103249988/d82ff2ac-3f74-4e64-9c0a-5e0de252fba6)

#### 10.2 Interface design

<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>Get</td>
		<td>/comment/commentList</td>
		<td>'token' is not needed, comment can be seen without login</td>
	</tr>
</table>

- Query parameters

```text
articleId
pageNum
pageSize
```

- Response Result (including root comment + subComment)

```json
{
    "code": 200,
    "data": {
        "rows": [
            {
                "articleId": "1",
                "children": [
                    {
                        "articleId": "1",
                        "content": "subComment",
                        "createBy": "1",
                        "createTime": "2022-01-30 10:06:21",
                        "id": "20",
                        "rootId": "1",
                        "toCommentId": "1",
                        "toCommentUserId": "1",
                        "toCommentUserName": "comment to",
                        "username": "the user makes the comment"
                    }
                ],
                "content": "root comment",
                "createBy": "1",
                "createTime": "2022-01-29 07:59:22",
                "id": "1",
                "rootId": "-1",
                "toCommentId": "-1",
                "toCommentUserId": "-1",
                "username": "user makes teh comment"
            }
        ],
        "total": "15"
    },
    "msg": "operation success"
}
```

#### 10.3 Coding

##### 10.3.1 Root comment implementation
1. Use _**EasyCode**_ to generate the Mapper, Entity, Service, and ServiceImpl of table Comment

![image](https://github.com/LavaXD/MyBlog/assets/103249988/ddfeafee-cc22-4222-85f6-190f6d6559fd)

2. Create _**CommentVo**_ to return the correct format to the frontstage

```java
package com.js.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private Long id;

    private Long articleId;

    private Long rootId;

    private String content;

    private Long toCommentUserId;

    private String toCommentUserName;

    private Long toCommentId;

    private Long createBy;

    private Date createTime;

    private String username;
}
```
3. Implement service code in **_CommentServiceImpl_** class under shared module

```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.Utils.BeanCopyUtil;
import com.js.domain.ResponseResult;
import com.js.domain.entity.Comment;
import com.js.domain.vo.CommentVo;
import com.js.domain.vo.PageVo;
import com.js.mapper.CommentMapper;
import com.js.service.CommentService;
import com.js.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.js.constants.SystemConstants.COMMENT_ROOT;


@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        //inquire root comment of the corresponding article
        //use articleId to inquire the corresponding article
        queryWrapper.eq(Comment::getArticleId, articleId);

        //the rootId of a root comment is -1
        queryWrapper.eq(Comment::getRootId, COMMENT_ROOT);

        //paging query
        Page<Comment> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());

        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }

    private List<CommentVo> toCommentVoList(List<Comment> list){
        List<CommentVo> commentVos = BeanCopyUtil.copyBeanList(list, CommentVo.class);

        //traversal vo list
        for (CommentVo commentVo : commentVos) {

            //get username through createBy
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);

            //get toCommentUserName through toCommentUserId
            if(commentVo.getToCommentId() != -1){ //a comment only have toCommentId when a comment is not a root comment
                String toCommentUserName = userService.getById(commentVo.getToCommentId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;
    }
}
```
4. Test

- Open redis
> d:

> cd/redis

> redis-server.exe redis.windows.conf

![image](https://github.com/LavaXD/MyBlog/assets/103249988/ade325f5-7759-4e71-8a9d-b7ae295f5b13)

- Use _**Postman**_ to test

![image](https://github.com/LavaXD/MyBlog/assets/103249988/2f8a5d44-f11f-4614-ba08-8e6d9063830c)


##### 10.3.2 Child comment implementation
1. Update _**CommentVo**_ as follows: add "children" attribute

```java
package com.js.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private Long id;

    private Long articleId;

    private Long rootId;

    private String content;

    private Long toCommentUserId;

    private String toCommentUserName;

    private Long toCommentId;

    private Long createBy;

    private Date createTime;

    private String username;

    private List<CommentVo> children;
}
```
2. Update _**CommentServiceImpl**_ as follows:

```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.Utils.BeanCopyUtil;
import com.js.domain.ResponseResult;
import com.js.domain.entity.Comment;
import com.js.domain.vo.CommentVo;
import com.js.domain.vo.PageVo;
import com.js.mapper.CommentMapper;
import com.js.service.CommentService;
import com.js.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.js.constants.SystemConstants.COMMENT_ROOT;


@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        //inquire root comment of the corresponding article
        //use articleId to inquire the corresponding article
        queryWrapper.eq(Comment::getArticleId, articleId);

        //the rootId of a root comment is -1
        queryWrapper.eq(Comment::getRootId, COMMENT_ROOT);

        //paging query
        Page<Comment> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());

        //inquire all the children comment as a list
        for (CommentVo commentVo : commentVoList) {

            //inquire corresponding children comment of each root comment - through root_id
            List<CommentVo> children = getChildrenComment(commentVo.getId());

            //set value
            commentVo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }

    private List<CommentVo> getChildrenComment(Long id) {

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);

        List<Comment> comments = list(queryWrapper);

        List<CommentVo> commentVos = toCommentVoList(comments);
        return commentVos;
    }

    private List<CommentVo> toCommentVoList(List<Comment> list){
        List<CommentVo> commentVos = BeanCopyUtil.copyBeanList(list, CommentVo.class);

        //traversal vo list
        for (CommentVo commentVo : commentVos) {

            //get username through createBy
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);

            //get toCommentUserName through toCommentUserId
            if(commentVo.getToCommentUserId() != -1){ //a comment only have toCommentId when a comment is not a root comment
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;
    }
}
```
3. Test with _**Postman**_

![image](https://github.com/LavaXD/MyBlog/assets/103249988/1fbab47d-8681-4530-87a4-b254287835e0)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/9d78104c-5660-496d-a1e1-3dc0273ded56)

4. Open Blog Vue and redis to test web page

- Blog Vue

```text
> d:
> cd/BlogWeb/js-blog-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/ab01ddcc-2dd8-4a58-9a3b-8693426ffcd6)

## 11. Blog FrontStage - Sending Comment

#### 11.1 Sending article comment

##### 11.1.1 Comment table

![image](https://github.com/LavaXD/MyBlog/assets/103249988/a56399fc-807e-47ab-969e-091f1e90f3d5)

##### 11.1.2 Interface design

<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>Post</td>
		<td>/comment</td>
		<td>'token' is needed, comment can be sent with login</td>
	</tr>
</table>

**_[Request body]_**
- When directly comment about an article and reply to a comment, type = 0.
- If comment about a friend link, type =1
```json
{"articleId":1,"type":0,"rootId":-1,"toCommentId":-1,"toCommentUserId":-1,"content":"comment an article"}
```
```json
{"articleId":1,"type":0,"rootId":"3","toCommentId":"3","toCommentUserId":"1","content":"reply to a comment"}
```
**_[Response format]_**
```json
{
	"code":200,
	"msg":"operation success"
}
```

##### 11.1.3 Coding

1. _**SecurityUtil**_ under Util package - encapsulation of method for getting userInfo form token

```java
package com.js.Utils;

import com.js.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//comment functionality utils
public class SecurityUtil {

    /**
     * get userid
     **/
    public static LoginUser getLoginUser() {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    /**
     * get Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * website admin is the one whose userId = 1
     * @return
     */
    public static Boolean isAdmin(){
        Long id = getLoginUser().getUser().getId();
        return id != null && 1L == id;
    }

    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }
}
```

2. _**MyMetaObjectHandler**_ under handler package - Mybatis' automatic field fill

```java
package com.js.handler.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.js.Utils.SecurityUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author js
 * @date 2023/7/26 0026 20:52
 */
@Component
//这个类是用来配置mybatis的字段自动填充。用于'发送评论'功能，由于我们在评论表无法对下面这四个字段进行插入数据(原因是前端在发送评论时，没有在
//请求体提供下面四个参数，所以后端在往数据库插入数据时，下面四个字段是空值)，所有就需要这个类来帮助我们往下面这四个字段自动的插入值，
//只要我们更新了评论表的字段，那么无法插入值的字段就自动有值了
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    //只要对数据库执行了插入语句，那么就会执行到这个方法
    public void insertFill(MetaObject metaObject) {
        Long userId = null;
        try {
            //获取用户id
            userId = SecurityUtil.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            userId = -1L;//如果异常了，就说明该用户还没注册，我们就把该用户的userid字段赋值d为-1
        }
        //自动把下面四个字段新增了值。
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy",userId , metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName(" ", SecurityUtil.getUserId(), metaObject);
    }
}
```
3. Update _**Comment**_ entity - for field fill

```java
package com.js.domain.entity;


import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("comment")
public class Comment  {
    
@TableId
    private Long id;

    

    private String type;
    

    private Long articleId;
    

    private Long rootId;
    

    private String content;
    

    private Long toCommentUserId;
    

    private Long toCommentId;
    
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    

    private Integer delFlag;
    
}
```

4. Update _**CommentServiceImpl**_ - for addComment function

```java
@Override
    public ResponseResult addComment(Comment comment) {

        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        //MP plus's method to automatically execute insert query
        save(comment);
        return ResponseResult.okResult();
    }
```

5. Test

![image](https://github.com/LavaXD/MyBlog/assets/103249988/8a212553-93dc-4921-9921-cf379fb50798)


![image](https://github.com/LavaXD/MyBlog/assets/103249988/97ce077a-c2c4-4f58-b421-510515016c86)

#### 11.2 Sending friend link comment

##### 11.2.1 Comment table

![image](https://github.com/LavaXD/MyBlog/assets/103249988/d5e330bc-cfbe-41d5-8e84-ea49745cb80c)

##### 11.2.2 Interface design

<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>Get</td>
		<td>/comment/LinkCommentList</td>
		<td>'token' is not needed, Link comment can be sent without login</td>
	</tr>
</table>

- Query request para
> pageNum
> pageSize

- Response format
```json
{
    "code": 200,
    "data": {
        "rows": [
            {
                "articleId": "1",
                "children": [
                    {
                        "articleId": "1",
                        "content": "reply to link comment 3",
                        "createBy": "1",
                        "createTime": "2022-01-30 10:08:50",
                        "id": "23",
                        "rootId": "22",
                        "toCommentId": "22",
                        "toCommentUserId": "1",
                        "toCommentUserName": "sg333",
                        "username": "sg333"
                    }
                ],
                "content": "link commet 2",
                "createBy": "1",
                "createTime": "2022-01-30 10:08:28",
                "id": "22",
                "rootId": "-1",
                "toCommentId": "-1",
                "toCommentUserId": "-1",
                "username": "sg333"
            }
        ],
        "total": "1"
    },
    "msg": "operation success"
}
```

##### 11.2.3 Coding

1. Update _**CommentController**_ (add commentType to distinguish artciel from friendLink)

```java
package com.js.controller;

import com.js.constants.SystemConstants;
import com.js.domain.ResponseResult;
import com.js.domain.entity.Comment;
import com.js.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize);
    }
}
```

2. Update **CommentList** method in  _**CommentServiceImpl**_ (add judging condition between article and friendLink)

```java
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        //inquire root comment of the corresponding article
        //use articleId to inquire the corresponding article
        //only check articleId when it's article comment
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId, articleId);

        //the rootId of a root comment is -1
        queryWrapper.eq(Comment::getRootId, COMMENT_ROOT);

        //comment type
        queryWrapper.eq(Comment::getType,commentType);

        //paging query
        Page<Comment> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());

        //inquire all the children comment as a list
        for (CommentVo commentVo : commentVoList) {

            //inquire corresponding children comment of each root comment - through root_id
            List<CommentVo> children = getChildrenComment(commentVo.getId());

            //set value
            commentVo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }
```
3. Test

![image](https://github.com/LavaXD/MyBlog/assets/103249988/6185baa7-e176-48b0-9f5f-61c5133e35f0)

## 12. Blog FrontStage - Personal Center

#### 12.1 Requirement 

- After logging in, user should be able to see and update personal information in the personal center

![image](https://github.com/LavaXD/MyBlog/assets/103249988/d7699a2e-7a74-472c-96a2-b2da11c41909)


#### 12.2 Interface design

<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>Get</td>
		<td>/user/userInfo</td>
		<td>'token' is needed, personal center can be seen with login</td>
	</tr>
</table>

- Response format

```json
{
	"code":200,
	"data":{
		"avatar":"avatar address",
		"email":"123@qq.com",
		"id":"1",
		"nickName":"username",
		"sex":"1"
	},
	"msg":"operation success"
}
```
#### 12.3 Coding 

1. _**UserServiceImpl**_

```java
package com.js.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.Utils.BeanCopyUtil;
import com.js.Utils.SecurityUtil;
import com.js.domain.ResponseResult;
import com.js.domain.entity.User;
import com.js.domain.vo.UserInfoVo;
import com.js.mapper.UserMapper;
import com.js.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {

        //Get current userId
        Long userId = SecurityUtil.getUserId();

        //inquire userInfo by userId
        User user = getById(userId);

        //encapsulate into UserInfoVo
        UserInfoVo vo = BeanCopyUtil.copyBean(user,UserInfoVo.class);

        return ResponseResult.okResult(vo);
    }
}
```

2. _**UserController**_
```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }
}
```
3. Update _**SecurityConfig**_

![image](https://github.com/LavaXD/MyBlog/assets/103249988/f960d4bf-1b07-4e0f-87eb-b96cf73bf156)

4. **Test**

Open Blog Vue and redis to test web page

- Blog Vue

```text
> d:
> cd/BlogWeb/js-blog-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/4f0e59ca-9581-4c13-a2e9-c588c15804ff)

## 13. Blog FrontStage - Avatar Uploading

#### 13.1 OSS preparation - Alibaba cloud

- Reason: If user upload files such as pictures and videos to a directory of the application's Web server, it will take up too much resources when reading pictures, And the performance of the application server is affected. Therefore, we choose to use OSS(Object Storage Service) to store pictures or videos

- Create a new account in Alibaba cloud, new a bucket
![image](https://github.com/LavaXD/MyBlog/assets/103249988/d647f107-85ff-4b3b-bd00-f3be346851d5)

- Add dependencies from documentation center
![image](https://github.com/LavaXD/MyBlog/assets/103249988/f99ba7dc-50f6-4069-bba7-d237b9a36fe1)

```xml
<!--ali cloud OSS-->
        <dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-sdk-oss</artifactId>
        </dependency>
        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.1</version>
        </dependency>
        <dependency>
            <groupId>javax.activation</groupId>
            <artifactId>activation</artifactId>
            <version>1.1.1</version>
        </dependency>
        <!-- no more than 2.3.3-->
```
- Copy demo code to implement file upload stream

![image](https://github.com/LavaXD/MyBlog/assets/103249988/d52eda34-c796-42bb-8c77-884e1ce43e90)

- Upadate _**application.yml**_

![image](https://github.com/LavaXD/MyBlog/assets/103249988/fd1d7724-7221-4a0b-ad74-5e4bb66a1819)

- Modify demo code as follows

```java
package com.js;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;

@Component
@SpringBootTest
@ConfigurationProperties(prefix = "oss")
public class OSStest {

    static private String accessKeyId;
    static private String accessKeySecret;
    static private String bucketName;

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    @Test
    public void test () throws Exception {
        //  actual endpoint is sydney
        String endpoint = "https://oss-ap-southeast-2.aliyuncs.com";
        // Obtain access credentials from environment variables. Before you run the sample code, make sure that the OSS_ACCESS_KEY_ID and OSS_ACCESS_KEY_SECRET environment variables are configured.
        //String accessKeyId = "LTAI5xxx";
        //String accessKeySecret = "xxx";
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        //EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // Specify the name of the bucket. Example: examplebucket.
        //String bucketName = "js-blog";
        // Specify the full path of the object. Do not include the bucket name in the full path. Example: exampledir/exampleobject.txt.
        String objectName = "avatar1.jpg";
        // Specify the full path of the local file that you want to upload. Example: D:\\localpath\\examplefile.txt.
        // If you do not specify the path of the local file, the local file is uploaded from the path of the project to which the sample program belongs.
        String filePath= "D:\\pics\\1.jpg";

        // Create an OSSClient instance.
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        try {
            InputStream inputStream = new FileInputStream(filePath);
            // Create a PutObjectRequest object.
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            // Create a PutObject request.
            PutObjectResult result = ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
```
- Run the test code and the image file has been uploaded

![image](https://github.com/LavaXD/MyBlog/assets/103249988/c570f099-78ac-4b1c-a8af-b637f6391077)


#### 13.2 Interface design

<table>
	<tr>
		<td>Request method</td>
		<td>Request path</td>
		<td>Request head</td>
	</tr>
	<tr>
		<td>POST</td>
		<td>/upload</td>
		<td>token needed</td>
	</tr>
</table>

Parameter:
> < .img > - the file user want to upload

Request head:
> Content-Type ：multipart/form-data;

Response format:
```json
{
    "code": 200,
    "data": "Url to access the file",
    "msg": "operation success"
}
```

#### 13.3 Coding

1. _**PathUtils**_ under Utils

```java
package com.js.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

//modify fileName of original file, and modify saving directory
public class PathUtils {

    public static String generateFilePath(String fileName){
        //generate path according to date   2022/1/15/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());
        //uuid as fileName
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //suffix should be the same
        int index = fileName.lastIndexOf(".");
        // test.jpg -> .jpg
        String fileType = fileName.substring(index);
        return new StringBuilder().append(datePath).append(uuid).append(fileType).toString();
    }
}
```

2. _**UploadController**_

```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult UploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}

```
3. _**UploadServiceImpl**_

```java
package com.js.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.js.Utils.PathUtils;
import com.js.domain.ResponseResult;
import com.js.enums.AppHttpCodeEnum;
import com.js.exception.SystemException;
import com.js.service.UploadService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@Data
@AllArgsConstructor
//@ConfigurationProperties(prefix = "oss")
public class UploadServiceImpl implements UploadService {

    @Override
    public ResponseResult uploadImg(MultipartFile img) {

        //check the file type
        //inquire file name
        String originalFilename = img.getOriginalFilename();

        //if file type is not png or jpg, throw exception
        if(!originalFilename.endsWith(".png") && !originalFilename.endsWith(".jpg")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }

        //if passed check, upload file to OSS
        String filePath = PathUtils.generateFilePath(originalFilename);
        String url = uploadOSS(img,filePath);

        return ResponseResult.okResult(url);
    }


    //static private String accessId;
    static private String accessSecret;
    static private String bucketName;

    private String uploadOSS(MultipartFile img, String filePath)  {

        //  actual endpoint is sydney
        String endpoint = "https://oss-ap-southeast-2.aliyuncs.com";

        String accessId = "xxx";
        String accessSecret = "xxx";
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessId, accessSecret);

        //EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // Specify the name of the bucket. Example: examplebucket.
        String bucketName = "js-blog";

        String objectName = filePath;

        // Create an OSSClient instance.
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        try {
            InputStream inputStream = img.getInputStream();
            // Create a PutObjectRequest object.
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            // Create a PutObject request.
            PutObjectResult result = ossClient.putObject(putObjectRequest);

            return "https://js-blog.oss-ap-southeast-2.aliyuncs.com/" + filePath;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return "Uploading fail";
    }
}
```
4. Use **Postman** to test

![image](https://github.com/LavaXD/MyBlog/assets/103249988/0395d2e0-ad7a-42e1-92b3-68f38283dfa2)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/fafa4c10-3d19-47c2-9b97-14f3d6af176b)

Test successful!

#### 13.4 Blog FrontStage - Updating User Info

##### 13.4.1 Interface design

Update the user information into database after editing 
<table>
	<tr>
		<td>Request method</td>
		<td>Request path</td>
		<td>Request head</td>
	</tr>
	<tr>
		<td>PUT</td>
		<td>/user/userInfo</td>
		<td>token needed</td>
	</tr>
</table>

- Request body
```json
{
    "avatar":"picture url",
    "email":"23412332@qq.com",
    "id":"1",
    "nickName":"userName",
    "sex":"1"
}
```
- Response format
```json
{
	"code":200,
	"msg":"operation success"
}
```

##### 13.4.2 Coding

1. Update _**UserService**_

```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.entity.User;


public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);
}
```

2. Update _**UserServiceImpl**_

```java
package com.js.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.Utils.BeanCopyUtil;
import com.js.Utils.SecurityUtil;
import com.js.domain.ResponseResult;
import com.js.domain.entity.User;
import com.js.domain.vo.UserInfoVo;
import com.js.mapper.UserMapper;
import com.js.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {

        //Get current userId
        Long userId = SecurityUtil.getUserId();

        //inquire userInfo by userId
        User user = getById(userId);

        //encapsulate into UserInfoVo
        UserInfoVo vo = BeanCopyUtil.copyBean(user,UserInfoVo.class);

        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {

        updateById(user);

        return ResponseResult.okResult();
    }
}
```

3. Update _**UserController**_

```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.domain.entity.User;
import com.js.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }
}
```

4. **Test**

Open Blog Vue and redis to test web page

- Blog Vue

```text
> d:
> cd/BlogWeb/js-blog-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/aaad6369-f024-47b0-b4a6-5f098ee6e0c7)

User information has been updated successfully!

## 14. Blog FrontStage - Registration

#### 14.1 Interface design

![image](https://github.com/LavaXD/MyBlog/assets/103249988/1cce8d8e-d656-4be1-84fb-6adf529b2b33)

- Username, email can not be duplicated with the information in the database, if yes, prompt proper msg to inform user.
- Username, email, password can not be empty
- Password should be stored into database in encrypted form

<table>
	<tr>
		<td>Request method</td>
		<td>Request path</td>
		<td>Request head</td>
	</tr>
	<tr>
		<td>Post</td>
		<td>/user/register</td>
		<td>token not needed</td>
	</tr>
</table>

Request body
```json
{
  "email": "string",
  "nickName": "string",
  "password": "string",
  "userName": "string"
}
```
Response format
```json
{
	"code":200,
	"msg":"operation success"
}
```

#### 14.2 Coding 
 
1. Update _**AppHttpCodeEnum**_

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
    NICKNAME_EXIST(512,"nick name already existed"),
    PHONENUMBER_EXIST(502,"phone number already existed"), EMAIL_EXIST(503, "email already existed"),
    REQUIRE_USERNAME(504, "require username"),
    LOGIN_ERROR(505,"The user name or password is incorrect"),
    CONTENT_NOT_NULL(506,"content must not be empty" ),
    FILE_TYPE_ERROR(507,"only accept .png file" ),
    USERNAME_NOT_NULL(508,"username can not be empty" ),
    NICKNAME_NOT_NULL(509,"nick name can not be empty" ),
    PASSWORD_NOT_NULL(510,"password can not be empty" ),
    EMAIL_NOT_NULL(511,"email name can not be empty" );

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

2. Update _**UserServiceImpl**_
```java
@Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult register(User user) {

        //do non-empty check - email,username,pass can not be empty
         if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
         }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }

        //do duplication check, if the input email is existed in DB
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(emailExist(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }

        //password encryption
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        //store into DB
        save(user);

        return ResponseResult.okResult();
    }

    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNickName,nickName);
        return count(queryWrapper) > 0;
    }

    private boolean emailExist(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,email);
        return count(queryWrapper) > 0;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,userName);
        return count(queryWrapper) > 0;
    }
```

3. Update _**UserController**_ - register method
```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.domain.entity.User;
import com.js.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}
```

4. **Test**

Open Blog Vue and redis to test web page

- Blog Vue

```text
> d:
> cd/BlogWeb/js-blog-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/75314c46-bd8d-4bd8-a9ca-a4b673d8d51a)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/0665ee89-21ff-40c9-9997-d3191c0e2ed9)

**User has been add into database**

## 15. Blog FrontStage - Log 

#### 15.1 Requirement analysis 

- Using log to record information when interfaces are called and used
- Application of AOP to realize the log recording because many interfaces need to record log
- It is a proper situation to use AOP for decoupling and not modify original service code

Output format: 
```java
log.info("======================Start======================");
        // print request URL
        log.info("URL            : {}",);
        // print description info
        log.info("BusinessName   : {}",);
        // print Http method
        log.info("HTTP Method    : {}",);
        // whole type name & method name of controller
        log.info("Class Method   : {}.{}",);
        // print request IP
        log.info("IP             : {}",);
        // print request passed param, cast args array to json
        log.info("Request Args   : {}", );
log.info("======================End======================" + System.lineSeparator()); // linefeed method of system
```
#### 15.2 Procedure 
1. Define aspect class
2. Use cutomized annotation infterface to determine the join point in aspect class
3. In aspect class, implement advice method on specific join point

There are 5 advice methods in AOP:
<table>
	<tr>
		<td>Advice method</td>
		<td>Description</td>
	</tr>
	<tr>
		<td>Before advice</td>
		<td>Excecute advice before a method</td>
	</tr>
	<tr>
		<td>After advice</td>
		<td>Excecute advice before a method</td>
	</tr>
	<tr>
		<td>After-returning advice</td>
		<td>Excecute advice finally, no matter the method is successfully excecuted or not</td>
	</tr>
	<tr>
		<td>After-throwing advice</td>
		<td>Excecute advice when exception is throwed</td>
	</tr>
	<tr>
		<td>Around advice</td>
		<td>Execute advice before and after of a join point</td>
	</tr>
</table>

#### 15.3 Coding
1. Create _**SystemLog**_ annotation interface under shared module
```java
package com.js.annotation;

import org.aspectj.lang.annotation.Around;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //which phase to maintain
@Target({ElementType.METHOD}) //is used on method
//customize annotation
public @interface SystemLog {

    String businessName();
}
```

2. Create _**LogAspect**_ aspect class under shared module
```java
package com.js.aspect;

import com.alibaba.fastjson.JSON;
import com.js.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
// aspect class of AOP
public class LogAspect {

    @Pointcut("@annotation(com.js.annotation.SystemLog)")
    public void pt(){

    }

    //Around notification
    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //do not use try/catch in here, because we want SystemException I defined to catch exception
        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            //print "end" anyway
            log.info("======================End======================" + System.lineSeparator()); // linefeed method of system
        }

        return  ret;
    }

    private void handleAfter(Object ret) {
        //print output param
        log.info("Response       : {}", JSON.toJSONString(ret));
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {


        //get request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = requestAttributes.getRequest();

        //get annotation object on certain method
        SystemLog systemLog = getSystemLog(joinPoint);

        log.info("======================Start======================");
        // print request URL
        log.info("URL            : {}",request.getRequestURL());
        // print description info
        log.info("BusinessName   : {}",systemLog.businessName());
        // print Http method
        log.info("HTTP Method    : {}",request.getMethod());
        // whole type name & method name of controller
        log.info("Class Method   : {}.{}",joinPoint.getSignature().getDeclaringTypeName(),((MethodSignature) joinPoint.getSignature()).getName());
        // print request IP
        log.info("IP             : {}",request.getRemoteHost());
        // print request passed param, cast args array to json
        log.info("Request Args   : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = methodSignature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;
    }
}
```

3. Modify _**UserController**_, add _SystemLog_ annotation on _updateUserInfo_ method to test
```java
package com.js.controller;

import com.js.annotation.SystemLog;
import com.js.domain.ResponseResult;
import com.js.domain.entity.User;
import com.js.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @SystemLog(businessName = "update user info")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}
```

#### 15.4 Test
Open Blog Vue and redis to test web page

- Blog Vue

```text
> d:
> cd/BlogWeb/js-blog-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/20b1e99e-afbf-4343-98d4-d7f88c358c93)

**Vist Web Page to test log infomation**

![image](https://github.com/LavaXD/MyBlog/assets/103249988/8d5d4772-f902-4433-aa67-77ba832d8ff9)

## 16. Blog FrontStage - Update viewCount

#### 16.1 Preparation - Procedure analysis

Update the blog view count when an article is viewd by user, but update the view count in redis first, and then update in the database to reduce traffic when concurrent volume is huge.
1. Transfer the view count data from database to redis when the application is initialized - preprocessing function
2. Only update the view count data in redis when viewCount increase
3. Update view count data into database from redis every 5 mins - timed task
4. Read view count data from redis when needed

#### 16.2 Preparation - preprocessing function

**CommandLineRunner** interface can be used when initialization operation is needed. Firstly, it is required to implement this interface and inject the corresponding bean into SpringBoot, and then the method in the implementation class will be executed when the application is initialized. 

1. Create _**TestRunner**_ under frontstage module
```java
package com.js.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("initialization...");
    }
}
```
2. Run the application, the message in the method is successfully printed
![image](https://github.com/LavaXD/MyBlog/assets/103249988/81e2fc48-96a8-4ed2-b2c4-e4776f827265)


#### 16.3 Preparation - timed task

A scheduling API provided by SpringBoot is used here to realize a simple timed task.
1. Using **@EnableScheduling** in the initialization class to enable timed task
```java
package com.js;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.js.mapper")
@EnableScheduling
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
    }

}
```
2. Complete task code, and configure the duration of the task - use **@Scheduled** and **cron** expression to configure timed task
```java
package com.js.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestJob {

    @Scheduled(cron = "0/5 * * * * ? *")
    public void testJob(){

        //task need to execute
        System.out.println("timed task is executed...");
    }

}
```
Run the application, the message is printed every 5 seconds 

![image](https://github.com/LavaXD/MyBlog/assets/103249988/3695bfb4-91a2-4183-9fa2-f7b040fff205) 

#### 16.4 Interface design

<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>PUT</td>
		<td>/article/updateViewCount/{id}</td>
		<td>token is not needed here</td>
	</tr>
</table>

Parameter
> **articleId** is carried in request path

Response format 
```json
{
	"code":200,
	"msg":"operation success"
}
```
#### 16.5 Coding 

##### 16.5.1 Preprocessing 
1. When the application is initialized, store 'id', 'viewCount' as a map from database to redis
create _**runner.ViewCountRunner**_ under frontstage module

```java
package com.js.runner;

import com.js.Utils.RedisCache;
import com.js.domain.entity.Article;
import com.js.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {

        //inquire blog info -> id, viewCount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> {
                    return article.getViewCount().intValue(); // long type can not increment in redis, so Integer is used here
                }));

        //store into redis
        redisCache.setCacheMap("viewCount",viewCountMap);
    }
}
```

2. Test

- open redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/0fc5b6eb-e3d2-4371-8cf4-54c3c23335b6)

##### 16.5.2 ViewCount increment

1. Add _**incrementCacheMapValue**_ method in **RedisCache** class under shared module
```java
/**
     * increments the value of a hash structure in redis
     * @param key
     * @param hKey
     * @param value
     */
public void incrementCacheMapValue(String key, String hKey, int value){
        redisTemplate.opsForHash().increment(key,hKey,value);
    }
```

2. Add _**updateViewCount**_ method in **ArticleServiceImpl** class
```java
@Override
    public ResponseResult updateViewCount(Long id) {

        //update viewCount of corresponding article in redis
        redisCache.incrementCacheMapValue("viewCount", id.toString(),1);
        return ResponseResult.okResult();
    }
```

3. Add _**updateViewCount**_ in **ArticleService** class 
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);
}
```
4. Add _**updateViewCount**_ method in **ArticleController**
```java
@PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }
```

5. **Test**
Open Blog Vue and redis to test web page

- Blog Vue

```text
> d:
> cd/BlogWeb/js-blog-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```
![image](https://github.com/LavaXD/MyBlog/assets/103249988/67d6fe44-0878-434e-bef3-db38eca36f32)

##### 16.5.3 Data synchronization 
1. Create _**UpdateViewCountJob**_ under **job** package under Frontstage module
```java
package com.js.job;

import com.js.Utils.RedisCache;
import com.js.domain.entity.Article;
import com.js.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired // because batch operation is needed here, so articleService is used with 'IService' interface
    private ArticleService articleService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void updateViewCount(){

        //get viewCount from redis
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("viewCount");

        //in 'updateBatchById' method, parameter is a collection, so map 'viewCountMap' should be transferred into a collection
        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        //update thew viewCount into database
        articleService.updateBatchById(articles);
    }
}
```

2. **Test**
Visit the web page to test **viewCount** update

Open Blog Vue and redis to test web page

- Blog Vue

```text
> d:
> cd/BlogWeb/js-blog-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```
- redis cli

```text
> d:
> cd/redis
> redis-cli
> hgetall viewCount
```
![image](https://github.com/LavaXD/MyBlog/assets/103249988/8a38d82f-7027-4ecb-96dd-e3c48b3c0aa2)

##### 16.5.4 Inquire 'viewCount' from redis instead of database

- Update **getArticelDetail** & **hotArticleList** functions in **ArticleServiceImpl**
- add updating 'viewCount' from redis code 
  
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.js.Utils.BeanCopyUtil;
import com.js.Utils.RedisCache;
import com.js.constants.SystemConstants;
import com.js.domain.ResponseResult;
import com.js.domain.entity.Article;
import com.js.domain.entity.Category;
import com.js.domain.vo.ArticleDetailVo;
import com.js.domain.vo.ArticleListVo;
import com.js.domain.vo.HotArticleVo;
import com.js.domain.vo.PageVo;
import com.js.mapper.ArticleMapper;
import com.js.service.ArticleService;
import com.js.service.CategoryService;
import io.swagger.models.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {

    @Autowired //using categoryService to inquire categoryId and categoryName
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisCache redisCache;

    //------------------------------------------------- hotArticleList ----------------------------------------------------------
    @Override
    //inquire hot articles, return ResponseResult
    public ResponseResult hotArticleList() {

        //update viewCount for hotArticleList from redis
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("viewCount");
            //use entrySet method to convert 'viewCountMap' from diallel set to single set
        List<Article> articleList = viewCountMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        //use 'articleService' updateBatchById method
        articleService.updateBatchById(articleList);

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //it has to be a completed, formal article
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);

        //order by view count
        queryWrapper.orderByDesc(Article::getViewCount);

        //max hot articles amount: 10
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();

        //bean copy
//        List<HotArticleVo> articleVos =new ArrayList<>();
//        for(Article article:articles){
//            HotArticleVo articleVo = new HotArticleVo();
//            BeanUtils.copyProperties(article,articleVo);
//            articleVos.add(articleVo);
//        }

        List<HotArticleVo> hotArticleVos = BeanCopyUtil.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(hotArticleVos);
    }

    //------------------------------------------------- articleList ---------------------------------------------------------
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {

        //inquire conditions
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

            //if categoryId is available, it should remain the same when inquiring
            //if categoryId is not null, and it is > 0(maybe it's -1 from frontend), and then check if they are the same
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0,Article::getCategoryId,categoryId);

            //the "status" of an article has to be "normal"
        queryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);

            //sort "istop" column in descending order, so that those articles whoes "istop" is 1 can be at top
        queryWrapper.orderByDesc(Article::getIsTop);

        //Paging query
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        //inquire categoryName
        List<Article> articles = page.getRecords();
//        //using articleId to config articleName
//        for (Article article : articles) {
//            Category category = categoryService.getById(article.getCategoryId());
//            article.setCategoryName(category.getName());
//        }
        articles.stream()
                //get categoryId by articleId, get categoryName by categoryId
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());

        //encapsulate query result
        List<ArticleListVo> articleListVos = BeanCopyUtil.copyBeanList(page.getRecords(), ArticleListVo.class);

        //use PageVo class to encapsulate articleListVos as rows and total attribute, and then return pageVo object as a complete data object
        PageVo pageVo = new PageVo(articleListVos,page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    //------------------------------------------------- ArticleDetail -------------------------------------------------------
    @Override
    public ResponseResult getArticleDetail(Long id) {

        //inquire article by articleId
        Article article = getById(id);

        //get viewCount from redis
        Integer viewCount = redisCache.getCacheMapValue("viewCount", id.toString());
        article.setViewCount(viewCount.longValue());

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

    @Override
    public ResponseResult updateViewCount(Long id) {

        //update viewCount of corresponding article in redis
        redisCache.incrementCacheMapValue("viewCount", id.toString(),1);
        return ResponseResult.okResult();
    }
}

```
#### 16.6 Test 
Open Blog Vue and redis to test web page

- Blog Vue

```text
> d:
> cd/BlogWeb/js-blog-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```
![image](https://github.com/LavaXD/MyBlog/assets/103249988/64cc4f90-19ab-4c0b-9228-fcb42b2a719d)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/7f86fa32-17b2-4479-9514-068b8c2c8782)

## 17 Swagger2

#### 17.1 Swagger2 - QuickStart
1. Add dependency into pom file of shared module
```xml
<!-- Swagger2 -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
        </dependency>
```
2. Add _@EnableSwagger2_annotation of swagger in **BlogApplication** initialization class
```java
package com.js;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.js.mapper")
@EnableScheduling
@EnableSwagger2
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
    }

}
```
3. Run the application and test
> http://localhost:7777/swagger-ui.html

![image](https://github.com/LavaXD/MyBlog/assets/103249988/5c7baafc-d695-4460-b56e-640d85d737d4)

#### 17.2 Swagger2 - Controller config
- Add _@Api_ annotation in **CommentController** to customize tag & description
```java
package com.js.controller;

import com.js.constants.SystemConstants;
import com.js.domain.ResponseResult;
import com.js.domain.entity.Comment;
import com.js.service.CommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = "comment",description = "comment interface")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize);
    }
}
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/ec11a5c8-4665-4139-85d6-2927fb07d422)

#### 17.3 Swagger2 - Interface config
- Update **linkCommentList** function in **CommentController** using _@ApiOperation_ annotation
```java
    @GetMapping("/linkCommentList")
    @ApiOperation(value = "friend link comment list", notes = "get one page of friend link comments ")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize);
    }
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/a1664c88-fadb-41df-b269-d0213432b5fb)


#### 17.4 Swagger2 - Interface parameter config
- Update **linkCommentList** function in **CommentController** using _ @ApiImplicitParams_ annotation

```java
@GetMapping("/linkCommentList")
    @ApiOperation(value = "friend link comment list", notes = "get one page of friend link comments ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "page number"),
            @ApiImplicitParam(name = "pageSize", value = "page size")
    })
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize);
    }
```
![image](https://github.com/LavaXD/MyBlog/assets/103249988/878a615c-366e-4521-b57d-79a4a21715f2)

#### 17.5 Swagger2 - Entity config
- Update **Comment** entity using _@ApiModel_ annotation 

```java
package com.js.domain.entity;


import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("comment")
@ApiModel(description = "adding comment entity")
public class Comment  {
    
@TableId
    private Long id;

    

    private String type;
    

    private Long articleId;
    

    private Long rootId;
    

    private String content;
    

    private Long toCommentUserId;
    

    private Long toCommentId;
    
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    

    private Integer delFlag;
    
}
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/444cd04f-9965-4f6e-af92-bb4ebf2233d0)

- Problem of directly adding  _@ApiModel_ annotation in **Comment** entity class:
  - In real developing senario, we can not use **Comment** entity class that is mapped with correspongding table in the database to receive response body from frontend;
  - Instead, DTO entity is used to receive the response body that only has certain attributes
 
- As a result, create **AddCommentDto** under **Shared/com/js/domain/dto**
- Using _@ApiModel_ &  _@ApiModelProperty_ annotation

```java
package com.js.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "DTO entity for add comment")
public class AddCommentDto {

    private Long id;

    @ApiModelProperty(notes = "0 - article comment, 1 - friend link comment")
    private String type;
    
    @ApiModelProperty(notes = "articleId")
    private Long articleId;

    private Long rootId;

    private String content;

    @ApiModelProperty(notes = "the useId of the user creating the target comment")
    private Long toCommentUserId;

    @ApiModelProperty(notes = "the id of the target comment")
    private Long toCommentId;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    
    @ApiModelProperty(notes = "0 - deleted, 1 - not deleted")
    private Integer delFlag;
}

```

- Update parameter of function **addComment** under **CommentController** since **AddCommentDto** we created is used here instead of **Comment**

```java
@PostMapping
    public ResponseResult addComment(@RequestBody AddCommentDto commentDto){
        Comment comment = BeanCopyUtil.copyBean(commentDto, Comment.class);
        return commentService.addComment(comment);
    }
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/c2c79278-3b0b-4f3c-9137-02ae0ca34a57)

#### 17.6 Swagger2 - Document info config
- Create **SwaggerConfig** under **FrontStage/com/js/config**

```java
package com.js.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()

                //only monitor api in com.js.controller
                //.apis(RequestHandlerSelectors.basePackage("com.js.controller"))

                // monitor all APIs
                .apis(RequestHandlerSelectors.any())

                //The incorrect interface address is not displayed, that is, the error path is not monitored
                .paths(Predicates.not(PathSelectors.regex("/error.*")))

                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Shuai Jiang", "https://www.google.com/","849227667@qq.com");
        return new ApiInfoBuilder()
                .title("JS's BLOG")
                .description("My first project")
                .contact(contact)   // contact info
                .version("1.1.0")  // version
                .build();
    }
}
```
![image](https://github.com/LavaXD/MyBlog/assets/103249988/f9837827-a4ed-490e-ad0e-49005e49442c)

## 18 Blog BackStage - Preparation

#### 18.1 Coding & Test
1. Create initialization class under **com/js/** under BackStage module
```java
package com.js;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.js.mapper")
//initialization class
public class BlogAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }
}
```
2. Add **SecurityConfig** & **JwtAuthenticationTokenFilter** in BackStage module to pass security check
   
![image](https://github.com/LavaXD/MyBlog/assets/103249988/ded89497-0def-49a4-a0f0-990ae3119a94)

3. Create entity, service, serviceImpl, mapper and controller for **tag** table

![image](https://github.com/LavaXD/MyBlog/assets/103249988/82f066d3-dbc5-4fdd-b739-977e7d38e1df)

- **_Tag_**
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
@TableName("tag")
public class Tag  {
    
    @TableId
    private Long id;


    private String name;
    

    private Long createBy;
    

    private Date createTime;
    

    private Long updateBy;
    

    private Date updateTime;
    

    private Integer delFlag;
    

    private String remark;
    
}
```
- **_TagMapper_**

```java
package com.js.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.js.domain.entity.Tag;


public interface TagMapper extends BaseMapper<Tag> {

}
```
- **_TagService_**

```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.entity.Tag;


public interface TagService extends IService<Tag> {

}
```

- **_TagServiceImpl_**
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.domain.entity.Tag;
import com.js.mapper.TagMapper;
import com.js.service.TagService;
import org.springframework.stereotype.Service;


@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
```
- **_TagController_**
```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult list(){
        return ResponseResult.okResult(tagService.list());
    }
}
```
4. Test

>http://localhost:8989/content/tag/list

![image](https://github.com/LavaXD/MyBlog/assets/103249988/2606f1a4-749f-4de5-9f3d-79483a4f631e)

**Connection SUCCESS**
		
#### 18.2 Frontend admin system prep
1. Download 'sg-vue-admin.zip' source code file
```text
https://cowtransfer.com/s/341b8ba1d01d48
```

2. Run the project
```text
> d:
> cd/BlogWeb/js-admin-vue
> npm install
> npm run dev
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/0c70dff3-d2b4-4b69-bbb5-f3377c243e17)

3. Visit web page of admin frontend system

```text
http://localhost:81/
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/d1ecb441-820c-43e5-8d80-dade728192de)

## 19. Blog BackStage - Login 

The login function of Backstage is relatively the same as the Frontstage, so the shared portion of code will be directly transferred from Frontstage.

### _AdminLoginService_

```java
package com.js.service;

import com.js.domain.ResponseResult;
import com.js.domain.entity.User;

public interface AdminLoginService {

    //login
    ResponseResult login(User user);

}
```
### _AdminLoginServiceImpl_

```java
package com.js.service.impl;

import com.js.Utils.JwtUtil;
import com.js.Utils.RedisCache;
import com.js.domain.ResponseResult;
import com.js.domain.entity.LoginUser;
import com.js.domain.entity.User;
import com.js.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {
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
        redisCache.setCacheObject("adminLogin:"+userId, loginUser   );

        //encapsulate token and return
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.okResult(map);

    }

//    @Override
//    public ResponseResult logout() {
//        //get token to parse userId
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//
//        //get userId from token
//        Long userId = loginUser.getUser().getId();
//
//        //delete user info from redis
//        redisCache.deleteObject("bloglogin:"+userId);
//        return ResponseResult.okResult();
//    }
}
```
### _AdminLoginController_
```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.domain.entity.User;
import com.js.enums.AppHttpCodeEnum;
import com.js.exception.SystemException;
import com.js.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){

        //if username is null, then throw exception that username is required
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);

    }

//    @PostMapping("/logout")
//    public ResponseResult logout(){
//        return blogLoginService.logout();
//    }

}
```
### _SecurityConfig_
```java
package com.js.config;

import com.js.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

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
                .antMatchers("/user/login").anonymous()
//                .antMatchers("/logout").authenticated()
//                .antMatchers("/user/userInfo").authenticated()
                //.antMatchers("/upload").authenticated()
                //.antMatchers("/content/tag/list").authenticated()
                // apart from above requests, no need of authentication
                .anyRequest().authenticated();

        //config exception handler into security
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        http.logout().disable();

        //first argument is the filter I want to add, second is the filter that is after the filter I added
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //allow cors
        http.cors();
    }
}
```

### Test
- admin Vue

```text
> d:
> cd/BlogWeb/js-admin-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/c3c04100-fef5-46f9-9374-3a62f8791516)

## 20. Blog BackStage - Authority control

#### 20.1 Interface design

- Menu table
  
![image](https://github.com/LavaXD/MyBlog/assets/103249988/efedc239-8b9f-4ba7-9766-113c052d6cf8)

- User can only visit certain functions that is allowed for this user's authority level
- RBAC authority model in SpringSecurity is used here to realize authority control
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/getInfo</td>
		<td>token needed</td>
	</tr>
</table>

Response format: if userId is 1 indicating 'admin' role, then 'roles' field only have admin, 'permission field need to have authorities whose types are 'C' (Menu) or 'F' (Button).
```json
{
	"code":200,
	"data":{
		"permissions":[
			"system:user:list",
            		"system:role:list",
			"system:menu:list",
			"system:user:query",
			"system:user:add"
            		//.......
		],
		"roles":[
			"admin"
		],
		"user":{
			"avatar":"http://r7yxkqloa.bkt.clouddn.com/2022/03/05/75fd15587811443a9a9a771f24da458d.png",
			"email":"23412332@qq.com",
			"id":1,
			"nickName":"sg3334",
			"sex":"1"
		}
	},
	"msg":"operation success"
}
```
#### 20.2 Coding
1. Create **AdminUserInfoVo** that is consistent with response format

```java
package com.js.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AdminUserInfoVo {

    private List<String> permissions;

    private List<String> roles;

    private UserInfoVo user;
}
```

2. Use **EasyCode** to generate entity, service, serviceImpl, mapper for **menu** table

**Menu**
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
@TableName("sys_menu")
public class Menu  {
    
@TableId
    private Long id;


    private String menuName;
    

    private Long parentId;
    

    private Integer orderNum;
    

    private String path;
    

    private String component;
    

    private Integer isFrame;
    

    private String menuType;
    

    private String visible;
    

    private String status;
    

    private String perms;
    

    private String icon;
    

    private Long createBy;
    

    private Date createTime;
    

    private Long updateBy;
    

    private Date updateTime;
    

    private String remark;
    

    private String delFlag;
    
}
```
**MenuService**
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.entity.Menu;

import java.util.List;


public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);
}
```
**MenuServiceImpl**
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.constants.SystemConstants;
import com.js.domain.ResponseResult;
import com.js.domain.entity.Menu;
import com.js.mapper.MenuMapper;
import com.js.service.MenuService;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectPermsByUserId(Long id) { //inquire permission by userId

        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        //if it's 'admin', return all authority (menuType = 'F' or 'C'; status is normal, not deleted)
        if(id == 1L){
            queryWrapper
                    .in(Menu::getMenuType, SystemConstants.MENU,SystemConstants.BUTTON)
                    .eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(queryWrapper);

            //since the list we get above is a list of Menu, so convert it into a list of String
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());

            return perms;
        }

        //else, return corresponding authority
        return getBaseMapper().selectPermsById(id);
    }
}
```
**MenuMapper.xml** under Shared/com/js/resources/mapper
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.js.mapper.MenuMapper">
    <!-- inquire normal user's authority -->
    <select id="selectPermsById" resultType="java.lang.String">
        select
            distinct m.perms
        from
            sys_user_role ur
                left join sys_role_menu rm on ur.role_id = rm.role_id
                left join sys_menu m on m.id = rm.menu_id
        where
            ur.user_id = #{userId} and
            m.menu_type in ('F','C') and
            m.status = 0 and
            m.del_flag = 0
    </select>
</mapper>
```

3. Use **EasyCode** to generate entity, service, serviceImpl, mapper for **role** table

**Role**
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
@TableName("sys_role")
public class Role  {
    
    @TableId
    private Long id;
    

    private String roleName;
    

    private String roleKey;
    

    private Integer roleSort;
    

    private String status;
    

    private String delFlag;
    

    private Long createBy;
    

    private Date createTime;
    

    private Long updateBy;
    

    private Date updateTime;
    

    private String remark;
    
}
```
**RoleService**
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.entity.Role;

import java.util.List;


public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}
```

**RoleServiceImpl**
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.domain.entity.Role;
import com.js.mapper.RoleMapper;
import com.js.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {

        List<String> roleKey = new ArrayList<>();
        //check if it's admin, if yes, role list only have 'admin'
        if(id == 1){
            roleKey.add("admin");
            return roleKey;
        }

        //else, return corresponding role info
        return getBaseMapper().selectRoleKeyById(id);
    }
}
```

**RoleMapper.xml** under Shared/com/js/resources/mapper
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.js.mapper.RoleMapper">

    <!--  get normal user's role  -->
    <select id="selectRoleKeyById" resultType="java.lang.String">
        select
            distinct role_key
        from
            sys_user_role ur
            left join sys_role r on ur.role_id = r.id
        where
            ur.user_id = #{userId} and
            r.status = 0 and
            r.del_flag = 0
    </select>
</mapper>
```
4. Update **AdminLoginController**

```java
package com.js.controller;

import com.js.Utils.BeanCopyUtil;
import com.js.Utils.SecurityUtil;
import com.js.domain.ResponseResult;
import com.js.domain.entity.LoginUser;
import com.js.domain.entity.User;
import com.js.domain.vo.AdminUserInfoVo;
import com.js.domain.vo.UserInfoVo;
import com.js.enums.AppHttpCodeEnum;
import com.js.exception.SystemException;
import com.js.service.AdminLoginService;
import com.js.service.MenuService;
import com.js.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){

        //if username is null, then throw exception that username is required
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);

    }

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){

        //get current login user
        LoginUser loginUser = SecurityUtil.getLoginUser();

        //inquire authority by userId
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());

        //inquire role by userId
        List<String> roles = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //copy bean of user to UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(loginUser.getUser(), UserInfoVo.class);

        //encapsulate and return
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roles,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }
}
```
5. Test

- admin Vue

```text
> d:
> cd/BlogWeb/js-admin-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/0020695e-d415-4e16-aacc-eb8602564cb2)

## 21. Blog BackStage - Dynamic Routing 

#### 21.1 Interface design
- Users with different authorities should see different menus on side bar
  
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/getRouters</td>
		<td>token needed</td>
	</tr>
</table>

- If userId is 1, then 'menus' field should contain all menus whose menu type is 'M' (table of contents) or 'C' (menu)
Response format
```json
{
	"code":200,
	"data":{
		"menus":[
			{
				"children":[],
				"component":"content/article/write/index",
				"createTime":"2022-01-08 11:39:58",
				"icon":"build",
				"id":2023,
				"menuName":"写博文",
				"menuType":"C",
				"orderNum":"0",
				"parentId":0,
				"path":"write",
				"perms":"content:article:writer",
				"status":"0",
				"visible":"0"
			},
			{
				"children":[
					{
						"children":[],
						"component":"system/user/index",
						"createTime":"2021-11-12 18:46:19",
						"icon":"user",
						"id":100,
						"menuName":"用户管理",
						"menuType":"C",
						"orderNum":"1",
						"parentId":1,
						"path":"user",
						"perms":"system:user:list",
						"status":"0",
						"visible":"0"
					},
					{
						"children":[],
						"component":"system/role/index",
						"createTime":"2021-11-12 18:46:19",
						"icon":"peoples",
						"id":101,
						"menuName":"角色管理",
						"menuType":"C",
						"orderNum":"2",
						"parentId":1,
						"path":"role",
						"perms":"system:role:list",
						"status":"0",
						"visible":"0"
					},
					{
						"children":[],
						"component":"system/menu/index",
						"createTime":"2021-11-12 18:46:19",
						"icon":"tree-table",
						"id":102,
						"menuName":"菜单管理",
						"menuType":"C",
						"orderNum":"3",
						"parentId":1,
						"path":"menu",
						"perms":"system:menu:list",
						"status":"0",
						"visible":"0"
					}
				],
				"createTime":"2021-11-12 18:46:19",
				"icon":"system",
				"id":1,
				"menuName":"系统管理",
				"menuType":"M",
				"orderNum":"1",
				"parentId":0,
				"path":"system",
				"perms":"",
				"status":"0",
				"visible":"0"
			}
		]
	},
	"msg":"operation success"
}
```

#### 21.2 Coding 

#### 21.3 Test
- admin Vue

```text
> d:
> cd/BlogWeb/js-admin-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/eb2e57d7-b88c-4c29-b3ea-628d9634e6b6)

## 22. Blog BackStage - Logout

#### 22.1 Interface Design

<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>POST</td>
		<td>/user/logOut</td>
		<td>token needed</td>
	</tr>
</table>

Response format
```json
{
    "code": 200,
    "msg": "operation success"
}
```

#### 22.2 Coding
Logout function is also similar to the frontstage 

1. Update **AdminLoginService**
```java
package com.js.service;

import com.js.domain.ResponseResult;
import com.js.domain.entity.User;

public interface AdminLoginService {

    //login
    ResponseResult login(User user);

    //logout
    ResponseResult logOut();
}
```
2. Update **AdminLoginServiceImpl**
```java
package com.js.service.impl;

import com.js.Utils.JwtUtil;
import com.js.Utils.RedisCache;
import com.js.Utils.SecurityUtil;
import com.js.domain.ResponseResult;
import com.js.domain.entity.LoginUser;
import com.js.domain.entity.User;
import com.js.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {
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
        redisCache.setCacheObject("adminLogin:"+userId, loginUser   );

        //encapsulate token and return
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.okResult(map);

    }

    @Override
    public ResponseResult logOut() {

        //get current userId
        Long userId = SecurityUtil.getUserId();

        //delete user token in redis
        redisCache.deleteObject("adminLogin:"+userId);

        return ResponseResult.okResult();
    }

}
```

3. Update **AdminLoginController** 

```java
package com.js.controller;

import com.js.Utils.BeanCopyUtil;
import com.js.Utils.RedisCache;
import com.js.Utils.SecurityUtil;
import com.js.domain.ResponseResult;
import com.js.domain.entity.LoginUser;
import com.js.domain.entity.Menu;
import com.js.domain.entity.User;
import com.js.domain.vo.AdminUserInfoVo;
import com.js.domain.vo.RoutersVo;
import com.js.domain.vo.UserInfoVo;
import com.js.enums.AppHttpCodeEnum;
import com.js.exception.SystemException;
import com.js.service.AdminLoginService;
import com.js.service.MenuService;
import com.js.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisCache redisCache;

    //-------------------------------------- login -------------------------------------------
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){

        //if username is null, then throw exception that username is required
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);
    }

    //-------------------------------------- logout -------------------------------------------
    @PostMapping("/user/logout")
    public ResponseResult logout(){

        return adminLoginService.logOut();
    }

    //-------------------------------------- getInfo -------------------------------------------
    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){

        //get current login user
        LoginUser loginUser = SecurityUtil.getLoginUser();

        //inquire authority by userId
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());

        //inquire role by userId
        List<String> roles = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //copy bean of user to UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(loginUser.getUser(), UserInfoVo.class);

        //encapsulate and return
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roles,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    //-------------------------------------- getRouters -------------------------------------------
    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters(){

        //inquire menu, result should be in form of tree (hierarchy)
        Long userId = SecurityUtil.getUserId();
        List<Menu> menus = menuService.selectRouterMenuTreeById(userId);

        //encap and return
        return ResponseResult.okResult(new RoutersVo(menus));
    }

}
```
#### 22.3 Test

- admin Vue

```text
> d:
> cd/BlogWeb/js-admin-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```
![image](https://github.com/LavaXD/MyBlog/assets/103249988/b873dae4-d4e5-4205-a6f2-d1e4e5e30df4)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/3aaefbda-65a1-4274-9900-9d858889d762)


## 23. Blog BackStage - Article Tag
### 23.1 Query tag list
#### I. Interface design 
In order to facilitate the later management of the article, an article can have multiple tags. In the backstage, the function of paging query tag is required, and the corresponding article can be paging query according to the tag name
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/content/tag/list</td>
		<td>token needed</td>
	</tr>
</table>

Request parameter 
```text
pageNum, pageSize, name, remark
```
Response format
```json
{
	"code":200,
	"data":{
		"rows":[
			{
				"id":4,
				"name":"Java",
				"remark":"sdad"
			}
		],
		"total":1
	},
	"msg":"operation success"
}
```
#### II. Coding
1. Update **TagController**
```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.vo.PageVo;
import com.js.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){

        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }
}
```
2. Update **TagService**
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.entity.Tag;
import com.js.domain.vo.PageVo;


public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);
}
```
3. Update **TagServiceImpl**
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.entity.Tag;
import com.js.domain.vo.PageVo;
import com.js.mapper.TagMapper;
import com.js.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {

        //paging query
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        //if tagListDto's name has value, then execute query, if name does not have value, it will not execute eq
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark, tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);

        //encap and return
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());

        return ResponseResult.okResult(pageVo);
    }
}
```
4. Create **TagListDto**
```java
package com.js.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagListDto {

    private String name;

    private String remark;
}
```

#### III. Test
- admin Vue

```text
> d:
> cd/BlogWeb/js-admin-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/f3b79897-3758-4d5c-b274-d9b959662c84)


### 23.2 Add tag 

#### I. Interface design
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/content/tag/list</td>
		<td>token needed</td>
	</tr>
</table>

Response body
```text
{"name":"tagName","remark":"tag's remark"}
```
Response format
```json
{
	"code":200,
	"msg":"operation success"
}
```

#### II. Coding

1. Update **TagController**
```java
package com.js.controller;

import com.js.Utils.BeanCopyUtil;
import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.entity.Tag;
import com.js.domain.vo.PageVo;
import com.js.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){

        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody TagListDto tagListDto){
        Tag tag = BeanCopyUtil.copyBean(tagListDto, Tag.class);
        return tagService.addTag(tag);
    }
}
```
2. Update **TagService**
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.entity.Tag;
import com.js.domain.vo.PageVo;


public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(Tag tag);
}
```
3. Update **TagServiceImpl**
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.entity.Tag;
import com.js.domain.vo.PageVo;
import com.js.enums.AppHttpCodeEnum;
import com.js.exception.SystemException;
import com.js.mapper.TagMapper;
import com.js.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    //----------------------------------------------- Query tag list -----------------------------------------
    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {

        //paging query
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        //if tagListDto's name has value, then execute query, if name does not have value, it will not execute eq
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark, tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);

        //encap and return
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    //----------------------------------------------- Add tag -----------------------------------------
    @Override
    public ResponseResult addTag(Tag tag) {
        // check if tag name is no null
        if(!StringUtils.hasText(tag.getName())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(tag);
        return ResponseResult.okResult();
    }
}
```

#### III. Test

- admin Vue

```text
> d:
> cd/BlogWeb/js-admin-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/acec8a10-2d2d-4773-8448-20b86ba509b2)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/6d99e41a-bd00-4c40-87cc-0241aa77d2ba)

### 23.3 Delete tag 

#### I. Interface design

![image](https://github.com/LavaXD/MyBlog/assets/103249988/dff098e6-e424-4d7c-afec-352d12bf8f04)

The request parameter is in the request path
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>DELETE</td>
		<td>/content/tag/{id}</td>
		<td>token needed</td>
	</tr>
</table>

Response format
```json
{
	"code":200,
	"msg":"operation success"
}
```

#### II. Coding
1. Update **TagController**
```java
package com.js.controller;

import com.js.Utils.BeanCopyUtil;
import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.entity.Tag;
import com.js.domain.vo.PageVo;
import com.js.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){

        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody TagListDto tagListDto){
        Tag tag = BeanCopyUtil.copyBean(tagListDto, Tag.class);
        return tagService.addTag(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable Long id){
        return tagService.deleteTag(id);
    }

}
```
2. Update **TagService**
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.entity.Tag;
import com.js.domain.vo.PageVo;


public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(Tag tag);

    ResponseResult deleteTag(Long id);
}
```
3. Update **TagServiceImpl**
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.constants.SystemConstants;
import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.entity.Tag;
import com.js.domain.vo.PageVo;
import com.js.enums.AppHttpCodeEnum;
import com.js.exception.SystemException;
import com.js.mapper.TagMapper;
import com.js.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    //----------------------------------------------- Query tag list -----------------------------------------
    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {

        //paging query
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        //if tagListDto's name has value, then execute query, if name does not have value, it will not execute eq
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark, tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);

        //encap and return
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    //----------------------------------------------- Add tag -----------------------------------------
    @Override
    public ResponseResult addTag(Tag tag) {
        // check if tag name is no null
        if(!StringUtils.hasText(tag.getName())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(tag);
        return ResponseResult.okResult();
    }

    //----------------------------------------------- Delete tag -----------------------------------------
    @Override
    public ResponseResult deleteTag(Long id) {

        //update del_flag of tag whose id = {id} from 0 to 1
        getBaseMapper().deleteTag(id);

        return ResponseResult.okResult();
    }
}
```
4. Update **TagMapper**
```java
package com.js.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.js.domain.entity.Tag;


public interface TagMapper extends BaseMapper<Tag> {

    void deleteTag(Long id);
}
```
5. Create **TagMapper.xml** to write logical delete query
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.js.mapper.TagMapper">

    <update id="deleteTag">
        update
            tag
        set
            del_flag = 1
        where
            id = #{id}
    </update>

</mapper>
```
#### III. Test
- admin Vue

```text
> d:
> cd/BlogWeb/js-admin-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/95861010-274b-4ace-96c9-32b8101278d7)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/a1b7bbf5-a15e-448e-9655-fca120c9e694)


### 23.4 Update tag 

#### I. Interface design
1. Get tag info by id. When the user clicks the update button, it is triggered and displayed in the pop-up box
Path variable is in request path
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/content/tag/{id}</td>
		<td>token needed</td>
	</tr>
</table>

Response format
```json
{
"code":200,
	"data":{
        "id":4,
        "name":"tagName",
        "remark":"remark"
	},
"msg":"operation success"
}
```

2. Update tag info
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>PUT</td>
		<td>/content/tag</td>
		<td>token needed</td>
	</tr>
</table>

Request body
```json
{
    "id":"7",
    "name":"tagName",
    "remark":"remark"
}
```
Response format
```json
{
	"code":200,
	"msg":"operation success"
}
```
#### II. Coding

1. Create **TagVo**
```java
package com.js.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo {

    @TableId
    private Long id;

    private String name;

    private String remark;
}
```
2. Update **TagController**
```java
package com.js.controller;

import com.js.Utils.BeanCopyUtil;
import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.entity.Tag;
import com.js.domain.vo.PageVo;
import com.js.domain.vo.TagVo;
import com.js.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody TagListDto tagListDto){
        Tag tag = BeanCopyUtil.copyBean(tagListDto, Tag.class);
        return tagService.addTag(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable Long id){
        return tagService.deleteTag(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getTagInfo(@PathVariable Long id){
        return tagService.getTagInfo(id);
    }

    @PutMapping
    public ResponseResult updateTag(@RequestBody TagVo tagVo){
        return tagService.updateTag(tagVo);
    }
}
```
3. Update **TagService**
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.entity.Tag;
import com.js.domain.vo.PageVo;
import com.js.domain.vo.TagVo;


public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(Tag tag);

    ResponseResult deleteTag(Long id);

    ResponseResult getTagInfo(Long id);

    ResponseResult updateTag(TagVo tagVo);
}
```
4. Update **TagServiceImpl**
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.Utils.BeanCopyUtil;
import com.js.constants.SystemConstants;
import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.entity.Tag;
import com.js.domain.vo.PageVo;
import com.js.domain.vo.TagVo;
import com.js.enums.AppHttpCodeEnum;
import com.js.exception.SystemException;
import com.js.mapper.TagMapper;
import com.js.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    //----------------------------------------------- Query tag list -----------------------------------------
    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {

        //paging query
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        //if tagListDto's name has value, then execute query, if name does not have value, it will not execute eq
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark, tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);

        //encap and return
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    //----------------------------------------------- Add tag -----------------------------------------
    @Override
    public ResponseResult addTag(Tag tag) {
        // check if tag name is no null
        if(!StringUtils.hasText(tag.getName())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        //mybatisplus' method to directly save tag into database
        save(tag);
        return ResponseResult.okResult();
    }

    //----------------------------------------------- Delete tag -----------------------------------------
    @Override
    public ResponseResult deleteTag(Long id) {

        //update del_flag of tag whose id = {id} from 0 to 1
        getBaseMapper().deleteTag(id);

        return ResponseResult.okResult();
    }

    //----------------------------------------------- Update tag -----------------------------------------
    @Override
    public ResponseResult getTagInfo(Long id) {

        //query the tag whose id = {id}, get name, remark
        Tag tag = getById(id);

        //convert Tag into TagVo with only three attributes: id, name, remark
        TagVo tagVo = BeanCopyUtil.copyBean(tag, TagVo.class);
        return ResponseResult.okResult(tagVo);
    }
    @Override
    public ResponseResult updateTag(TagVo tagVo) {
        if(!StringUtils.hasText(tagVo.getName())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        Tag tag = BeanCopyUtil.copyBean(tagVo, Tag.class);
        updateById(tag);
        return ResponseResult.okResult();
    }


}
```

#### III. Test
- admin Vue

```text
> d:
> cd/BlogWeb/js-admin-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```
- _getTagInfo_
![image](https://github.com/LavaXD/MyBlog/assets/103249988/eecec019-790d-49e6-bb26-c5482ac60c4f)

- _UpdateTag_
![image](https://github.com/LavaXD/MyBlog/assets/103249988/2431e9af-2c7a-4f5f-ad82-62eed8936782)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/f2658e00-8147-42d7-ba8b-107b81e00ac5)

## 24. Blog BackStage - Writing Blog

### 24.1 Query category list
#### I. Interface design
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/content/category/listAllCategory</td>
		<td>token needed</td>
	</tr>
</table>

Response format
```json
{
	"code":200,
	"data":[
		{
			"description":"wsd",
			"id":1,
			"name":"java"
		},
		{
			"description":"wsd",
			"id":2,
			"name":"PHP"
		}
	],
	"msg":"operation success"
}
```
#### II. Coding
1. Update **CategoryVo**
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
    private String description;
}
```
2. Create **CategoryController** in backstage module
```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.domain.vo.CategoryVo;
import com.js.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult<CategoryVo> listAllCategory(){
        return categoryService.listAllCategory();
    }
}
```
3. Update **CategoryService**
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.vo.CategoryVo;


public interface CategoryService extends IService<com.js.domain.entity.Category> {

    ResponseResult getCategoryList();

    ResponseResult<CategoryVo> listAllCategory();
}
```
4. Update **CategoryServiceImpl**
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
import com.js.domain.entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

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
        List<Category> categories = listByIds(categoryIds);

        categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        //encapsulate vo
        List<CategoryVo> categoryVos = BeanCopyUtil.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }

    // ---------------------------------- getAllCategories for writing blog ---------------------------
    @Override
    public ResponseResult<CategoryVo> listAllCategory() {

        //query all categories whose status is normal
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus,SystemConstants.STATUS_NORMAL);
        List<Category> list = list(queryWrapper);

        //convert to categoryVos
        List<CategoryVo> categoryVos = BeanCopyUtil.copyBeanList(list, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }
}
```
#### III. Test
- admin Vue

```text
> d:
> cd/BlogWeb/js-admin-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/22e56f7a-d42b-40b4-a33e-a9fbfc5dfb27)

### 24.2 Query tag list
#### I. Interface design
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/content/tag/listAllTag</td>
		<td>token needed</td>
	</tr>
</table>

Response format
```json
{
	"code":200,
	"data":[
		{
			"id":1,
			"name":"Mybatis"
		},
		{
			"id":4,
			"name":"Java"
		}
	],
	"msg":"operation success"
}
```
#### II. Coding
1. Update **TagController**
```java
package com.js.controller;

import com.js.Utils.BeanCopyUtil;
import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.entity.Tag;
import com.js.domain.vo.PageVo;
import com.js.domain.vo.TagVo;
import com.js.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody TagListDto tagListDto){
        Tag tag = BeanCopyUtil.copyBean(tagListDto, Tag.class);
        return tagService.addTag(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable Long id){
        return tagService.deleteTag(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getTagInfo(@PathVariable Long id){
        return tagService.getTagInfo(id);
    }

    @PutMapping
    public ResponseResult updateTag(@RequestBody TagVo tagVo){
        return tagService.updateTag(tagVo);
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        return tagService.getAllTag();
    }
}
```
2. Update **TagService**
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.entity.Tag;
import com.js.domain.vo.PageVo;
import com.js.domain.vo.TagVo;


public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(Tag tag);

    ResponseResult deleteTag(Long id);

    ResponseResult getTagInfo(Long id);

    ResponseResult updateTag(TagVo tagVo);

    ResponseResult getAllTag();
}
```
3. Update **TagServiceImpl**
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.Utils.BeanCopyUtil;
import com.js.constants.SystemConstants;
import com.js.domain.ResponseResult;
import com.js.domain.dto.TagListDto;
import com.js.domain.entity.Tag;
import com.js.domain.vo.PageVo;
import com.js.domain.vo.TagVo;
import com.js.enums.AppHttpCodeEnum;
import com.js.exception.SystemException;
import com.js.mapper.TagMapper;
import com.js.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    //----------------------------------------------- Query tag list -----------------------------------------
    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {

        //paging query
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        //if tagListDto's name has value, then execute query, if name does not have value, it will not execute eq
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark, tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);

        //encap and return
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    //----------------------------------------------- Add tag -----------------------------------------
    @Override
    public ResponseResult addTag(Tag tag) {
        // check if tag name is no null
        if(!StringUtils.hasText(tag.getName())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        //mybatisplus' method to directly save tag into database
        save(tag);
        return ResponseResult.okResult();
    }

    //----------------------------------------------- Delete tag -----------------------------------------
    @Override
    public ResponseResult deleteTag(Long id) {

        //update del_flag of tag whose id = {id} from 0 to 1
        getBaseMapper().deleteTag(id);

        return ResponseResult.okResult();
    }

    //----------------------------------------------- Update tag -----------------------------------------
    @Override
    public ResponseResult getTagInfo(Long id) {

        //query the tag whose id = {id}, get name, remark
        Tag tag = getById(id);

        //convert Tag into TagVo with only three attributes: id, name, remark
        TagVo tagVo = BeanCopyUtil.copyBean(tag, TagVo.class);
        return ResponseResult.okResult(tagVo);
    }
    @Override
    public ResponseResult updateTag(TagVo tagVo) {
        if(!StringUtils.hasText(tagVo.getName())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        Tag tag = BeanCopyUtil.copyBean(tagVo, Tag.class);
        updateById(tag);
        return ResponseResult.okResult();
    }

    //----------------------------------------------- get all tags -----------------------------------------
    @Override
    public ResponseResult getAllTag() {

        //get all tags
        List<Tag> tags = list();

        //convert to tagVo
        List<TagVo> tagVos = BeanCopyUtil.copyBeanList(tags, TagVo.class);
        return ResponseResult.okResult(tagVos);
    }


}
```
#### III. Test
- admin Vue

```text
> d:
> cd/BlogWeb/js-admin-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/8823153b-e8b8-437c-8066-dd6f7c7ad1fb)

### 24.3 Image uploading
#### I. Interface design
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>POST</td>
		<td>/upload</td>
		<td>token needed</td>
	</tr>
</table>

Request head
```java
Content-Type ：multipart/form-data;
```

Response format
```json
{
    "code": 200,
    "data": "url",
    "msg": "operation success"
}
```
#### II. Coding
1. Create **UploadController** under backstage module

```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(@RequestParam("img")MultipartFile multipartFile){
        try{
            return uploadService.uploadImg(multipartFile);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("img uploading fail");
        }

    }

}
```
#### III. Test

![image](https://github.com/LavaXD/MyBlog/assets/103249988/bfa519a6-496d-44db-8a9c-36902e3558e7)

### 24.4 Add new article
#### I. Interface design
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>POST</td>
		<td>/content/article</td>
		<td>token needed</td>
	</tr>
</table>

Request body
```json
{
    "title":"测试新增博文",
    "thumbnail":"https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/08/21/4ceebc07e7484beba732f12b0d2c43a9.png",
    "isTop":"0",
    "isComment":"0",
    "content":"# 一级标题\n## 二级标题\n![Snipaste_20220228_224837.png](https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/08/21/c3af554d4a0f4935b4073533a4c26ee8.png)\n正文",
    "tags":[
        1,
        4
    ],
    "categoryId":1,
    "summary":"哈哈",
    "status":"1"
}
```

Response format
```json
{
	"code":200,
	"msg":"operation success"
}
```
#### II. Coding
1. Create **AddArticleDto** for responsing attributes
```java
package com.js.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddArticleDto {

    private String title;

    private String content;

    private String summary;

    private Long categoryId;

    private String thumbnail;

    //1 - yes, 0 - no
    private String isTop;

    //1 - normal, 0 - draft
    private String status;

    //1 - yes, 0 - no
    private String isComment;

    private List<Long> tags;
}
```
2. Add @TableField annotation in **Article** for field auto fill 
```java
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
```
3. Create **ArticleController** in backstage module
```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.domain.dto.AddArticleDto;
import com.js.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult addArticle(@RequestBody AddArticleDto addArticleDto){
        return articleService.addArticle(addArticleDto);
    }
}
```
3. Update **ArticleService** in shared module
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.dto.AddArticleDto;
import com.js.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult addArticle(AddArticleDto addArticleDto);
}
```
4. Create **ArticleTag** entity of table article_tag and its related mapper, service, serviceImpl
- Since an article can have multiple tags, a tag can be used by multiple articles, so a middle entity AritcleTag is needed
```java
package com.js.domain.entity;

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
@TableName("article_tag")
public class ArticleTag  {
    
    @TableId
    private Long articleId;
    
    @TableId
    private Long tagId;

    
}
```

5. Update **ArticleServiceImpl**
```java
 @Autowired
    private ArticleTagService articleTagService;

    //------------------------------------------- add new article --------------------------------------------------
    @Override
    @Transactional // using atomicity of transaction to ensure that saving article operation and saving connection between article and tags are either both successful or not
    public ResponseResult addArticle(AddArticleDto addArticleDto) {

        //add article
        Article article = BeanCopyUtil.copyBean(addArticleDto, Article.class);
        save(article);

        //transfer tagId to a ArticleTag instance
        List<ArticleTag> articleTags = addArticleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(),tagId))
                .collect(Collectors.toList());

        //add connection between article and tags
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }
```
#### III. Test
![image](https://github.com/LavaXD/MyBlog/assets/103249988/bb253948-d778-48fe-a9bf-4f4197b8f584)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/b192f084-ffd1-4da4-ba97-c76f422b732f)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/49538294-0666-4299-ab92-a9cfdf0cbce3)

## 25. Export category excel file

### 25.1 Interface design
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/content/category/export</td>
		<td>token needed</td>
	</tr>
</table>

- If successful, excel file will be downloaded
- If fail, response format:
```json
{
	"code":500,
	"msg":"system error"
}
```

- **EasyExcel** is used here to export excel file
> official website: https://github.com/alibaba/easyexcel

> quickstart: https://easyexcel.opensource.alibaba.com/docs/current/quickstart/write#%E7%A4%BA%E4%BE%8B%E4%BB%A3%E7%A0%81-1

### 25.2 Coding
1. Update **WebUtils**
```java
package com.js.Utils;

import org.springframework.web.context.request.RequestContextHolder;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WebUtils {
    /**
     * Render the string to the client
     *
     * @param response render object
     * @param string String to be rendered
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

    //easyExcel export
    public static void setDownLoadHeader(String filename, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fname= URLEncoder.encode(filename,"UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition","attachment; filename="+fname);
    }
}
```
2. Create **ExcelCategoryVo**
```java
package com.js.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelCategoryVo {

    @ExcelProperty("categoryName")
    private String name;

    @ExcelProperty("description")
    private  String description;

    @ExcelProperty("0 - normal, 1 - hidden")
    private String status;
}
```
3. Update **CategoryService**
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.vo.CategoryVo;


public interface CategoryService extends IService<com.js.domain.entity.Category> {

    ResponseResult getCategoryList();

    ResponseResult<CategoryVo> listAllCategory();
}
```
4. Update **CategoryController**
```java
package com.js.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.js.Utils.BeanCopyUtil;
import com.js.Utils.WebUtils;
import com.js.domain.ResponseResult;
import com.js.domain.entity.Category;
import com.js.domain.vo.CategoryVo;
import com.js.domain.vo.ExcelCategoryVo;
import com.js.enums.AppHttpCodeEnum;
import com.js.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult<CategoryVo> listAllCategory(){
        return categoryService.listAllCategory();
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response){

        try {
            //config request head of the file
            WebUtils.setDownLoadHeader("category.xlsx",response);

            //get exporting data
            List<Category> categories = categoryService.list();
            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtil.copyBeanList(categories, ExcelCategoryVo.class);

            //write data into excel
            EasyExcel
                    .write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE)
                    .sheet("article categories")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            //if exception occur, respond json data
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }
}
```

### 25.3 Test
- admin Vue

```text
> d:
> cd/BlogWeb/js-admin-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/2c0d8d24-2168-4747-a925-f6caf15872bb)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/70559bcc-e64d-4f85-a289-ed8d28fe6d83)

## 26. Authority Control

### 26.1 Interface requirments
- Through the customized permission check, to achieve permission control of the interface 'export category'

### 26.2 Coding
1. Update **SecurityConfig** - add security annotation
```java
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //for @PreAuthorize() to work
public class SecurityConfig extends WebSecurityConfigurerAdapter {
```
2. Update **UserDetailsImpl** - add permission info encapsulation
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.js.constants.SystemConstants;
import com.js.domain.entity.LoginUser;
import com.js.domain.entity.User;
import com.js.mapper.MenuMapper;
import com.js.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

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
        //TODO only if its backstage, query authority info encapsulation is needed
        //the type of admin user in backstage is 1
        if(user.getType().equals(SystemConstants.ADMIN)){
            List<String> perms = menuMapper.selectPermsById(user.getId());
            return new LoginUser(user,perms);
        }
        return new LoginUser(user,null);
    }
}
```
3. Create **PermissionService**
```java
package com.js.service.impl;

import com.js.Utils.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ps")
public class PermissionService {

    /**
     * check if current user has certain permission
     * @param permission
     * @return
     */
    public boolean hasPermissions(String permission){

        //if it's super admin (userId = 1), return true
        if(SecurityUtil.isAdmin()){
            return true;
        }

        //else, get current user's permission, check if the permission is existed
        List<String> permissions = SecurityUtil.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }
}
```
4. Update **export** in **CategoryController** using security annotation
```java
    @PreAuthorize("@ps.hasPermissions('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response){

        try {
            //config request head of the file
            WebUtils.setDownLoadHeader("category.xlsx",response);

            //get exporting data
            List<Category> categories = categoryService.list();
            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtil.copyBeanList(categories, ExcelCategoryVo.class);

            //write data into excel
            EasyExcel
                    .write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE)
                    .sheet("article categories")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            //if exception occur, respond json data
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }
```
### 26.3 Test

![image](https://github.com/LavaXD/MyBlog/assets/103249988/06395ac9-bae6-4bab-aeb3-dfb545a90969)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/e4aded76-ef89-46c7-9a16-0deed8c4b7e9)

## 27. Blog BackStage - Article list

### 27.1 Query article
#### I. Interface design
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/content/article/list</td>
		<td>token needed</td>
	</tr>
</table>

Request parameter
```text
pageNum, pageSize, title, summary
```
Response format
```json
{
	"code":200,
	"data":{
		"rows":[
			{
				"categoryId":"1",
				"content":"content",
				"createTime":"2023-08-10 07:20:11",
				"id":"1",
				"isComment":"0",
				"isTop":"1",
				"status":"0",
				"summary":"summary",
				"thumbnail":"thumbnail.png|jpg",
				"title":"title",
				"viewCount":"viewCount"
			}
		],
		"total":"1"
	},
	"msg":"operation success"
}
```
#### II. Coding
1. Update **ArticleController**
```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.domain.dto.AddArticleDto;
import com.js.domain.entity.Article;
import com.js.domain.vo.PageVo;
import com.js.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult addArticle(@RequestBody AddArticleDto addArticleDto){
        return articleService.addArticle(addArticleDto);
    }

    @GetMapping("/list")
    public ResponseResult<PageVo> listArticle(Article article, Integer pageNum, Integer pageSize){
        return articleService.listArticle(article,pageNum,pageSize);
    }
}
```
2. Update **ArticleService**
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.dto.AddArticleDto;
import com.js.domain.entity.Article;
import com.js.domain.vo.PageVo;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult addArticle(AddArticleDto addArticleDto);

    ResponseResult<PageVo> listArticle(Article article, Integer pageNum, Integer pageSize);

}
```
3. Update **ArticleServiceImpl**
```java
//------------------------------------------- admin list article ------------------------------------------------
    @Override
    public ResponseResult<PageVo> listArticle(Article article, Integer pageNum, Integer pageSize) {

        //for fuzzy query
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .like(StringUtils.hasText(article.getTitle()), Article::getTitle, article.getTitle())
                .like(StringUtils.hasText(article.getSummary()),Article::getSummary, article.getSummary());

        //set page attributes
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);

        //convert page to pageVo
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
```

#### III. Test
- admin Vue

```text
> d:
> cd/BlogWeb/js-admin-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```
![image](https://github.com/LavaXD/MyBlog/assets/103249988/045f226e-01f5-407d-beb0-16fbd60e4d8b)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/c5b71cd4-ecd8-45df-a24e-d11a75a25e32)

### 27.2 Update article
#### I. Interface design
1. Jump to the updating page where detail info of article is shown
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/content/article/{id}</td>
		<td>token needed</td>
	</tr>
</table>

Path parameter
> /{id}

Response format
```json
{
	"code":200,
	"data":{
		"categoryId":"1",
		"content":"content",
		"createBy":"1",
		"createTime":"2023-08-28 15:15:46",
		"delFlag":0,
		"id":"10",
		"isComment":"0",
		"isTop":"1",
		"status":"0",
		"summary":"summary",
		"tags":[
			"1",
			"4",
			"5"
		],
		"thumbnail":"thumbnail.png|jpg",
		"title":"title",
		"updateBy":"1",
		"updateTime":"2022-08-28 15:15:46",
		"viewCount":"0"
	},
	"msg":"operation success"
}
```
2. Update article info and save into database
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>PUT</td>
		<td>/content/article</td>
		<td>token needed</td>
	</tr>
</table>

Request body
```json
{
    "categoryId":"1",
    "content":"content",
    "createBy":"1",
    "createTime":"2023-08-28 15:15:46",
    "delFlag":0,
    "id":"10",
    "isComment":"0",
    "isTop":"1",
    "status":"0",
    "summary":"summary",
    "tags":[
        "1",
        "4",
        "5"
    ],
    "thumbnail":"thumbnail.png|jpg",
    "title":"title",
    "updateBy":"1",
    "updateTime":"2022-08-28 15:15:46",
    "viewCount":"0"
}
```
Response format
```json
{
	"code":200,
	"msg":"操作成功"
}
```
#### II. Coding
1. Update **ArticleController**
```java
@GetMapping("/{id}")
    public ResponseResult<Article> jumpToUpdate(@PathVariable Long id){
        return articleService.jumpToUpdate(id);
    }

    @PutMapping
    public ResponseResult updateArticle(@RequestBody Article article){
        return articleService.updateArticle(article);
    }
```
2. Update **ArticleService**
```java
	ResponseResult<Article> jumpToUpdate(Long id);

    	ResponseResult updateArticle(Article article);
```
3. Update **ArticleServiceImpl**
```java
//------------------------------------------- admin update article ------------------------------------------------
    @Override
    public ResponseResult<Article> jumpToUpdate(Long id) {

        //get article content by id
        Article article = getById(id);

        //get corresponding tags of the article
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId,article.getId());
        List<ArticleTag> articleTags = articleTagService.list(queryWrapper);

        //manipulate articleTags list, convert it into a list of tagId
        List<Long> tagIds = articleTags.stream()
                .map(articleTag -> articleTag.getTagId())
                .collect(Collectors.toList());

        article.setTags(tagIds);
        return ResponseResult.okResult(article);
    }

    @Override
    public ResponseResult updateArticle(Article article) {

        //update this article into database
        updateById(article);

        //delete original tag & article connection
        LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTag::getArticleId,article.getId());
        articleTagService.remove(wrapper);

        //update new connection into database
        List<ArticleTag> articleTags = article.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);

        return ResponseResult.okResult();
    }
```
#### III. Test
- Blog Vue

```text
> d:
> cd/BlogWeb/js-blog-vue
> npm run dev
```
- admin Vue

```text
> d:
> cd/BlogWeb/js-admin-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```

![image](https://github.com/LavaXD/MyBlog/assets/103249988/2f269a89-f07a-409b-87c1-1eac02e85cfa)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/b9ab7107-568e-49a6-961a-2038ecfee500)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/d9a9d9fc-a318-4817-a034-1f00a8119d53)


### 27.3 Delete article
#### I. Interface design
<table>
	<tr>
		<td>Request Method</td>
		<td>Request Path</td>
		<td>Request Head</td>
	</tr>
	<tr>
		<td>DELETE</td>
		<td>/content/article/{id}</td>
		<td>token needed</td>
	</tr>
</table>

Response format
```json
{
	"code":200,
	"msg":"操作成功"
}
```
#### II. Coding
1. Update **ArticleController**
```java
@DeleteMapping("/{id}")
    public ResponseResult deleteArticle(@PathVariable Long id){
        return articleService.deleteArticle(id);
    }
```
2. Update **ArticleService**
```java
	ResponseResult deleteArticle(Long id);
```
3. Update **ArticleServiceImpl**
```java
    //------------------------------------------- admin delete article ------------------------------------------------
    @Override
    public ResponseResult deleteArticle(Long id) {

        removeById(id);
        return ResponseResult.okResult();
    }
```
#### III. Test
- admin Vue

```text
> d:
> cd/BlogWeb/js-admin-vue
> npm run dev
```
- redis

```text
> d:
> cd/redis
> redis-server.exe redis.windows.conf
```
![image](https://github.com/LavaXD/MyBlog/assets/103249988/230303b0-36b3-48fc-80fa-2b2e08c763db)

![image](https://github.com/LavaXD/MyBlog/assets/103249988/4304d50c-12b1-40d5-8316-5b62a9dab34e)

## 28. Blog Backstage - Menu CRUD
### MenuController
```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.domain.dto.MenuDto;
import com.js.domain.entity.Menu;

import com.js.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult listMenu(MenuDto menuDto){
        return menuService.listMenu(menuDto);
    }

    @PostMapping
    public ResponseResult addMenu(@RequestBody Menu menu){
        return menuService.addMenu(menu);
    }

    @GetMapping("/{id}")
    public ResponseResult jumpToUpdate(@PathVariable Long id){
        return menuService.jumpToUpdate(id);
    }

    @PutMapping
    public ResponseResult updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteMenu(@PathVariable Long id){
        return menuService.deleteMenu(id);
    }
}
```
### MenuService
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.dto.MenuDto;
import com.js.domain.entity.Menu;

import java.util.List;


public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeById(Long userId);

    ResponseResult listMenu(MenuDto menuDto);

    ResponseResult addMenu(Menu menu);

    ResponseResult jumpToUpdate(Long id);

    ResponseResult updateMenu(Menu menu);

    ResponseResult deleteMenu(Long id);
}
```
### MenuServiceImpl
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.Utils.BeanCopyUtil;
import com.js.Utils.SecurityUtil;
import com.js.constants.SystemConstants;
import com.js.domain.ResponseResult;
import com.js.domain.dto.MenuDto;
import com.js.domain.entity.Menu;
import com.js.domain.vo.MenuVo;
import com.js.enums.AppHttpCodeEnum;
import com.js.mapper.MenuMapper;
import com.js.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectPermsByUserId(Long id) { //inquire permission by userId

        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        //if it's 'admin', return all authority (menuType = 'F' or 'C'; status is normal, not deleted)
        if(id == 1L){
            queryWrapper
                    .in(Menu::getMenuType, SystemConstants.MENU,SystemConstants.BUTTON)
                    .eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(queryWrapper);

            //since the list we get above is a list of Menu, so convert it into a list of String
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());

            return perms;
        }

        //else, return corresponding authority
        return getBaseMapper().selectPermsById(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeById(Long userId) {

        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;

        //check if it's admin
        if(SecurityUtil.isAdmin()){
            //if yes, return all menus
             menus = menuMapper.selectAllRouterMenu();
        } else {
            //else, return corresponding menus
            menus = menuMapper.selectRouterMenuById(userId);
        }

        //create tree model
        List<Menu> menuTree = menuTreeBuilder(menus,0L);
        return menuTree;
    }

    /*
    1. find first level menu - parentId = 0
    2. find children of first menu
     */
    private List<Menu> menuTreeBuilder(List<Menu> menus, Long parentId) {
        //manipulate menus list
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());

        return menuTree;
    }

    /**
     * get menu's children list
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {

        //manipulate menus list, find children of 'menu'
        List<Menu> children = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                //.map(m-> m.setChildren(getChildren(m,menus))) // this step is for getting children for children menu, it is a backup solution but not that necessary
                .collect(Collectors.toList());

        return children;
    }

    //--------------------------------------------- admin list menu -------------------------------------------
    @Override
    public ResponseResult listMenu(MenuDto menuDto) {

        //fuzzy search for menuName & status
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .like(StringUtils.hasText(menuDto.getMenuName()),Menu::getMenuName,menuDto.getMenuName())
                .like(StringUtils.hasText(menuDto.getStatus()),Menu::getStatus,menuDto.getStatus())
                .orderByAsc(Menu::getParentId)
                .orderByAsc(Menu::getOrderNum);

        //list all menus based on the wrapper
        List<Menu> menus = list(wrapper);

        return ResponseResult.okResult(menus);
    }

    //--------------------------------------------- admin add menu -------------------------------------------
    @Override
    public ResponseResult addMenu(Menu menu) {

        //save menu to database
        save(menu);

        return ResponseResult.okResult();
    }

    //--------------------------------------------- admin update menu -------------------------------------------
    @Override
    public ResponseResult jumpToUpdate(Long id) {

        //query menu by id
        Menu menu = getById(id);

        //convert to MenuVo
        MenuVo menuVo = BeanCopyUtil.copyBean(menu, MenuVo.class);

        return ResponseResult.okResult(menuVo);
    }
    @Override
    public ResponseResult updateMenu(Menu menu) {

        if(menu.getId().equals(menu.getParentId())){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"updating menu "+menu.getMenuName()+" fail, parent menu cannot be itself");
        }

        updateById(menu);
        return ResponseResult.okResult();
    }

    //--------------------------------------------- admin delete menu -------------------------------------------
    @Override
    public ResponseResult deleteMenu(Long id) {

        //check if menu has children
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId,id);

        //if has children, prompt msg
        if(count(wrapper) != 0){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"deletion not allowed,children menu exist");
        }
        //if no children, remove
        removeById(id);
        return ResponseResult.okResult();
    }
}
```

## 29. Blog Backstage - Role CRUD
### RoleController
```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.domain.dto.RoleStatusDto;
import com.js.domain.entity.Role;

import com.js.service.MenuService;
import com.js.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult listRole(Integer pageNum, Integer pageSize, Role role){
        return roleService.listRole(pageNum,pageSize,role);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody RoleStatusDto roleDto){
        return roleService.changeStatus(roleDto);
    }

    @PostMapping
    public ResponseResult addRole(@RequestBody Role role ){
        return roleService.addRole(role);
    }

    @GetMapping("/{id}")
    public ResponseResult<Role> getRoleInfo(@PathVariable Long id){
        return roleService.getRoleInfo(id);
    }

    @PutMapping
    public ResponseResult updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteRole(@PathVariable Long id){
        return roleService.deleteRole(id);
    }
}
```

### RoleServiceImpl
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.domain.ResponseResult;
import com.js.domain.dto.RoleStatusDto;
import com.js.domain.entity.Role;
import com.js.domain.entity.RoleMenu;
import com.js.domain.vo.PageVo;
import com.js.mapper.RoleMapper;
import com.js.service.RoleMenuService;
import com.js.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {

        List<String> roleKey = new ArrayList<>();
        //check if it's admin, if yes, role list only have 'admin'
        if(id == 1){
            roleKey.add("admin");
            return roleKey;
        }

        //else, return corresponding role info
        return getBaseMapper().selectRoleKeyById(id);
    }

    /**
     * query role list
     * @param pageNum
     * @param pageSize
     * @param role
     * @return
     */
    @Override
    public ResponseResult listRole(Integer pageNum, Integer pageSize, Role role) {

        //fuzzy search for roleName, status
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .like(StringUtils.hasText(role.getRoleName()),Role::getRoleName,role.getRoleName())
                .like(StringUtils.hasText(role.getStatus()),Role::getStatus,role.getStatus());

        //set up page
        Page<Role> page = new Page<>(pageNum,pageSize);
        page(page,wrapper);
        List<Role> roles = page.getRecords();

        //convert to pageVo
        PageVo pageVo = new PageVo(roles,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    /**
     * change role's status
     * @param roleDto
     * @return
     */
    @Override
    public ResponseResult changeStatus(RoleStatusDto roleDto) {

        //get role, set corresponding status,update
        Role role = getById(roleDto.getRoleId());
        role.setStatus(roleDto.getStatus());
        updateById(role);

        return ResponseResult.okResult();
    }

    /**
     * create new role
     * @param role
     * @return
     */
    @Override
    public ResponseResult addRole(Role role) {

        //save role into database
        save(role);

        //since menuId attribute is not in database, we use RoleMenu entity to restore menuId
        addRoleMenu(role);
        return ResponseResult.okResult();
    }

    /**
     * create new role-menu connection
     * @param role
     */
    public void addRoleMenu(Role role){

        List<Long> menuIds = role.getMenuIds();
        List<RoleMenu> roleMenus = null;
        if(menuIds.size() > 0){
            roleMenus = menuIds.stream()
                    .map(menuId -> new RoleMenu(role.getId(), menuId))
                    .collect(Collectors.toList());
        }

        roleMenuService.saveBatch(roleMenus);
    }

    /**
     * get role info
     * @param id
     * @return
     */
    @Override
    public ResponseResult<Role> getRoleInfo(Long id) {
        Role role = getById(id);
        return ResponseResult.okResult(role);
    }

    /**
     * Updating into database
     * @param role
     * @return
     */
    @Override
    public ResponseResult updateRole(Role role) {
        //update role basic info into database
        updateById(role);

        //remove original Role-Menu connection
        roleMenuService.removeRoleMenuByRoleId(role.getId());

        //create new Role-Menu connection
        addRoleMenu(role);
        return ResponseResult.okResult();
    }

    /**
     * delete role
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteRole(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }
}
```
### MenuController
```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.domain.dto.MenuDto;
import com.js.domain.entity.Menu;

import com.js.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult listMenu(MenuDto menuDto){
        return menuService.listMenu(menuDto);
    }

    @PostMapping
    public ResponseResult addMenu(@RequestBody Menu menu){
        return menuService.addMenu(menu);
    }

    @GetMapping("/{id}")
    public ResponseResult jumpToUpdate(@PathVariable Long id){
        return menuService.jumpToUpdate(id);
    }

    @PutMapping
    public ResponseResult updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteMenu(@PathVariable Long id){
        return menuService.deleteMenu(id);
    }

    //for adding new role
    @GetMapping("/treeselect")
    public ResponseResult treeSelect(){
        return menuService.treeSelect();
    }

    //for updating role info
    @GetMapping("/roleMenuTreeselect/{id}")
    public ResponseResult getMenuListByRoleId(@PathVariable Long id){
        return menuService.getMenuListByRoleId(id);
    }
}
```
### MenuServiceImpl
```java
/**
     * TreeSelect all menus, for adding new role function
     * @return
     */
    @Override
    public ResponseResult treeSelect() {

        List<Menu> menus = list();

        List<MenuTreeVo> results = TreeVoBuilder(menus);

        return ResponseResult.okResult(results);
    }

    public List<MenuTreeVo> TreeVoBuilder(List<Menu> menus){
        //convert menus to menuTreeVos
        List<MenuTreeVo> menuTreeVos = menus.stream()
                .map(menu -> new MenuTreeVo(null, menu.getId(), menu.getMenuName(), menu.getParentId()))
                .collect(Collectors.toList());

        //set children for all menuTreeVos
        List<MenuTreeVo> results = menuTreeVos.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> menu.setChildren(getMenuTreeVoChildren(menuTreeVos, menu)))
                .collect(Collectors.toList());
        return results;
    }

    /**
     * get children of MenuTreeVo
     * @param treeVos
     * @param menuTreeVo
     * @return
     */
    public List<MenuTreeVo> getMenuTreeVoChildren(List<MenuTreeVo> treeVos,MenuTreeVo menuTreeVo){
        List<MenuTreeVo> menuTreeVos = treeVos.stream()
                .filter(t -> t.getParentId().equals(menuTreeVo.getId()))
                .map(t -> t.setChildren(getMenuTreeVoChildren(treeVos, t)))
                .collect(Collectors.toList());
        return menuTreeVos;
    }

    /**
     * role menu tree for updating role info
     * @param id
     * @return
     */
    @Override
    public ResponseResult getMenuListByRoleId(Long id) {

        //get checkedKeys
        List<Long> checkedKeys = null;
        if(id==1){
            //if it's admin, return all menus
            checkedKeys = list().stream().map(menu -> menu.getId()).collect(Collectors.toList());
        }else {
            //if it's not admin, get corresponding menus
            checkedKeys = getBaseMapper().selectMenuByRoleId(id);
        }

        //get MenuTreeVos
        List<MenuTreeVo> menuTreeVos = TreeVoBuilder(list());

        //encap and return
        RoleMenuTreeVo result = new RoleMenuTreeVo(menuTreeVos,checkedKeys);

        return ResponseResult.okResult(result);
    }
```
### RoleMenuServiceImpl
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.domain.entity.RoleMenu;
import com.js.mapper.RoleMenuMapper;
import com.js.service.RoleMenuService;
import org.springframework.stereotype.Service;


@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    /**
     * for updating role info, original connection between role and menu should be removed
     * @param id
     */
    @Override
    public void removeRoleMenuByRoleId(Long id) {
        //find the RoleMenu connection
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getRoleId,id);

        //remove it
        remove(wrapper);
    }
}
```
## 30. Blog Backstage - User CRUD
### AddUserDto
```java
package com.js.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto {

    private Long id;
    private String userName;
    private String nickName;
    private String password;
    private String email;
    private String phonenumber;
    private String sex;
    private String status;
    private List<Long> roleIds;
}
```
### UserRoleVo
```java
package com.js.domain.vo;

import com.js.domain.entity.Role;
import com.js.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleVo {

    private List<Long> roleIds;

    private List<Role> roles;

    private User user;
}
```
### UserController 
```java
package com.js.controller;

import com.js.domain.ResponseResult;
import com.js.domain.dto.AddUserDto;
import com.js.domain.entity.User;
import com.js.service.UserService;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseResult<User> listUser(Integer pageNum,Integer pageSize,User user){
        return userService.listUser(pageNum,pageSize,user);
    }

    @PostMapping
    public ResponseResult addUser(@RequestBody AddUserDto addUserDto){
        return userService.addUser(addUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getUserInfo(@PathVariable Long id){
        return userService.getUserInfo(id);
    }

    @PutMapping
    public ResponseResult updateUser(@RequestBody AddUserDto userDto){
        return userService.updateUser(userDto);
    }
}
```

### UserService
```java
 package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.dto.AddUserDto;
import com.js.domain.entity.User;

public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult<User> listUser(Integer pageNum, Integer pageSize, User user);

    ResponseResult addUser(AddUserDto addUserDto);

    ResponseResult deleteUser(Long id);

    ResponseResult getUserInfo(Long id);

    ResponseResult updateUser(AddUserDto userDto);
}
```
### UserServiceImpl
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.Utils.BeanCopyUtil;
import com.js.Utils.SecurityUtil;
import com.js.domain.ResponseResult;
import com.js.domain.dto.AddUserDto;
import com.js.domain.entity.Role;
import com.js.domain.entity.User;
import com.js.domain.entity.UserRole;
import com.js.domain.vo.PageVo;
import com.js.domain.vo.UserInfoVo;
import com.js.domain.vo.UserRoleVo;
import com.js.enums.AppHttpCodeEnum;
import com.js.exception.SystemException;
import com.js.mapper.UserMapper;
import com.js.service.RoleService;
import com.js.service.UserRoleService;
import com.js.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Override
    public ResponseResult userInfo() {

        //Get current userId
        Long userId = SecurityUtil.getUserId();

        //inquire userInfo by userId
        User user = getById(userId);

        //encapsulate into UserInfoVo
        UserInfoVo vo = BeanCopyUtil.copyBean(user,UserInfoVo.class);

        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {

        updateById(user);

        return ResponseResult.okResult();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult register(User user) {

        //do non-empty check - email,username,pass can not be empty
         if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
         }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }

        //do duplication check, if the input email is existed in DB
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(emailExist(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }

        //password encryption
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        //store into DB
        save(user);

        return ResponseResult.okResult();
    }

    private boolean phonenumberExist(String phonenumber) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhonenumber,phonenumber);
        return count(queryWrapper) > 0;
    }

    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNickName,nickName);
        return count(queryWrapper) > 0;
    }

    private boolean emailExist(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,email);
        return count(queryWrapper) > 0;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,userName);
        return count(queryWrapper) > 0;
    }

    /**
     * list all users in admin system
     * @param pageNum
     * @param pageSize
     * @param user
     * @return
     */
    @Override
    public ResponseResult<User> listUser(Integer pageNum, Integer pageSize,User user) {

        //fuzzy search
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .like(StringUtils.hasText(user.getUserName()),User::getUserName,user.getUserName())
                .like(StringUtils.hasText(user.getPhonenumber()),User::getPhonenumber,user.getPhonenumber())
                .like(StringUtils.hasText(user.getStatus()),User::getStatus,user.getStatus());

        //set up page
        Page<User> page = new Page<>(pageNum,pageSize);
        page(page,wrapper);

        //encap as PageVo and return
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    /**
     * add new user
     * @param addUserDto
     * @return
     */
    @Override
    public ResponseResult addUser(AddUserDto addUserDto) {

        //check if userName is null, and userName,phonenumber,email is not existed in DB
        if(addUserDto.getUserName().equals("")){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(userNameExist(addUserDto.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(phonenumberExist(addUserDto.getPhonenumber())){
            throw new SystemException(AppHttpCodeEnum.PHONE_NUMBER_EXIST);
        }
        if(emailExist(addUserDto.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }

        //encrypt password
        String password = passwordEncoder.encode(addUserDto.getPassword());
        addUserDto.setPassword(password);
        //convert addUserDto to User
        User user = BeanCopyUtil.copyBean(addUserDto, User.class);
        //save to DB
        save(user);
        //save new user-role connection
        userRoleService.addUserRole(user.getId(),addUserDto.getRoleIds());
        return ResponseResult.okResult();
    }

    /**
     * delete user by id
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteUser(Long id) {

        //remove user
        removeById(id);
        //remove user-role connection
        userRoleService.deleteUserRoleByUserId(id);
        return ResponseResult.okResult();
    }

    /**
     * get user info when updating user info
     * @param id
     * @return
     */
    @Override
    public ResponseResult getUserInfo(Long id) {
        //get userRoles by userId
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,id);
        List<UserRole> userRoles = userRoleService.list(wrapper);

        //use stream to get roleIds
        List<Long> roleIds = userRoles.stream()
                .map(userRole -> userRole.getRoleId())
                .collect(Collectors.toList());

        //get all normal roles
        LambdaQueryWrapper<Role> roleWrapper = new LambdaQueryWrapper<>();
        roleWrapper.eq(Role::getStatus, 0);
        List<Role> roles = roleService.list(roleWrapper);

        //encapsulate and return
        UserRoleVo userRoleVo = new UserRoleVo(roleIds,roles,getById(id));
        return ResponseResult.okResult(userRoleVo);
    }

    /**
     * update userInfo into DB
     * @param userDto
     * @return
     */
    @Override
    public ResponseResult updateUser(AddUserDto userDto) {
        //convert userDto to user
        User user = BeanCopyUtil.copyBean(userDto, User.class);
        //update into DB
        updateById(user);
        //remove original user-role connection
        userRoleService.deleteUserRoleByUserId(userDto.getId());
        //add new user-role connection
        userRoleService.addUserRole(userDto.getId(),userDto.getRoleIds());
        return ResponseResult.okResult();
    }
}
```
### UserRoleService
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.entity.UserRole;

import java.util.List;


public interface UserRoleService extends IService<UserRole> {

    void addUserRole(Long userId, List<Long> roleIds);

    void deleteUserRoleByUserId(Long userId);
}
```

### UserRoleServiceImpl
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.domain.entity.UserRole;
import com.js.mapper.UserRoleMapper;
import com.js.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    /**
     * add new User-role connection when adding new user
     * @param userId
     * @param roleIds
     */
    @Override
    public void addUserRole(Long userId, List<Long> roleIds) {

        List<UserRole> userRoles = roleIds.stream()
                .map(roleId -> new UserRole(userId, roleId))
                .collect(Collectors.toList());

        //save userRoles into DB
        saveBatch(userRoles);
    }

    @Override
    public void deleteUserRoleByUserId(Long userId) {

        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,userId);

        remove(wrapper);
    }
}
```

## 31. Blog Backstage - Category CRUD
### CategoryDto
```java
package com.js.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private String description;
    private String status;
}
```
### CategoryController
```java
package com.js.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.js.Utils.BeanCopyUtil;
import com.js.Utils.WebUtils;
import com.js.domain.ResponseResult;
import com.js.domain.dto.CategoryDto;
import com.js.domain.entity.Category;
import com.js.domain.vo.CategoryVo;
import com.js.domain.vo.ExcelCategoryVo;
import com.js.domain.vo.PageVo;
import com.js.enums.AppHttpCodeEnum;
import com.js.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult<CategoryVo> listAllCategory(){
        return categoryService.listAllCategory();
    }

    @PreAuthorize("@ps.hasPermissions('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response){

        try {
            //config request head of the file
            WebUtils.setDownLoadHeader("category.xlsx",response);

            //get exporting data
            List<Category> categories = categoryService.list();
            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtil.copyBeanList(categories, ExcelCategoryVo.class);

            //write data into excel
            EasyExcel
                    .write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE)
                    .sheet("article categories")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            //if exception occur, respond json data
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    @GetMapping("/list")
    public ResponseResult<PageVo> adminListCategory(Integer pageNum, Integer pageSize, Category category){
        return categoryService.adminListCategory(pageNum,pageSize,category);
    }

    @PostMapping
    public ResponseResult adminAddCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.adminAddCategory(categoryDto);
    }

    @GetMapping("/{id}")
    public ResponseResult<Category> getCategoryInfo(@PathVariable Long id){
        return categoryService.getCategoryInfo(id);
    }

    @PutMapping
    public ResponseResult adminUpdateCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.adminUpdateCategory(categoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult adminDeleteCategory(@PathVariable Long id){
        return categoryService.adminDeleteCategory(id);
    }
}
```

### CategoryService
```java
package com.js.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.js.domain.ResponseResult;
import com.js.domain.dto.CategoryDto;
import com.js.domain.entity.Category;
import com.js.domain.vo.CategoryVo;
import com.js.domain.vo.PageVo;


public interface CategoryService extends IService<com.js.domain.entity.Category> {

    ResponseResult getCategoryList();

    ResponseResult<CategoryVo> listAllCategory();

    ResponseResult<PageVo> adminListCategory(Integer pageNum, Integer pageSize, Category category);

    ResponseResult adminAddCategory(CategoryDto categoryDto);

    ResponseResult<Category> getCategoryInfo(Long id);

    ResponseResult adminUpdateCategory(CategoryDto categoryDto);

    ResponseResult adminDeleteCategory(Long id);
}
```

### CategoryServiceImpl
```java
package com.js.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.Utils.BeanCopyUtil;
import com.js.constants.SystemConstants;
import com.js.domain.ResponseResult;
import com.js.domain.dto.CategoryDto;
import com.js.domain.entity.Article;
import com.js.domain.vo.CategoryVo;
import com.js.domain.vo.PageVo;
import com.js.mapper.CategoryMapper;
import com.js.service.ArticleService;
import com.js.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.js.domain.entity.Category;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

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
        List<Category> categories = listByIds(categoryIds);

        categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        //encapsulate vo
        List<CategoryVo> categoryVos = BeanCopyUtil.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }

    // ---------------------------------- getAllCategories for writing blog ---------------------------
    @Override
    public ResponseResult<CategoryVo> listAllCategory() {

        //query all categories whose status is normal
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus,SystemConstants.STATUS_NORMAL);
        List<Category> list = list(queryWrapper);

        //convert to categoryVos
        List<CategoryVo> categoryVos = BeanCopyUtil.copyBeanList(list, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }

    /**
     * admin system list all categories
     * @param pageNum
     * @param pageSize
     * @param category
     * @return
     */
    @Override
    public ResponseResult<PageVo> adminListCategory(Integer pageNum, Integer pageSize, Category category) {

        //fuzzy search
        LambdaQueryWrapper<Category> categoryWrapper = new LambdaQueryWrapper<>();
        categoryWrapper
                .like(StringUtils.hasText(category.getName()),Category::getName,category.getName())
                .like(StringUtils.hasText(category.getStatus()),Category::getStatus,category.getStatus());

        //set up page
        Page<Category> page = new Page<>(pageNum,pageSize);
        page(page,categoryWrapper);

        //return pageVo
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    /**
     * admin system add new category
     * @param categoryDto
     * @return
     */
    @Override
    public ResponseResult adminAddCategory(CategoryDto categoryDto) {
        //convert categoryDto to category
        Category category = BeanCopyUtil.copyBean(categoryDto, Category.class);
        //save to DB
        save(category);
        return ResponseResult.okResult();
    }

    /**
     * admin system update category, get category info for updating
     * @param id
     * @return
     */
    @Override
    public ResponseResult<Category> getCategoryInfo(Long id) {
        Category category = getById(id);
        return ResponseResult.okResult(category);
    }

    /**
     * admin system update category
     * @param categoryDto
     * @return
     */
    @Override
    public ResponseResult adminUpdateCategory(CategoryDto categoryDto) {
        //convert categoryDto to category
        Category category = BeanCopyUtil.copyBean(categoryDto, Category.class);
        //update into DB
        updateById(category);
        return ResponseResult.okResult();
    }

    /**
     * admin system delete category
     * @param id
     * @return
     */
    @Override
    public ResponseResult adminDeleteCategory(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }
}
```

## 32. Blog Backstage - Friend Link CRUD
