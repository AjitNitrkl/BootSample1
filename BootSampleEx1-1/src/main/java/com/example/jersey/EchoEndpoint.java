package com.example.jersey;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/echo")
//@Produces(TEXT_PLAIN)
public class EchoEndpoint {
 
    @GET
    public Response echo(@QueryParam("message") String message) {
        return Response.ok().entity(message == null ? "no message" : message).build();
    }
 
    @GET
    @Path("jwt")
    @JWTTokenNeeded
    @RolesAllowed("SECRET")
    public Response echoWithJWTToken(@QueryParam("message") String message) {
        return Response.ok().entity(message == null ? "no message" : message).build();
    }
}