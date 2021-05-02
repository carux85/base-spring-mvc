package com.mycompany.basespringmvc.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.basespringmvc.models.Article;
import com.mycompany.basespringmvc.models.Brand;
import com.mycompany.basespringmvc.models.Customer;

@Repository
public class ArticleDaoImpl implements ArticleDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public void saveArticle(Article article) {
		sessionFactory.getCurrentSession().save(article);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Article> findArticlesByBrand(String brandId) {
		TypedQuery<Article> query = sessionFactory.getCurrentSession().createQuery(
    			"select a "+
    			"from "+Article.class.getSimpleName()+" a "+
    			"join a.brand b "+
    			"where b.id= :brandId");
        query.setParameter("brandId", brandId);
        return query.getResultList();
	}
	
	@Transactional
	@Override
	public void saveBrand(Brand brand) {
		sessionFactory.getCurrentSession().save(brand);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Brand> findAllBrands() {
		TypedQuery<Brand> query = sessionFactory.getCurrentSession().createQuery("from "+Brand.class.getSimpleName());   
	    return query.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Article findArticleById(String id) {
		TypedQuery<Article> query = sessionFactory.getCurrentSession().createQuery("from "+Article.class.getSimpleName()+" where id= :id");
		query.setParameter("id", id);
		return query.getSingleResult();
	}

}
