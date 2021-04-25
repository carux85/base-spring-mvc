package com.mycompany.basespringmvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mycompany.basespringmvc.models.CustomerJdbc;
import com.mycompany.basespringmvc.models.CustomerJdbcRepository;
import com.mycompany.basespringmvc.models.CustomerJpa;
import com.mycompany.basespringmvc.models.CustomerJpaRepository;

@SpringBootApplication
public class BaseSpringMvcApplication implements CommandLineRunner {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	CustomerJdbcRepository customerJdbcRepository;
	
	@Autowired
	CustomerJpaRepository customerJpaRepository;

	public static void main(String[] args) {
		SpringApplication.run(BaseSpringMvcApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		System.out.println("----------- JDBC Test -----------");
	    jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
	    jdbcTemplate.execute("CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
	    
	    CustomerJdbc c1= new CustomerJdbc("John", "Woo");
	    CustomerJdbc c2= new CustomerJdbc("Jeff", "Dean");
	    CustomerJdbc c3= new CustomerJdbc("John", "Bloch");
	    
	    customerJdbcRepository.insert(c1);
	    customerJdbcRepository.insertAll(c2, c3);
	    
	    System.out.println("findById \"1\" result:");
	    System.out.println(customerJdbcRepository.findById(1).toString());
	    
	    System.out.println("findByName \"John\" result:");
	    customerJdbcRepository.findByName("John")
	    .forEach(customer -> System.out.println(customer.toString()));
	    
	    System.out.println("delete \"1\"");
	    customerJdbcRepository.delete(1);
	    
	    System.out.println("findByAll result:");
	    customerJdbcRepository.findAll()
	    .forEach(customer -> System.out.println(customer.toString()));
	    
	    
	    System.out.println("----------- JPA Test -----------");
	    // save a few customers
	    customerJpaRepository.save(new CustomerJpa("Jack", "Bauer"));
	    customerJpaRepository.save(new CustomerJpa("Chloe", "O'Brian"));
	    customerJpaRepository.save(new CustomerJpa("Kim", "Bauer"));
	    customerJpaRepository.save(new CustomerJpa("David", "Palmer"));
	    customerJpaRepository.save(new CustomerJpa("Michelle", "Dessler"));
		
		// fetch all customers
		System.out.println("Customers found with findAll():");
		  for (CustomerJpa customer : customerJpaRepository.findAll()) {
			  System.out.println(customer.toString());
		  }

		// fetch an individual customer by ID
		CustomerJpa customer = customerJpaRepository.findById(1L);
		System.out.println("Customer found with findById(1L):");
		System.out.println(customer.toString());
		
		// fetch customers by last name
		System.out.println("Customer found with findByLastName('Bauer'):");
		customerJpaRepository.findByLastName("Bauer").forEach(bauer -> {
			System.out.println(bauer.toString());
		});
	    
	}
}
