package dev.kalon.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kalon.app.app;
import dev.kalon.app.servlets.AuthServlet;
import dev.kalon.app.servlets.UserServlet;
import dev.kalon.daos.appUserDAO;
import dev.kalon.daos.appUserDAOPostgres;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.time.LocalDateTime;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was initialized at " + LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        appUserDAO userDAO = new appUserDAOPostgres();
        UserServlet userServlet = new UserServlet(mapper, userDAO);
        AuthServlet authServlet = new AuthServlet(mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was destroyed at " + LocalDateTime.now());
    }
}
