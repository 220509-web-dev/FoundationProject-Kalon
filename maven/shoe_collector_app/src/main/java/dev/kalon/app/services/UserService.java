package dev.kalon.app.services;

import dev.kalon.app.daos.AppUserDAO;
import dev.kalon.app.entities.User;
import dev.kalon.app.utils.exceptions.BadRequestException;
import dev.kalon.app.utils.exceptions.InvalidUsernameException;
import dev.kalon.app.utils.exceptions.ResourceNotFoundException;

import java.util.List;

public class UserService {

    private final AppUserDAO userDao;

    public UserService(AppUserDAO userDao) {
        this.userDao = userDao;
    }

    public User getUserByUsername(String username) {
        try {

            String un = username;

            if (un.length() <= 4 ) {
                String msg = "Invalid username provided, username must be at least 5 characters";
                System.out.println("[ERROR] - " + msg);
                throw new InvalidUsernameException(msg);
            }

            User foundUser = userDao.getByUsername(un);

            if (foundUser == null) {
                String msg = "No user found with the provided username!";
                System.out.println("[ERROR] - " + msg);
                throw new ResourceNotFoundException(msg);
            }

            return foundUser;

        } catch (BadRequestException e) {
            String msg = "An error occurred. Please try again.";
            System.out.println("[ERROR] - " + msg);
            throw new BadRequestException(msg);
        }
    }

    public User getUserById (String id){

        try {

            int idNum = Integer.parseInt(id);

            if (idNum <= 0 ) {
                String msg = "Invalid id provided, was not a positive integer";
                System.out.println("[ERROR] - " + msg);
                throw new BadRequestException(msg);
            }

            User foundUser = userDao.getById(idNum);

            if (foundUser == null) {
                String msg = "No user found with the provided id!";
                System.out.println("[ERROR] - " + msg);
                throw new ResourceNotFoundException(msg);
            }

            return foundUser;

        } catch (NumberFormatException e) {
            String msg = "Invalid id provided, was not an integer";
            System.out.println("[ERROR] - " + msg);
            throw new BadRequestException(msg);
        }
    }

    public List<User> getAllUsers () {
        return userDao.getAllUsers();
    }

    public void insert(User userToBeRegistered) {

    }


}
