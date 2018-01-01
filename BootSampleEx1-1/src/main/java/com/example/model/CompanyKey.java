package com.example.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
public class CompanyKey implements Serializable {
 
	
    @Column(name = "companyid")
    private int companyId;
 
    @Column(name = "companyname")
    private String companyName;
 
    public CompanyKey() {
    }
 
 
  

	public int getCompanyId() {
		return companyId;
	}




	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	



	public String getCompanyName() {
		return companyName;
	}




	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}




	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyKey)) return false;
        CompanyKey that = (CompanyKey) o;
        return Objects.equals(getCompanyId(), that.getCompanyId()) &&
                Objects.equals(getCompanyName(), that.getCompanyName());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getCompanyId(), getCompanyName());
    }
}