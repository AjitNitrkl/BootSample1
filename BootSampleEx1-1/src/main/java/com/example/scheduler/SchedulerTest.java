package com.example.scheduler;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class SchedulerTest {
	
	@Scheduled(fixedDelay = 5000)
	 public void fixedDelayTask() {
	 System.out.println(new Date() + " This runs in a fixed delay");
	 }


}
