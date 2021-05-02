package com.mycompany.basespringmvc;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mycompany.basespringmvc.controllers.ArticleController;
import com.mycompany.basespringmvc.models.Brand;
import com.mycompany.basespringmvc.services.ArticleService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ArticleController.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@Import(BaseSpringMvcApplicationTestConfig.class)
public class ArticleControllerTest {
	
	//N.B commentare il contenuto del run nel BaseSpringMVCApplication

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ArticleService articleService;
	
	@Test
	public void greetingShouldReturnMessageFromService() throws Exception {
		
		ArrayList<Brand> mokeBrands = new ArrayList<Brand>();
	    mokeBrands.add(new Brand("100", "Sony"));
	    mokeBrands.add(new Brand("200", "LG"));
	    
	    Mockito.when(articleService.getAllBrands()).thenReturn(mokeBrands);
	    
	    this.mvc.perform(get("/brands"))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(content().string("[{\"id\":\"100\",\"name\":\"Sony\"},{\"id\":\"200\",\"name\":\"LG\"}]"));

		//this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
		//		.andExpect(content().string(containsString("Hello, Mock")));
	}
}
