package com.mycompany.basespringmvc.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.basespringmvc.models.City;
import com.mycompany.basespringmvc.models.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao{

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void save(Customer customer) {
      sessionFactory.getCurrentSession().save(customer);
   }
 
   @Transactional(readOnly = true)
   @Override
   public List<Customer> findAll() {
      
	  TypedQuery<Customer> query = sessionFactory.getCurrentSession().createQuery("from "+Customer.class.getSimpleName());   
      return query.getResultList();     
   }
   
   @Transactional(readOnly = true)
   @Override
   public Customer findById(long id) {
	  try {
	      TypedQuery<Customer> query = sessionFactory.getCurrentSession().createQuery("from "+Customer.class.getSimpleName()+" where id= :id");
	      query.setParameter("id", id);
	      return query.getSingleResult();
	  }catch (NoResultException e) {
		return null;
	  }
   }
	
    @Transactional(readOnly = true)
	@Override
	public List<Customer> findByFirstName(String firstName) {
        TypedQuery<Customer> query = sessionFactory.getCurrentSession().createQuery("from "+Customer.class.getSimpleName()+" where firstName= :firstName");
        query.setParameter("firstName", firstName);
        return query.getResultList();
	}
	
    @Transactional(readOnly = true)
	@Override
	public List<Customer> findByLastName(String lastName) {
        TypedQuery<Customer> query = sessionFactory.getCurrentSession().createQuery("from "+Customer.class.getSimpleName()+" where lastName= :lastName");
        query.setParameter("lastName", lastName);
        return query.getResultList();
	}
    
    @Transactional
	@Override
	public List<Customer> findByCityName(String cityName) {
    	TypedQuery<Customer> query = sessionFactory.getCurrentSession().createQuery(
    			"select cu "+
    			"from "+Customer.class.getSimpleName()+" cu "+
    			"join cu.city ci "+
    			"where ci.name= :cityName");
        query.setParameter("cityName", cityName);
        return query.getResultList();
	}
	
    @Transactional
	@Override
	public void delete(Customer customer) {
    	sessionFactory.getCurrentSession().delete(customer);
	}
    
    @Transactional
	@Override
	public void deleteById(long id) {
    	Customer cus = new Customer();
    	cus.setId(id);
    	sessionFactory.getCurrentSession().delete(cus);
	}
   
}
