package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.entity.Users;
import com.example.service.UserService;

@RestController
@RequestMapping(value = "/jpa")
public class TestController {
	
	@Autowired
	UserService service;
	
	 @Autowired
	private ModelMapper modelMapper;
	
	//@PostMapping(value = "/create")
	 @RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
	public User create(@PathVariable int id) {
		Users usrs = service.getUserFromCache(id);
		//service.createUser(user);
		//return new ResponseEntity<Users>(usrs, HttpStatus.OK);
		
		User usr = modelMapper.map(usrs, User.class);
		//usr.setUserId((long)usrs.getId());
		return usr;
	}
	 
	 @RequestMapping(value = "/create", method = RequestMethod.POST)
		public ResponseEntity<User> create() {
			Users usrs = service.getUserFromCache(1);
			User usr = modelMapper.map(usrs, User.class);
			return new ResponseEntity<User>(usr, HttpStatus.OK);
		
		}

}
