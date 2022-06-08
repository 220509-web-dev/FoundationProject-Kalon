package dev.kalon.app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kalon.app.daos.AppUserDAO;
import dev.kalon.app.daos.AppUserDAOPostgres;
import dev.kalon.app.services.AuthService;
import dev.kalon.app.services.UserService;
import dev.kalon.app.servlets.AuthServlet;
import dev.kalon.app.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.time.LocalDateTime;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was initialized at " + LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();

        AppUserDAO userDAO = new AppUserDAOPostgres();
        UserService userService = new UserService(userDAO);
        AuthService authService = new AuthService(userService);
        AuthServlet authServlet = new AuthServlet(authService, mapper);
        UserServlet userServlet = new UserServlet(mapper, userService, userDAO);

        ServletContext context = sce.getServletContext();

        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was destroyed at " + LocalDateTime.now());
    }
}
