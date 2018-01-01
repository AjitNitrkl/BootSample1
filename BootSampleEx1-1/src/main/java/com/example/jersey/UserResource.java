package com.example.jersey;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.hibernate.GeneralDAO;
 
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "users")
@Path("/users")
public class UserResource
{
	
	@Autowired
	GeneralDAO generalDAO;
    private static Map<Integer, User> DB = new HashMap<>();
    
    @RequestMapping(value = "/create" ,method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String  create(@Context UriInfo info ) {
    		String userName = info.getQueryParameters().getFirst("username");
    		String password = info.getQueryParameters().getFirst("password");
    	
		generalDAO.createLoginUser(userName, password);
		return "success";
}
    
    
   /* @GET
    @Path("jwt")
    @JWTTokenNeeded
    public Response echoWithJWTToken(@QueryParam("message") String message) {
        return Response.ok().entity(message == null ? "no message" : message).build();
    }*/
     
    @GET
    @Produces("application/json")
    public Users getAllUsers() {
        Users users = new Users();
        users.setUsers(new ArrayList<>(DB.values()));
        return users;
    }
    
    
    @GET
    @Path("cache/{id}")
    @Produces("application/json")
    public Response getCacheUserById(@PathParam("id") int id, @Context Request req) throws URISyntaxException
    {
    	
    	//Create cache control header
        CacheControl cc = new CacheControl();
        //Set max age to one day
        cc.setMaxAge(86400);
        
        Response.ResponseBuilder rb = null;
        
        //Calculate the ETag on last modified date of user resource 
        EntityTag etag = new EntityTag(DB.get(id).hashCode()+"");
        
        //Verify if it matched with etag available in http request
        rb = req.evaluatePreconditions(etag);
        
        //If ETag matches the rb will be non-null;
        //Use the rb to return the response without any further processing
        if (rb != null)
        {
            return rb.cacheControl(cc).tag(etag).build();
        }
        
        
		
        
      //If rb is null then either it is first time request; or resource is modified
        //Get the updated representation and return with Etag attached to it
        
        User user = DB.get(id);
        if(user == null) {
            return Response.status(404).build();
        }
        return Response
                .status(200)
                .entity(user)
                .contentLocation(new URI("/user-management/"+id)).cacheControl(cc).tag(etag).build();
    }
    
    
    
    
    
     
    @POST
    @Consumes("application/json")
    public Response createUser(User user) throws URISyntaxException
    {
        if(user.getFirstName() == null || user.getLastName() == null) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
        user.setId(DB.values().size()+1);
        user.setUri("/user-management/"+user.getId());
        DB.put(user.getId(), user);
        return Response.status(201).contentLocation(new URI(user.getUri())).build();
    }
 
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getUserById(@PathParam("id") int id) throws URISyntaxException
    {
        User user = DB.get(id);
        if(user == null) {
            return Response.status(404).build();
        }
        return Response
                .status(200)
                .entity(user)
                .contentLocation(new URI("/user-management/"+id)).build();
    }
    
    @GET
	@Path("/context")
    @Produces("application/json")
	public Response getUsers(@Context UriInfo info) throws URISyntaxException {

		int id = Integer.parseInt(info.getQueryParameters().getFirst("id"));
	
		
		User user = DB.get(id);
        if(user == null) {
            return Response.status(404).build();
        }
        return Response
                .status(200)
                .entity(user)
                .contentLocation(new URI("/user-management/"+id)).build();
		
    }
 
    @GET
	@Path("/matrix")
    @Produces("application/json")
	public Response getUsers(@MatrixParam("name") String name,
			@HeaderParam("user-agent") String userAgent,
			@Context HttpHeaders headers) throws URISyntaxException {

    	
		List<User> user = DB.values().stream().filter(s->s.getFirstName().equals("Ajit")).collect(Collectors.toList());
        if(user == null) {
            return Response.status(404).build();
        }
        
        String usrAgent = headers.getRequestHeader("user-agent").get(0);
        System.out.println("from http headers"+usrAgent);
        
        NewCookie cookie1 = new NewCookie("myCookie", "Cognizant Laptop");
        
        return Response
                .status(200)
                .entity(user.get(0))
                .cookie(cookie1)
               // .entity("addUser is called, userAgent : " + userAgent)
                .contentLocation(new URI("/user-management/"+user.get(0).getId())).build();
		
    }
    
    @GET
    @Path("/query")
    @Produces("application/json")
    public Response getUserById1(@QueryParam("id") int id, @CookieParam("myCookie") String strCookie) throws URISyntaxException
    {
    	System.out.println(strCookie);
        User user = DB.get(id);
        if(user == null) {
            return Response.status(404).build();
        }
        return Response
                .status(200)
                .entity(user)
                .contentLocation(new URI("/user-management/"+id)).build();
    }
    
    @POST
    @Path("/multivalued")
    //@Consumes(MediaType.APPLICATION_JSON)
    public String postMultivalued(@Context UriInfo info) {
    	 MultivaluedMap params =info.getQueryParameters();
    	 System.out.println(params.getFirst("id"));
    	 return "success";
     
    }
    
    
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUser(@PathParam("id") int id, User user) throws URISyntaxException
    {
        User temp = DB.get(id);
        if(user == null) {
            return Response.status(404).build();
        }
        temp.setFirstName(user.getFirstName());
        temp.setLastName(user.getLastName());
        DB.put(temp.getId(), temp);
        return Response.status(200).entity(temp).build();
    }
 
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) throws URISyntaxException {
        User user = DB.get(id);
        if(user != null) {
            DB.remove(user.getId());
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }
     
    static
    {
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Ajit");
        user1.setLastName("Behera");
        user1.setUri("/user-management/1");
 
        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Anil");
        user2.setLastName("Behera");
        user2.setUri("/user-management/2");
         
        DB.put(user1.getId(), user1);
        DB.put(user2.getId(), user2);
    }
}