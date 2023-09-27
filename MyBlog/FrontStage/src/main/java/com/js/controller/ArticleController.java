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
