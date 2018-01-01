package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.controller.User;
import com.example.controller.UserService;
import com.example.controller.WelcomeController;
import com.example.hibernate.GeneralDAO;
import com.example.hibernate.HibUtil;


@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(WelcomeController.class)
public class BootSampleEx11ApplicationTests {
	

    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private UserService service;
    
    
    @MockBean
	 private JdbcTemplate jdbcTemplate;
	 
    @MockBean
	 private HibUtil util;
	 
    @MockBean
	GeneralDAO generalDAO;
    
	User u = new User();
    
    
    List<User> mockUser = new ArrayList<User>();
    
    String exampleCourseJson = "{\"id\":\"1\",\"name\":\"Sachin\",\"age\":20\",\"sal\":1000\"]}";

    		

    
    @Before
    public void initialize() {
    	 mockUser.add(new User(1L,"Ajit",30, 70000));
       // mockUser.add(new User(2L,"Sujit",40, 50000));
    }
    
   
   
	@Test
	public void testAllUSers() throws Exception {
		Mockito.when(service.getUsers()).thenReturn(mockUser);
		
		RequestBuilder requestBuilder =  MockMvcRequestBuilders.get("/test/user");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals("{userId:1,userName:Ajit,age:30,salary:70000}",
				result.getResponse().getContentAsString().replace("[", ""), false);
	}
	
	@Test
	public void testCreateUser() throws Exception {
		Mockito.when(service.createUser(Mockito.any(User.class))).thenReturn("Success");
		
		RequestBuilder requestBuilder =  MockMvcRequestBuilders.post("/test/request")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals("Success", result.getResponse().getContentAsString());
		
		
	}
	
	

}