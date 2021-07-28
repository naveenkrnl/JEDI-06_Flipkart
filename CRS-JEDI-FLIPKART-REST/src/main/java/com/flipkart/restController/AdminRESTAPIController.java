package com.flipkart.restController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

//REST admin
@Path("/admin")
public class AdminRESTAPIController {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, I am admin!";
    }
}
