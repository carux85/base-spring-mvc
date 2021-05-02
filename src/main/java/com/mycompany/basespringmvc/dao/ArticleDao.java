package com.mycompany.basespringmvc.dao;

import java.util.List;

import com.mycompany.basespringmvc.models.Article;
import com.mycompany.basespringmvc.models.Brand;

public interface ArticleDao {

	void saveArticle(Article article);
	Article findArticleById(String id);
	List<Article> findArticlesByBrand(String brandId);
	void saveBrand(Brand brand);
	List<Brand> findAllBrands();
}
