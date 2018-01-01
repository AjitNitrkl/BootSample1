package com.example.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company implements Serializable {
	
	@Id @GeneratedValue
	@Column(name = "comp_id")
	private int compId;
	@Column(name = "comp_name")
	private String compName;
	@Column(name = "head_count")
	private String compHCount;
	@Embedded
	private Address compAddress;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER)
	private Collection<Users> user;
	
	
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompHCount() {
		return compHCount;
	}
	public void setCompHCount(String compHCount) {
		this.compHCount = compHCount;
	}
	public Address getCompAddress() {
		return compAddress;
	}
	public void setCompAddress(Address compAddress) {
		this.compAddress = compAddress;
	}
	public Collection<Users> getUser() {
		return user;
	}
	public void setUser(Collection<Users> user) {
		this.user = user;
	}
	
	
	

}
