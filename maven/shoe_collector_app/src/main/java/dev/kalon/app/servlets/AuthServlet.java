package dev.kalon.app.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kalon.app.entities.User;
import dev.kalon.app.services.AuthService;
import dev.kalon.app.utils.exceptions.InvalidCredentialsException;
import dev.kalon.app.utils.exceptions.UsernameNotAvailableException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthServlet extends HttpServlet {

    private final ObjectMapper objectMapper;
    private final AuthService authService;

    public AuthServlet(AuthService authService, ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
        this.authService = authService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();

        String line;
        StringBuilder stringBuilder = new StringBuilder();

        while ((line = reader.readLine()) != null){
            stringBuilder.append(line);
        }
        String body = stringBuilder.toString();
        System.out.println("body: " + body);

        User userCredentials = objectMapper.readValue(body, User.class);

        String uri = req.getRequestURI().replace("/shoe_collector/auth", "");

        if (uri.equals("/register")) {

            try {
                authService.register(userCredentials);
                PrintWriter writer = resp.getWriter();
                writer.println("User registered successfully!");
                resp.setStatus(201);
            } catch (UsernameNotAvailableException e) {
                e.printStackTrace();
                resp.setStatus(400);
                PrintWriter writer = resp.getWriter();
                writer.println("Username is already taken");
            } catch (InvalidCredentialsException e) {
                e.printStackTrace();
                resp.setStatus(400);
                PrintWriter writer = resp.getWriter();
                writer.write("Invalid input");
                return;
            } catch (Throwable t) {
                t.printStackTrace();
                resp.setStatus(500);
                PrintWriter writer = resp.getWriter();
                writer.println("Some error occurred");
            }
        }
        if (uri.equals("/login")) {

            try {

                User user = authService.login(userCredentials.getUsername(), userCredentials.getPassword());

                HttpSession session = req.getSession();
                session.setAttribute("currentUser", user);
                PrintWriter writer = resp.getWriter();
                writer.println(objectMapper.writeValueAsString(user));
                resp.setStatus(202);
                resp.setContentType("application/json");
            } catch (InvalidCredentialsException e) {

                e.printStackTrace();
                resp.setStatus(400);
                PrintWriter writer = resp.getWriter();
                writer.println("Invalid credentials provided.");
            }
        } else {
            resp.setStatus(404);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        resp.setStatus(204);
    }
}
