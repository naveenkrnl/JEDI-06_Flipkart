package com.flipkart.restController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello-world")
public class HelloController {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}