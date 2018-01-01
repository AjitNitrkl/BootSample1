package com.example.hibernate;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.jersey.User;
import com.example.model.CompanyBranch;
import com.example.model.CompanyKey;
import com.example.model.Stock;
import com.example.model.StockDetail;
import com.example.util.PasswordUtils;


@Repository
//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class GeneralDAO {
    @Autowired
    private SessionFactory sessionFactory;
    
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
 
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public void create() {
    	Company company = new Company();
    	Set<Empl> employeeList = new HashSet<Empl>();
    	CompanyKey companyKey = new CompanyKey();
    	companyKey.setCompanyName("COGNIZANT");
    	//companyKey.setCompanyId(1);
		company.setCompanyKey(companyKey );
		Set<CompanyBranch> branch = new HashSet<CompanyBranch>();
		CompanyBranch branch1 = new CompanyBranch();
		branch1.setBranchCity("Chicago");
		branch1.setBranchName("Deerfield branch");
		branch.add(branch1 );
		//company.setBranches(branch );
    	Empl emp1 = new Empl();
    	emp1.setEmpName("Ajit");
    	Address addr = new Address();
    	addr.setStreet("1651 EASTON CTS");
    	addr.setCity("WHEELING");
    	addr.setZip("60090");
    	addr.setCountry("USA");
		emp1.setHomeAddr(addr);
		Address offcAddr = new Address();
		offcAddr.setCity("DEERFIELD");
		offcAddr.setStreet("102 WILMOT ROAD");
		emp1.setOffcAddr(offcAddr );
		
    	emp1.setCompany(company);
    	Set<String> phoneNumbers = new HashSet<String>();
    	phoneNumbers.add("4802499268");
		emp1.setPhoneNumbers(phoneNumbers);
    	employeeList.add(emp1);
    	//getSession().save(emp1);
		company.setEmployeeList(employeeList);
    	//getSession().save(company);
		
		getSession().persist(company);
    }

    
	public String saveStock() {
		
		Stock stock = new Stock();

		stock.setStockCode("123");
		stock.setStockName("CTS");

		StockDetail stockDetail = new StockDetail();
		stockDetail.setCompName("CTS INDIA");

		stock.setStockDetail(stockDetail);
		stockDetail.setStock(stock);

		getSession().save(stock);
		
		
		return "success";
	}
    
	public String insertPhone() {
		String sql = "INSERT INTO PHONE " +
				"(empid, phonenumber) VALUES (?, ?)";
		jdbcTemplate.update(sql, new Object[] { 1,
				"4802499268"
			});
		return "success";
	}

	public int insertPhone2() {
		String sql = "INSERT INTO PHONE " +
				"(empid1, phonenumber) VALUES (?, ?)";
		jdbcTemplate.update(sql, new Object[] { 2,
				"4802499268"
			});
		
		return 0;
	}

	public int getUser(String usr, String pswd) {
		
		String sql = "SELECT * FROM login where username=:usr and password=:pswd";
		
		int count = jdbcTemplate.update(sql,  new Object[] { usr,
				PasswordUtils.digestPassword(pswd)
			});
		return count;
	}
	
public int createLoginUser(String usr, String pswd) {
		
	String sql = "INSERT INTO LOGIN " +
			"(username, password) VALUES (?, ?)";
		
		int count = jdbcTemplate.update(sql,  new Object[] { usr,
				PasswordUtils.digestPassword(pswd)
			});
		return count;
	}
}
