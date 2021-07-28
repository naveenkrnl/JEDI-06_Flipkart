package com.flipkart.restController;

import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.utils.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/admin")
public class AdminRESTAPIController {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, I am admin!";
    }

    @Path("/login")
    @POST
    @Produces("text/plain")
    public javax.ws.rs.core.Response loginUser(User user) {
        System.out.println(user.getUserId());
        System.out.println(user.getPassword());
        return javax.ws.rs.core.Response.status(200).entity("Login successful").build();
    }
    /**
     * Method to add Professor to DB
     */
    @POST
    @Path("/addProfessor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfessor(Professor professor) {

        AdminInterface adminOperation = AdminOperation.getInstance();

        try {
            adminOperation.addProfessor(professor);

        } catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
            StringUtils.printErrorMessage(e.getMessage());
            Response.status(500).entity("Operation Failed. "+e.getMessage()).build();
        }
        return Response.status(200).entity("Operation Successful").build();

    }

}
