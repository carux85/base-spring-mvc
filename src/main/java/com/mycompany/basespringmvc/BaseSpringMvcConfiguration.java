package com.mycompany.basespringmvc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.mycompany.basespringmvc.models.ContactForm;
import com.mycompany.basespringmvc.models.CustomerJdbcRepository;
import com.mycompany.basespringmvc.models.SimpleBean;

@Configuration
public class BaseSpringMvcConfiguration {

    @Bean(name="defaultContactForm")
    public ContactForm defaultContactForm() {
    	ContactForm form = new ContactForm();
        return form;
    }
    
    @Bean(name="defaultSimpleBean")
    @Scope("prototype")//(default) Scopes a single bean definition to any number of object instances
    //@Scope("singleton") //Scopes a single bean definition to a single object instance per Spring IoC container
    //@Scope("request") //Scopes a single bean definition to the lifecycle of a single HTTP request
    //@Scope("session") //Scopes a single bean definition to the lifecycle of a HTTP Session
    public SimpleBean defaultSimpleBean() {
    	SimpleBean bean = new SimpleBean();
    	bean.setName("default");
        return bean;
    }
    
    @Bean(name="firstSimpleBean")
    public SimpleBean firstSimpleBean() {
    	SimpleBean bean = new SimpleBean();
    	bean.setName("first");
        return bean;
    }
    
    @Bean(name="secondSimpleBean")
    public SimpleBean secondSimpleBean() {
    	SimpleBean bean = new SimpleBean();
    	bean.setName("second");
        return bean;
    }
    
    /*
    @Bean(name="customerJdbcDao")
    public CustomerJdbcDao customerJdbcDao() {
    	return new CustomerJdbcDao();
    }
    */
    
}
