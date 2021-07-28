package com.flipkart.restController;

import org.glassfish.jersey.server.ResourceConfig;


public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        register(AdminRESTAPIController.class);
        register(UserRESTAPIController.class);
        register(StudentRESTAPIController.class);
        register(ProfessorRESTAPIController.class);
    }

}