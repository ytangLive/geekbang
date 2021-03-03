package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/register")
public class RegisterController implements PageController {

    private UserService userService = new UserServiceImpl();

    @POST
    @Path("/action")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {

        String uid = request.getParameter("uid");
        String psw = request.getParameter("psw");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        if(uid == null || uid.length() == 0 || psw == null || psw.length() == 0){
            return "register/error.jsp";
        }

        User user = new User();
        user.setName(uid);
        user.setPassword(psw);
        user.setEmail(email);
        user.setPhoneNumber(phone);

        Boolean succeed = userService.register(user);

        if(succeed){
            return "register/success.jsp";
        }else{
            return "register/error.jsp";
        }
    }

}
