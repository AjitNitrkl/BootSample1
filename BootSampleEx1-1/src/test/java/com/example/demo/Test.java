package com.example.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

	
	
	public static String m1() {
		 for(int i=0; i<=10; i++) {
		 System.out.println("Child Thread");
		
		 }
		 return "Child Thread returned";
		}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		
		String s1="test";
		String s2="test";
		
		if(s1==s2)
			System.out.println("yes");
		if(s1.equals(s2))
			System.out.println("yes");
		 Executor e = Executors.newFixedThreadPool(3); 
        Runnable r=()->System.out.println("test");
        e.execute(r); //Executor has no submit method
        ExecutorService e1 = Executors.newFixedThreadPool(3); 
        Callable r1=Test::m1;	
        Future<String> future = e1.submit(r1); //ExecutorService has submit method
        System.out.println(future.get());
        e1.shutdown();
        /*Executors class Factory and utility methods for Executor, ExecutorService,
        ScheduledExecutorService, ThreadFactory, and Callable classes defined in this package.*/
        

	}

}
