package com.mycompany.basespringmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mycompany.basespringmvc.models.CustomerJdbc;

public class CustomerJdbcRowMapper implements RowMapper<CustomerJdbc> {

    @Override
    public CustomerJdbc mapRow(ResultSet rs, int rowNum) throws SQLException {

    	CustomerJdbc customer = new CustomerJdbc(
    			rs.getLong("id"), 
    			rs.getString("first_name"), 
    			rs.getString("last_name")
    	);
    	
        return customer;
    }
}