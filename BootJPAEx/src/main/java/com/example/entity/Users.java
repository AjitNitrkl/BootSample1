package com.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Users  implements Serializable{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id @GeneratedValue
   @Column(name = "id")
   private int id;

   @Column(name = "first_name")
   private String name;

   @Column(name = "age")
   private int age;

   @Column(name = "salary")
   private int salary;
   
   @ManyToOne(fetch = FetchType.EAGER)
	  @JoinColumn(name = "comp_id")
   Company company;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
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

public Company getCompany() {
	return company;
}

public void setCompany(Company company) {
	this.company = company;
}
   
   
   
}
