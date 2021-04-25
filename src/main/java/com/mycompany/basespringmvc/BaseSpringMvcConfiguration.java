package com.mycompany.basespringmvc;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mycompany.basespringmvc.dao.CustomerJdbcRepository;
import com.mycompany.basespringmvc.models.ContactForm;
import com.mycompany.basespringmvc.models.CustomerHibernate;
import com.mycompany.basespringmvc.models.SimpleBean;

@Configuration
@EnableTransactionManagement
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
    
    @Autowired
    private Environment env;
    
    
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
    	
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        Properties props=new Properties();
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.put("hibernate.dialect",env.getProperty("hibernate.dialect"));
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(CustomerHibernate.class);
        return factoryBean;
    }
    
    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
