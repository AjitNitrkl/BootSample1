package com.example.hibernate;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HibUtil {
	
	//@Transactional(propagation = Propagation.REQUIRED)
	public void getDetails() throws ArithmeticException {
		int i=1/0;
	}

}
