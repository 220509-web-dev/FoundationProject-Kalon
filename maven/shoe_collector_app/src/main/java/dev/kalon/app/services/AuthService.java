package dev.kalon.app.services;

import dev.kalon.app.daos.appUserDAO;
import dev.kalon.app.entities.User;
import dev.kalon.app.utils.exceptions.InvalidCredentialsException;
import dev.kalon.app.utils.exceptions.UsernameNotAvailableException;

public class AuthService {

    private final appUserDAO userDAO;

    public AuthService(appUserDAO userDAO){
        this.userDAO = userDAO;
    }


    public static void register(User userToBeRegistered) {

        if (userService.getUserByUsername(userToBeRegistered.getUsername()) != null) {

            throw new UsernameNotAvailableException("Username is already taken.");
        }

        userService.insert(userToBeRegistered);
    }

    UserService userService = new UserService();

    public User login(String username, String password) {

        User user;

        user = userService.getUserByUsername(username);

        if (user != null && password.equals(user.getPassword())) {

            System.out.println("Logged in successfully!");
            return user;

        } else if (user != null && !password.equals(user.getPassword())) {

            System.out.println("Wrong Password");
            throw new InvalidCredentialsException("Wrong password entered.");

        } else {

            System.out.println("User Does not exist!");
            throw new InvalidCredentialsException("User does not exist.");
        }
    }
}


