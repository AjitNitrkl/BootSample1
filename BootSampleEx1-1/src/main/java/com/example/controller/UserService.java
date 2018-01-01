package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.hibernate.GeneralDAO;
import com.example.hibernate.HibUtil;

@Service("userService")
public class UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	private static List<User> users;
	
	
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
	 @Autowired
	 private HibUtil util;
	 

		@Autowired
		GeneralDAO generalDAO;
	
	static{
        users= populateDummyUsers();
    }
	
	
	public List<User> getUsers(){
		return users;
	}
	
	public String createUser(User user) {
		users.add(user);
		return "success";
	}

	private static List<User> populateDummyUsers() {
		
		List<User> users = new ArrayList<User>();
        users.add(new User(counter.incrementAndGet(),"Ajit",30, 70000));
        users.add(new User(counter.incrementAndGet(),"Sujit",40, 50000));
        users.add(new User(counter.incrementAndGet(),"Anil",45, 30000));
        users.add(new User(counter.incrementAndGet(),"Sunil",50, 40000));
        return users;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Employee> getAllEmployees(){
		
	return	 jdbcTemplate.query(
                "SELECT empno1, ename, job, sal, deptno FROM emp",
                (rs, rowNum) -> new Employee(rs.getInt("empno"),
                        rs.getString("ename"), rs.getString("job"), 
                        rs.getInt("sal"),
                        rs.getInt("deptno"))
        );
		

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertStock() throws Exception{
		try {
		generalDAO.insertPhone();
		
			//util.getDetails();
			int i = generalDAO.insertPhone2();
		}catch(Exception e) {
			e.printStackTrace();
		}
			/*if(i==0)
				throw new Exception();*/
			
		//generalDAO.saveStock();
		/*try {
			generalDAO.create();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	

}
