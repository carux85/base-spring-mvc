package com.mycompany.basespringmvc.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.basespringmvc.models.Article;
import com.mycompany.basespringmvc.models.Brand;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

	@GetMapping
	public String showCatalog(Model model) {
		
		model.addAttribute("brands", new ArrayList<Brand>());
		model.addAttribute("articles", new ArrayList<Article>());	
		return "catalog";
	}
}
