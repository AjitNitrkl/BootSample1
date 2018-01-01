package com.example.hibernate;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
 
@Repository
@Transactional
public class CricketerDAO {
    @Autowired
    private SessionFactory sessionFactory;
 
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
 
    public String saveCricketer(Cricketer cricketer) {
        int isSuccess = (Integer)getSession().save(cricketer);
        if(isSuccess >= 1){
            return "Success";
        }else{
            return "Error while Saving Cricketer";
        }
         
    }
 
    public boolean delete(Cricketer cricketer) {
        getSession().delete(cricketer);
        return true;
    }
    
    public  Cricketer getCricketerById(int id) {
    	Query query = getSession().createQuery("from Cricketer where id=:id");
    	query.setParameter("id", id);
    	return (Cricketer)query.list().get(0);
    	
    }
 
    @SuppressWarnings("unchecked")
    public List<Cricketer> getAllCricketers() {
        return getSession().createQuery("from Cricketer").list();
    }
}