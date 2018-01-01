package com.example.hibernate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.UserService;

@RestController
public class CricketerController {
	
	@Autowired
	CricketerDAO cricketerDAO;
	
	@Autowired
	GeneralDAO generalDAO;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/allCricketers")
    public List<Cricketer> getCricketer() {
        try {
            return cricketerDAO.getAllCricketers();
        } catch (Exception ex) {
            return null;
        }
    }
	
	@RequestMapping(value = "/cricketer/{id}", method = RequestMethod.GET)
    public Cricketer cricketer(@PathVariable int id) {
        try {
        	System.out.println(id);
            return cricketerDAO.getCricketerById(id);
        } catch (Exception ex) {
            return null;
        }
    }
	
	@RequestMapping(value = "/saveCricketer", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public String saveCricketer(@RequestBody Cricketer cricketer) {
        try {
        	System.out.println("working");
            return  cricketerDAO.saveCricketer(cricketer);
        } catch (Exception ex) {
        	throw new UserNotFoundException("User not found");
        }
    }
	
	
	@RequestMapping(value = "/getCricketerByCountry/{country}", method = RequestMethod.GET)
    public List<Cricketer> getCricketerByCountry(@PathVariable String country) {
        try {
          return cricketerDAO.getAllCricketers().stream().filter((s)->s.getName().equalsIgnoreCase(country)).collect(Collectors.toList());
        	 
        	// return cricketerDAO.getAllCricketers().stream().map(s->s.getName()).collect(Collectors.toList());
         //or return cricketerDAO.getAllCricketers().stream().map(Cricketer::getCountry).collect(Collectors.toList());
        	
        } catch (Exception ex) {
        	throw new UserNotFoundException("User not found");
        }
    }
	
	@RequestMapping(value = "/createEmp", method = RequestMethod.POST)
    public String createEmployee() {
		
		generalDAO.create();
		return "success";
		
	}
	
	
	
	

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	 class UserNotFoundException extends RuntimeException {

		public UserNotFoundException(String userId) {
			super("could not find user '" + userId + "'.");
		}
	}

}
