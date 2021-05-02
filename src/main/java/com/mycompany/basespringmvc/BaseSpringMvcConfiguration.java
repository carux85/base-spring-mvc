package com.mycompany.basespringmvc;

import java.util.Locale;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.mycompany.basespringmvc.models.Article;
import com.mycompany.basespringmvc.models.Brand;
import com.mycompany.basespringmvc.models.City;
import com.mycompany.basespringmvc.models.ContactForm;
import com.mycompany.basespringmvc.models.Customer;
import com.mycompany.basespringmvc.models.SimpleBean;

@Configuration
@EnableTransactionManagement
public class BaseSpringMvcConfiguration implements WebMvcConfigurer{

    @Bean(name="defaultContactForm")
    public ContactForm defaultContactForm() {
    	ContactForm form = new ContactForm();
    	form.setSubject("Info request");
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
    
    @Bean
    @Scope("prototype")
    public Logger logger(final InjectionPoint ip) {
    	
       if(ip.getField()!=null)
    	   return LoggerFactory.getLogger(ip.getField().getDeclaringClass());
       
       return LoggerFactory.getLogger("");
       
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
    
    
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
          = new ReloadableResourceBundleMessageSource();
        
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
