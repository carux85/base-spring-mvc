package com.mycompany.basespringmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.mycompany.basespringmvc.dao.ArticleDao;
import com.mycompany.basespringmvc.models.Article;
import com.mycompany.basespringmvc.models.Brand;
import com.mycompany.basespringmvc.models.ContactForm;
import com.mycompany.basespringmvc.services.ArticleService;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = BaseSpringMvcApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties") 
//@Import(BaseSpringMvcApplicationTestConfig.class)
class BaseSpringMvcApplicationTests {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private ArticleService articleService;
	
	@MockBean
	private ArticleDao articleDao;
	
	@Test
	void contextLoads(){
		
	}

	@Test
	void controller_hello_parameter() throws Exception{
		
		this.mvc.perform(get("/hello")
				.param("name", "daniele")
		)
		.andExpect(status().isOk())
        .andExpect(view().name("hello"))
        .andExpect(forwardedUrl("/WEB-INF/jsp/hello.jsp"))
        .andExpect(model().attribute("name", is("daniele")));

		/*this.mvc.perform(
				post("/contact")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("name", "myname")
					.param("surname", "mysurname")
					.param("email", "a.b@myemail.it")
					.param("subject", "mysubject")
					.param("message", "mymessage")
					)
				.andExpect(content().string("Read x-www-form-urlencoded: ContactForm {name=[myname], surname=[mysurname], email=[a.b@myemail.it], subject=[mysubject], message=[mymessage]}"));*/
	}
	
	@Test
	void controller_hello_variable() throws Exception{
		
		this.mvc.perform(get("/hello/{name}", "daniele"))
		.andExpect(status().isOk())
        .andExpect(view().name("hello"))
        .andExpect(forwardedUrl("/WEB-INF/jsp/hello.jsp"))
        .andExpect(model().attribute("name", is("daniele")));
    }
	
	@Test
	void controller_contact_get() throws Exception{
		
		this.mvc.perform(get("/contact"))
		.andExpect(status().isOk())
        .andExpect(view().name("contact")) //jsp name
        .andExpect(forwardedUrl("/WEB-INF/jsp/contact.jsp"))
        .andExpect(model().attribute("contactform", isA(ContactForm.class)))
		.andExpect(model().attribute("contactform", hasProperty("subject", is("Info request"))));
        //.andExpect(model().attribute("todos", hasSize(2)))
        //.andExpect(model().attribute("todos", hasItem(
        //        allOf(
        //                hasProperty("id", is(1L)),
        //                hasProperty("description", is("Lorem ipsum")),
        //                hasProperty("title", is("Foo"))
        //        )
        //)))
        //.andExpect(model().attribute("todos", hasItem(
        //        allOf(
        //                hasProperty("id", is(2L)),
        //                hasProperty("description", is("Lorem ipsum")),
        //                hasProperty("title", is("Bar"))
        //        )
        //)));
    }
	
	@Test
	void controller_contact_post() throws Exception{
		
		this.mvc.perform(
				post("/contact")
					.param("name", "myname")
					.param("surname", "mysurname")
					.param("email", "a.b@myemail.it")
					.param("subject", "mysubject")
					.param("message", "mymessage")
					)
		.andExpect(status().isOk())
        .andExpect(view().name("confirmContact")) //jsp name
        .andExpect(forwardedUrl("/WEB-INF/jsp/confirmContact.jsp"))
        .andExpect(model().attribute("contactform", isA(ContactForm.class)))
        .andExpect(model().attribute("contactform", hasProperty("name", is("myname"))))
		.andExpect(model().attribute("contactform", hasProperty("surname", is("mysurname"))))
		.andExpect(model().attribute("contactform", hasProperty("email", is("a.b@myemail.it"))))
		.andExpect(model().attribute("contactform", hasProperty("subject", is("mysubject"))))
		.andExpect(model().attribute("contactform", hasProperty("message", is("mymessage"))))
		.andExpect(model().attribute("name", is("myname")));
    }
	
	@Test
	public void restcontroller_brands() throws Exception {
		
		ArrayList<Brand> mokeBrands = new ArrayList<Brand>();
	    mokeBrands.add(new Brand("100", "Sony"));
	    mokeBrands.add(new Brand("200", "LG"));
	    
	    Mockito.when(articleService.getAllBrands()).thenReturn(mokeBrands);
	    
	    this.mvc.perform(get("/brands"))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(content().string("[{\"id\":\"100\",\"name\":\"Sony\"},{\"id\":\"200\",\"name\":\"LG\"}]"));

	}
	
	/*
	@Test
	public void articleService_getArticle() throws Exception {
		
		Article mokeArticle = new Article("10", "Smartphone", new Brand("100", "Sony"));
	    Mockito.when(articleDao.findArticleById(mokeArticle.getId())).thenReturn(mokeArticle);
	    
	    Article found = articleService.getArticle(mokeArticle.getId());
	    
	    assertThat(found.getName(), equalTo(mokeArticle.getName()));
	    assertThat(found.getBrand().getName(), equalTo(mokeArticle.getBrand().getName()));

	}
    */
}
