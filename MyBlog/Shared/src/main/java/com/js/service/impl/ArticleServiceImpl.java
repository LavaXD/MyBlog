package com.js.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.js.domain.entity.Article;
import com.js.mapper.ArticleMapper;
import com.js.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {
}
