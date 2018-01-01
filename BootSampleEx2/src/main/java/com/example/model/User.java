package com.example.model;

public class User {
	
	private Long userId;
	private String userName;
	private int age;
	private int salary;
	User(){
		
	}
	
	User(Long id, String name, int age, int sal){
		this.userId = id;
		this.userName = name;
		this.age = age;
		this .salary = sal;
	}
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
	
	

}
