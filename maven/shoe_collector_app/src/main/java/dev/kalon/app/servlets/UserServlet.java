package dev.kalon.app.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kalon.app.daos.AppUserDAO;
import dev.kalon.app.entities.User;
import dev.kalon.app.services.UserService;
import dev.kalon.app.utils.exceptions.BadRequestException;
import dev.kalon.app.utils.exceptions.InvalidUsernameException;
import dev.kalon.app.utils.exceptions.ResourceNotFoundException;
import dev.kalon.app.utils.exceptions.UsernameNotAvailableException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


public class UserServlet extends HttpServlet {

    private final ObjectMapper mapper;

    private final UserService userService;
    private final AppUserDAO userDAO;


    public UserServlet(ObjectMapper mapper, UserService userService, AppUserDAO userDAO) {
        this.mapper = mapper;
        this.userService = userService;
        this.userDAO = userDAO;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("[LOG] - User Servlet received a request at " + LocalDateTime.now());
        System.out.println("[LOG] - Request URI: " + req.getRequestURI());
        System.out.println("[LOG] - Request method: " + req.getMethod());

            //Get all users
        if ((req.getRequestURI().equals("/shoe_collector/users/"))) {

            String respPayload = mapper.writeValueAsString(userService.getAllUsers());
            resp.setContentType("application/json");
            resp.getWriter().write(respPayload);

            //Find user by ID
        } else if ((req.getRequestURI().equals("/shoe_collector/users/id"))) {

            try {

                String id = req.getParameter("id");
                User foundUser = userService.getUserById(id);
                String respPayload = mapper.writeValueAsString(foundUser);
                resp.setContentType("application/json");
                resp.getWriter().write(respPayload);

            } catch (BadRequestException e ) {
                resp.setStatus(400);
                resp.getWriter().write(e.getMessage());
            } catch (ResourceNotFoundException e) {
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
            } catch (Exception e ) {
                resp.setStatus(500);
                resp.getWriter().write("Please check application logs");
            }

            //Find user by Username
        } else if ((req.getRequestURI().equals("/shoe_collector/users/un"))) {

            try {

                String username = req.getParameter("un");
                User foundUser = userService.getUserByUsername(username);
                String respPayload = mapper.writeValueAsString(foundUser);
                resp.setContentType("application/json");
                resp.getWriter().write(respPayload);

            } catch (InvalidUsernameException e ) {
                resp.setStatus(400);
                resp.getWriter().write(e.getMessage());
            } catch (ResourceNotFoundException e) {
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
            } catch (Exception e ) {
                resp.setStatus(500);
                resp.getWriter().write("Please check application logs");
            }
        }
    }

    //Create a new user and persist to the database
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[LOG] - User Servlet received a request at " + LocalDateTime.now());
        System.out.println("[LOG] - Request URI: " + req.getRequestURI());
        System.out.println("[LOG] - Request method: " + req.getMethod());
        try {

            User userToBeRegistered = mapper.readValue(req.getInputStream(), User.class);
            System.out.println(userToBeRegistered);

            if (userToBeRegistered == null
                    || userToBeRegistered.getFirstName() == null
                    || userToBeRegistered.getLastName() == null
                    || userToBeRegistered.getBirthDate() == null
                    || userToBeRegistered.getUsername() == null
                    || userToBeRegistered.getPassword() == null
                    || userToBeRegistered.equals("")
                    || userToBeRegistered.getFirstName().equals("")
                    || userToBeRegistered.getLastName().equals("")
                    || userToBeRegistered.getBirthDate().equals("")
                    || userToBeRegistered.getUsername().equals("")
                    || userToBeRegistered.getPassword().equals("")
                    || userToBeRegistered.getStatusId() < 1
                    || userToBeRegistered.getStatusId() > 3) {
                throw new BadRequestException("Provided user data was invalid. First name, last name, birth date, username, status id, and password must not be null or empty");
            }
            userDAO.create(userToBeRegistered);
            resp.setStatus(201);
            resp.getWriter().write("User created successfully!");
            return;

        } catch (UsernameNotAvailableException e){
            resp.setStatus(400);
            resp.getWriter().write(e.getMessage());
        }
        resp.setStatus(204);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[LOG] - User Servlet received a request at " + LocalDateTime.now());
        System.out.println("[LOG] - Request URI: " + req.getRequestURI());
        System.out.println("[LOG] - Request method: " + req.getMethod());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[LOG] - User Servlet received a request at " + LocalDateTime.now());
        System.out.println("[LOG] - Request URI: " + req.getRequestURI());
        System.out.println("[LOG] - Request method: " + req.getMethod());
    }
}
