package com.mycompany.basespringmvc.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.basespringmvc.models.Article;
import com.mycompany.basespringmvc.models.Brand;
import com.mycompany.basespringmvc.services.ArticleService;

@RestController
public class ArticleController {
	
	@Autowired
	private ArticleService articleService; 
	
	@GetMapping("/brands")
	public List<Brand> brands() {
		
		return articleService.getAllBrands();
	}

	@GetMapping("/articles")
	public List<Article> articles(@RequestParam(value = "brand", required = true) String brand) {
		
		return articleService.getArticlesByBrand(brand);
	}
	
	@GetMapping("/article/{articleId}")
	public Article article(@PathVariable(value = "articleId", required=true) String articleId) {
		
		return articleService.getArticle(articleId);
	}
}
