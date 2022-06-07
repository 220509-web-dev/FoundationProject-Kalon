package dev.kalon.app.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kalon.app.entities.User;
import dev.kalon.app.daos.appUserDAO;
import dev.kalon.app.daos.appUserDAOPostgres;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;


public class UserServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final appUserDAOPostgres userDAO;


    public UserServlet(ObjectMapper mapper, appUserDAO userDAO) {
        this.mapper = mapper;
        this.userDAO = (appUserDAOPostgres) userDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("[LOG] - User Servlet received a request at " + LocalDateTime.now());
        System.out.println("[LOG] - Request URI: " + req.getRequestURI());
        System.out.println("[LOG] - Request method: " + req.getMethod());

        if ((req.getRequestURI().equals("/shoe_collector/users/"))) {

            appUserDAO appuserdao = new appUserDAOPostgres();
            String respPayload = mapper.writeValueAsString(appuserdao.getAllUsers());
            resp.setContentType("application/json");
            resp.getWriter().write("All Users" + respPayload);

        } else if ((req.getRequestURI().equals("/shoe_collector/users/id"))) {

            BufferedReader payloadReader = new BufferedReader(new InputStreamReader(req.getInputStream()));

            String line;
            while ((line = payloadReader.readLine()) != null){
                appUserDAO appuserdao = new appUserDAOPostgres();
                String respPayload = mapper.writeValueAsString(appuserdao.getById(Integer.parseInt(line)));
                resp.setContentType("application/json");
                resp.getWriter().write("User Found! " + respPayload);
            }

        } else if ((req.getRequestURI().equals("/shoe_collector/users/un"))) {

            BufferedReader payloadReader = new BufferedReader(new InputStreamReader(req.getInputStream()));

            String line;
            while ((line = payloadReader.readLine()) != null) {
                appUserDAO appuserdao = new appUserDAOPostgres();
                String respPayload = mapper.writeValueAsString(appuserdao.getByUsername(line));
                resp.setContentType("application/json");
                resp.getWriter().write("User Found! " + respPayload);
            }

        } else throw new RuntimeException ("Error connecting to database.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[LOG] - User Servlet received a request at " + LocalDateTime.now());
        System.out.println("[LOG] - Request URI: " + req.getRequestURI());
        System.out.println("[LOG] - Request method: " + req.getMethod());
        User newUser = mapper.readValue(req.getInputStream(), User.class);
        System.out.println(newUser);
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
