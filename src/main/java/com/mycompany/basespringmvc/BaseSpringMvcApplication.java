package com.mycompany.basespringmvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mycompany.basespringmvc.dao.CityDao;
import com.mycompany.basespringmvc.dao.CustomerDao;
import com.mycompany.basespringmvc.dao.CustomerJdbcRepository;
import com.mycompany.basespringmvc.models.City;
import com.mycompany.basespringmvc.models.Customer;
import com.mycompany.basespringmvc.models.CustomerJdbc;
import com.mycompany.basespringmvc.models.CustomerJpa;
//import com.mycompany.basespringmvc.models.CustomerJpaRepository;

@SpringBootApplication
public class BaseSpringMvcApplication implements CommandLineRunner {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	CustomerJdbcRepository customerJdbcRepository;
	
	//@Autowired
	//CustomerJpaRepository customerJpaRepository;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	CityDao cityDao;

	public static void main(String[] args) {
		SpringApplication.run(BaseSpringMvcApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		System.out.println("----------- JDBC Test -----------");
	    jdbcTemplate.execute("DROP TABLE customers_jdbc IF EXISTS"); //For H2
	    //jdbcTemplate.execute("DROP TABLE IF EXISTS customers_jdbc"); //For MySql
	    jdbcTemplate.execute("CREATE TABLE customers_jdbc(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
	    
	    customerJdbcRepository.insert(new CustomerJdbc("John", "Woo"));
	    customerJdbcRepository.insertAll(new CustomerJdbc("Jeff", "Dean"), new CustomerJdbc("John", "Bloch"));
	    
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
	    
	    /*
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
		*/
	    
		
	    System.out.println("\n----------- Hibernate Test -----------");
	    
	    City turin = new City("Turin", "Italy");
	    City milan = new City("Milan", "Italy");
	    
	    System.out.println(Customer.class.getSimpleName());
	    // save a few customers
	    customerDao.save(new Customer("Jack", "Bauer", turin));
	    customerDao.save(new Customer("Chloe", "O'Brian", turin));
	    customerDao.save(new Customer("Kim", "Bauer", milan));
	    customerDao.save(new Customer("David", "Palmer", milan));
	    customerDao.save(new Customer("Michelle", "Dessler", turin));
		
		// fetch all customers
		System.out.println("\nCustomers found with findAll():");
		for (Customer customer : customerDao.findAll()) {
			System.out.println(customer.toString());
		}

		// fetch customers by last name
		System.out.println("\nCustomer found with findByLastName('Bauer'):");
		customerDao.findByLastName("Bauer").forEach(bauer -> {
			System.out.println(bauer.toString());
		});
		
		// fetch customers by city name
		System.out.println("\nCustomer found with findByCityName('Turin'):");
		customerDao.findByCityName("Turin").forEach(cus -> {
			System.out.println(cus.toString());
		});
		
	}
}
