package com.mycompany.basespringmvc;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.mycompany.basespringmvc.dao.ArticleDao;
import com.mycompany.basespringmvc.dao.ArticleDaoImpl;
import com.mycompany.basespringmvc.dao.CustomerDao;
import com.mycompany.basespringmvc.dao.CustomerDaoImpl;
import com.mycompany.basespringmvc.models.Article;
import com.mycompany.basespringmvc.models.Brand;
import com.mycompany.basespringmvc.models.City;
import com.mycompany.basespringmvc.models.Customer;
import com.mycompany.basespringmvc.services.ArticleService;
import com.mycompany.basespringmvc.services.ArticleServiceImpl;

@TestConfiguration
public class BaseSpringMvcApplicationTestConfig {

	@Bean
	public ArticleService articleService() {
		return new ArticleServiceImpl();
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
        factoryBean.setAnnotatedClasses(Customer.class, City.class, Article.class, Brand.class);
        return factoryBean;
    }
    
    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
	
}
