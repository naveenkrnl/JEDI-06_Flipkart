package com.flipkart.restController;

import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
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


//    /**
//     * Method to add Administrative Account
//     *
//     * @param name Name
//     * @param userID User ID
//     * @param password Password
//     * @param gender Gender
//     * @param address Address
//     * @param country Country
//     * @return Admin ID
//     */
//
//    public int register(String name, String userID, String password, Gender gender, String address,
//                        String country) throws AdminAccountNotCreatedException {
//        int adminId = 0;
//        try {
//            User admin = new Admin(userID, name, Role.ADMIN, password, gender, address, country);
//            adminId = adminDaoOperation.addAdmin(admin) ;
//
//        } catch (AdminAccountNotCreatedException ex) {
//            throw ex;
//        }
//        return adminId;
//    }

    /**
     * Method to add Professor to DB
     */
    @POST
    @Path("/addProfessor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfessor(Professor professor) {

        AdminDaoInterface adminDaoOperation = AdminDaoOperation.getInstance();

        try {
            adminDaoOperation.addProfessor(professor);

        } catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
            StringUtils.printErrorMessage(e.getMessage());
            Response.status(500).entity("Operation Failed. "+e.getMessage()).build();
        }
        return Response.status(200).entity("Operation Successful").build();

    }

}
