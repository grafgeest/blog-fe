package xyz.grafgeest.blog.dao;

import xyz.grafgeest.blog.entity.Article;

import java.util.List;

public interface ArticleDao {
    List<Article> getAllArticles();
    Article getArticleById(int articleId);
    void createArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(int articleId);
    boolean articleExists(String title, String category);
}
 