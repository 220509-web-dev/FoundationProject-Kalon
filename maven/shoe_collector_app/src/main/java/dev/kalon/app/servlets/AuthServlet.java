package dev.kalon.app.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;

public class AuthServlet extends HttpServlet {

    private final ObjectMapper objectMapper;
//    private final AuthService authService;

    public AuthServlet(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
//        this.authService = authService;
    }

}
