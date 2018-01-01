package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserCompanyRepository;
import com.example.dao.UserRepository;
import com.example.entity.Users;

@Service("userService")
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userDao;
	
	@Autowired
	UserCompanyRepository compDao;
	
	public void createUser(Users user) {
		userDao.save(user);
	}
	
	@Cacheable(value="userCache",key="#id")
	public Users getUserFromCache(int  id) {
		return userDao.findOne(id);
	}
	
	/* @CachePut(value="userCache",key="#userCacheId")
		public List<Users> updateUserCache() {
	    	List<Users> userData = (List<Users>) userDao.findAll();
			return userData;
		}*/
	 
	 @CachePut(value = "userCache", key = "#usr.id" , unless="#result==null")
	    public Users updateUserCache() {
		 List<Users> userData = (List<Users>) userDao.findAll();
	        for(Users usr : userData){
	                return usr;
	        }
	        return null;
	    }
}