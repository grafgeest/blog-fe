package xyz.grafgeest.blog.service;


import xyz.grafgeest.blog.entity.Article;

import java.util.List;

public interface ArticleService {
     List<Article> getAllArticles();
     Article getArticleById(int articleId);
     boolean createArticle(Article article);
     void updateArticle(Article article);
     void deleteArticle(int articleId);
}
