package com.mycompany.basespringmvc.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.basespringmvc.models.CustomerHibernate;

@Repository
public class CustomerHibernateDaoImpl implements CustomerHibernateDao{

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void save(CustomerHibernate customer) {
      sessionFactory.getCurrentSession().save(customer);
   }
 
   @Transactional(readOnly = true)
   @Override
   public List<CustomerHibernate> findAll() {
      @SuppressWarnings("unchecked")
      TypedQuery<CustomerHibernate> query = sessionFactory.getCurrentSession().createQuery("from CustomerHibernate");
      return query.getResultList();
   }
}
