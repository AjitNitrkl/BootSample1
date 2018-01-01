package com.example.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Company;

public interface UserCompanyRepository extends CrudRepository<Company, Integer>{

}
