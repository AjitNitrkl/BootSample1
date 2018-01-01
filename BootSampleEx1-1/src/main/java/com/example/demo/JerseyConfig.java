package com.example.demo;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.example.jersey.EchoEndpoint;
import com.example.jersey.JWTTokenNeededFilter;
import com.example.jersey.LoggingFilter;
import com.example.jersey.UserEndpoint;
import com.example.jersey.UserResource;
 
@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
    	 register(UserEndpoint.class);
        register(UserResource.class);
       // register(LoggingFilter.class);
        register(EchoEndpoint.class);
        register(JWTTokenNeededFilter.class);
        
        
       
    }
}