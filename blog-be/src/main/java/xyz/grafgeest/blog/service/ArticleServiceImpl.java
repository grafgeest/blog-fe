package xyz.grafgeest.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.grafgeest.blog.dao.ArticleDao;
import xyz.grafgeest.blog.entity.Article;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	@Override
	public Article getArticleById(int articleId) {
		Article obj = articleDao.getArticleById(articleId);
		return obj;
	}	
	@Override
	public List<Article> getAllArticles(){
		return articleDao.getAllArticles();
	}
	@Override
	public synchronized boolean createArticle(Article article){
       if (articleDao.articleExists(article.getTitle(), article.getCategory())) {
    	   return false;
       } else {
    	   articleDao.createArticle(article);
    	   return true;
       }
	}
	@Override
	public void updateArticle(Article article) {
		articleDao.updateArticle(article);
	}
	@Override
	public void deleteArticle(int articleId) {
		articleDao.deleteArticle(articleId);
	}
}
