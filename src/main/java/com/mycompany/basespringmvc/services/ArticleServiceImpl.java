package com.mycompany.basespringmvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.basespringmvc.dao.ArticleDao;
import com.mycompany.basespringmvc.models.Article;
import com.mycompany.basespringmvc.models.Brand;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao articleDao;
	
	public List<Brand> getAllBrands() {
		
		return articleDao.findAllBrands();
	}

	@Override
	public List<Article> getArticlesByBrand(String brandId) {

		return articleDao.findArticlesByBrand(brandId);
	}

	@Override
	public Article getArticle(String id) {
		return articleDao.findArticleById(id);
	}
}
