package com.mycompany.basespringmvc.dao;

import java.util.List;

import com.mycompany.basespringmvc.models.CustomerHibernate;

public interface CustomerHibernateDao {
	
   void save(CustomerHibernate customer);
   List<CustomerHibernate> findAll();
}
