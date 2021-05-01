package com.mycompany.basespringmvc.dao;

import java.util.List;

import com.mycompany.basespringmvc.models.Customer;

public interface CustomerDao {
	
   void save(Customer customer);
   Customer findById(long id);
   List<Customer> findByFirstName(String firstName);
   List<Customer> findByLastName(String lastName);
   List<Customer> findByCityName(String cityName);
   List<Customer> findAll();
   void delete(Customer customer);
   void deleteById(long id);
}
