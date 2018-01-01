package com.example.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import com.example.model.Empl;
import com.example.model.User;

@RestController
@RequestMapping(value = "/test")
public class WelcomeController {
	
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	
	 @RequestMapping(value = "/welcome", method = RequestMethod.GET)
	 public String welcome() {
		return "Welcome to Spring Boot";
	}
	 
	@PostMapping(value = "/request",consumes = MediaType.APPLICATION_JSON_VALUE )
	// @RequestMapping(value = "/request", method = RequestMethod.POST)
	// ResponseBody is not required diff with controller
	 public ResponseEntity<User> request( @RequestBody User user) {
		System.out.println("Name is"+user.getUserName());
		 return new ResponseEntity<User>(user, HttpStatus.OK);
 
	}
	
	 
		
		
		

}
