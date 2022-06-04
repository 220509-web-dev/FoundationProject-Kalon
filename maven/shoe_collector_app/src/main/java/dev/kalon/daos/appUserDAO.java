package dev.kalon.daos;

import dev.kalon.entities.User;

import java.util.List;

public interface appUserDAO {

    //create
    int create(User userToBeRegistered);

    //Read
    User getById(int id);
    List<User> getAllUsers();

    User getByUsername(String username);

    //Update
    User updateUser(User user);

    //Delete
    boolean deleteUserById(int id);
}
