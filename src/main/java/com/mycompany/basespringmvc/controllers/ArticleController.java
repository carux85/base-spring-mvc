package com.mycompany.basespringmvc.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.basespringmvc.models.Article;
import com.mycompany.basespringmvc.models.Brand;

@RestController
public class ArticleController {
	
	@GetMapping("/brands")
	public List<Brand> brands() {
		
		//TODO: gets from DB
		Brand indesit = new Brand();
		indesit.setId("1");
		indesit.setName("Indesit");
		Brand philips = new Brand();
		philips.setId("2");
		philips.setName("dishwasher");
		ArrayList<Brand> brands= new ArrayList<Brand>(); 
		brands.add(indesit);
		brands.add(philips);
		
		return brands;
	}

	@GetMapping("/articles")
	public List<Article> articles(@RequestParam(value = "brand", required = true) String brand) {
		
		HashMap<String, List<Article>> articles = new HashMap<String, List<Article>>();
		
		//TODO: gets from DB
		Article art1 = new Article();
		art1.setId("1");
		art1.setName("washing machine");
		Article art2 = new Article();
		art2.setId("2");
		art2.setName("dishwasher");
		ArrayList<Article> indesit= new ArrayList<Article>(); 
		indesit.add(art1);
		indesit.add(art2);
		Article art3 = new Article();
		art3.setId("3");
		art3.setName("television");
		Article art4 = new Article();
		art4.setId("4");
		art4.setName("radio");
		ArrayList<Article> philips= new ArrayList<Article>(); 
		philips.add(art3);
		philips.add(art4);
		
		articles.put("1", indesit);
		articles.put("2", philips);
		
		return articles.get(brand);
	}
	
	@GetMapping("/article/{articleId}")
	public Article article(@PathVariable(value = "articleId", required=true) String articleId) {
		
		HashMap<String, Article> articles = new HashMap<String, Article>();
		
		//TODO: gets from DB
		Article art1 = new Article();
		art1.setId("1");		
		art1.setName("washing machine");
		articles.put(art1.getId(), art1);
		
		Article art2 = new Article();
		art2.setId("2");
		art2.setName("dishwasher"); 
		articles.put(art2.getId(), art2);
		
		Article art3 = new Article();
		art3.setId("3");
		art3.setName("television");
		articles.put(art3.getId(), art3);
		
		Article art4 = new Article();
		art4.setId("4");
		art4.setName("radio");
		articles.put(art4.getId(), art4);
		
		if(!articles.containsKey(articleId)) {
			return null;
		}
		
		return articles.get(articleId);
	}
}
