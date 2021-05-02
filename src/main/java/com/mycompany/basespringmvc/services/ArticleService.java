package com.mycompany.basespringmvc.services;

import java.util.List;

import com.mycompany.basespringmvc.models.Article;
import com.mycompany.basespringmvc.models.Brand;

public interface ArticleService {

	public List<Brand> getAllBrands();
	public Article getArticle(String id);
	public List<Article> getArticlesByBrand(String brandId);
}
