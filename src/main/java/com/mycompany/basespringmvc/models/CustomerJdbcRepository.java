package com.mycompany.basespringmvc.models;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerJdbcRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int insert(CustomerJdbc customer) {

	    return jdbcTemplate.update(
	    	      "INSERT INTO customers(first_name, last_name) VALUES (?,?)", 
	    	      customer.getFirstName(),
	    	      customer.getLastName()
    	      );
	}
	
	public int[] insertAll(CustomerJdbc... customers) {
		
		List<Object[]> paramsList = new ArrayList<Object[]>();
		for (CustomerJdbc c : customers) {
			String[] params = new String[2];
			params[0] = c.getFirstName();
			params[1] = c.getLastName();		
			paramsList.add(params);
		}
		
		return jdbcTemplate.batchUpdate(
					"INSERT INTO customers(first_name, last_name) VALUES (?,?)", 
					paramsList
				);
	}
	
	public int update(CustomerJdbc customer) {
		
		return jdbcTemplate.update(
					"UPDATE customers SET first_name = ?, last_name = ? WHERE id = ?", 
					customer.getFirstName(), 
					customer.getLastName(),
					customer.getId()
				);
	}
	
	public int delete(long id) {
		
		return jdbcTemplate.update(
					"DELETE FROM customers WHERE id = ?", 
					id
				);
	}
	
	public CustomerJdbc findById(long id) {

		
        return jdbcTemplate.queryForObject(
	        		"SELECT * FROM customers WHERE id = ?", 
	        		new Object[]{id}, 
	        		new int[]{Types.INTEGER},
	        		new CustomerJdbcRowMapper()
        		);
        
		/*
        return jdbcTemplate.queryForObject(
        		"SELECT * FROM customers WHERE id = ?", 
        		new Object[]{id},
        		new int[]{Types.BIGINT},
        		CustomerJdbc.class
    		);
    	*/
    }
	
	public List<CustomerJdbc> findByName(String name) {
		
		return jdbcTemplate.query(
        		"SELECT * FROM customers WHERE first_name = ?", 
        		new Object[]{name},
        		new int[]{Types.VARCHAR},
        		new CustomerJdbcRowMapper()
    		);
		
		/*
		return jdbcTemplate.queryForList(
        		"SELECT * FROM customers WHERE first_name = ?", 
        		new Object[]{name},
        		new int[]{Types.VARCHAR},
        		CustomerJdbc.class
    		);
		*/	
	}
	
	public List<CustomerJdbc> findAll() {
		
		return jdbcTemplate.query(
					"SELECT * FROM customers", 
					new CustomerJdbcRowMapper()
				);
		
		/*
		return jdbcTemplate.queryForList(
        		"SELECT * FROM customers WHERE first_name = ?", 
        		new Object[]{name},
        		new int[]{Types.VARCHAR},
        		CustomerJdbc.class
    		);
		*/	
	}
}
