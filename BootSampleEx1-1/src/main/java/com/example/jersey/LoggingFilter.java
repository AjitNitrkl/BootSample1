package com.example.jersey;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext req, ContainerResponseContext res) throws IOException {
		
		System.out.println("Request Filter");
		
	}

	@Override
	public void filter(ContainerRequestContext req) throws IOException {
		System.out.println("Request Filter");
		
	}
	
}