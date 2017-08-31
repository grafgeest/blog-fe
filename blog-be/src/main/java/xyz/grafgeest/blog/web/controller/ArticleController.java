package xyz.grafgeest.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.grafgeest.blog.entity.Article;
import xyz.grafgeest.blog.service.ArticleService;

import java.util.List;

@Controller
@RequestMapping("user")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@GetMapping("article")
	public ResponseEntity<Article> getArticleById(@RequestParam("id") String id) {
		Article article = articleService.getArticleById(Integer.parseInt(id));
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}
	@GetMapping("all-articles")
	public ResponseEntity<List<Article>> getAllArticles() {
		List<Article> list = articleService.getAllArticles();
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}
	@PostMapping("article")
	public ResponseEntity<Void> createArticle(@RequestBody Article article, UriComponentsBuilder builder) {
        boolean flag = articleService.createArticle(article);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article?id={id}").buildAndExpand(article.getArticleId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("article")
	public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
		articleService.updateArticle(article);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}
	@DeleteMapping("article")
	public ResponseEntity<Void> deleteArticle(@RequestParam("id") String id) {
		articleService.deleteArticle(Integer.parseInt(id));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 