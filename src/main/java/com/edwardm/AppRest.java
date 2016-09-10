package com.edwardm;


import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/*
http://localhost:8080/CamelRestAuth/rest/api/
 */
@Path("/")
public class AppRest {

    @GET
    @Produces("application/json")
    @Path("test1")
    public AppResource test1() {
        return new AppResource("Anyone can get");
    }

    @GET
    @Produces("application/json")
    @Path("test2")
    public AppResource test2(@Context SecurityContext sc) {
        if (sc.getUserPrincipal() != null)
            return new AppResource("Anyone can get: " + sc.getUserPrincipal().getName());
        else
            return new AppResource("Anyone can get: Anonymous");
    }

    @GET
    @Produces("application/json")
    @Path("test3")
    @RolesAllowed("Admin")
    public AppResource test3() {
        return new AppResource("Only Admin role can get");
    }
}
