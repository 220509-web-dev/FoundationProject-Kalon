package dev.kalon.app.servlets;

import dev.kalon.daos.appUserDAO;
import dev.kalon.daos.appUserDAOPostgres;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class userServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("[LOG] - User Servlet received a request at " + LocalDateTime.now());
        System.out.println("[LOG] - Request URI: " + req.getRequestURI());
        System.out.println("[LOG] - Request method: " + req.getMethod());
        System.out.println("[LOG] - Request header, example: " + req.getHeader("example"));

        resp.setStatus(200);
        resp.setHeader("Content-type", "text/plain");
        resp.setHeader("App Users", "");
        resp.getWriter().write("This is the response payload");
        appUserDAO appuserdao = new appUserDAOPostgres();
        System.out.println(appuserdao.getAllUsers());
    }
}
