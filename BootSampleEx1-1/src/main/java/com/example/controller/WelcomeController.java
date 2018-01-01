package com.example.controller;

import java.util.List;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.ConfigProperties;

@RestController
@PropertySource("classpath:global.properties")
@RequestMapping(value = "/test")
public class WelcomeController {
	
	@Autowired
	UserService userService;
	
	 @Value("${user.email}")
	 private String email;
	 
	 //or inject the properties file bean
	 @Autowired
	 ConfigProperties config;
	 
	
	 @RequestMapping(value = "/welcome", method = RequestMethod.GET)
	 public String welcome() {
		return "Welcome to Spring Boot";
	}
	 
	//@PostMapping(value = "/request")
	 @RequestMapping(value = "/request", method = RequestMethod.POST)
	@Consumes("application/json")
	 public String createUser(@RequestBody User user) {
		System.out.println("Name is"+user.getUserName());
		userService.createUser(user);
		return "Welcome to Spring Boot";
	}
	 
	 @RequestMapping(value = "/user", method = RequestMethod.GET)
		public ResponseEntity<List<User>> listAllUSers() {
		 
		List<User> users =  userService.getUsers();
		 
	        if (users == null) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
	 
	@RequestMapping(value = "/users" ,method = RequestMethod.GET,
             produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<User>  getAllUsers() {
		List<User> users =  userService.getUsers();
		return users;
}
	
	 @RequestMapping(value = "/employees", method = RequestMethod.GET)
		public List<Employee> listAllEmployees() {
		 System.out.println("Requested by -"+email);
		 System.out.println("Request by -"+config.getAddress());
		 
		 return userService.getAllEmployees();
	 }
	
	
	

}
