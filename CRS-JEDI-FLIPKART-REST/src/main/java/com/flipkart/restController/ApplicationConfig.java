package com.flipkart.restController;

import org.glassfish.jersey.server.ResourceConfig;


public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        // All the web servies to be registered Here
//        register(StudentRestAPI.class);
//        register(UserRestAPI.class);
//        register(ProfessorRestAPI.class);
//        register(AdminRestAPI.class);
        register(HelloController.class);
        register(AdminRESTAPIController.class);
        register(UserRESTAPIController.class);


    }

}