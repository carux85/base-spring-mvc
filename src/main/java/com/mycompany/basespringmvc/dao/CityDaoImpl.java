package com.mycompany.basespringmvc.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.basespringmvc.models.City;

@Repository
public class CityDaoImpl implements CityDao{

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void save(City city) {
      sessionFactory.getCurrentSession().save(city);
   }
 
   @Transactional(readOnly = true)
   @Override
   public List<City> findAll() {
      
	  TypedQuery<City> query = sessionFactory.getCurrentSession().createQuery("from "+City.class.getSimpleName());   
      return query.getResultList();     
   }
   
   @Transactional(readOnly = true)
   @Override
   public City findById(long id) {
	  try {
	      TypedQuery<City> query = sessionFactory.getCurrentSession().createQuery("from "+City.class.getSimpleName()+" where id= :id");
	      query.setParameter("id", id);
	      return query.getSingleResult();
	  }catch (NoResultException e) {
		return null;
	  }
   }
	
    @Transactional(readOnly = true)
	@Override
	public List<City> findByName(String name) {
        TypedQuery<City> query = sessionFactory.getCurrentSession().createQuery("from "+City.class.getSimpleName()+" where name= :name");
        query.setParameter("name", name);
        return query.getResultList();
	}
	
    @Transactional(readOnly = true)
	@Override
	public List<City> findByCountry(String country) {
        TypedQuery<City> query = sessionFactory.getCurrentSession().createQuery("from "+City.class.getSimpleName()+" where country= :country");
        query.setParameter("country", country);
        return query.getResultList();
	}
	
    @Transactional
	@Override
	public void delete(City city) {
    	sessionFactory.getCurrentSession().delete(city);
	}
   
}
