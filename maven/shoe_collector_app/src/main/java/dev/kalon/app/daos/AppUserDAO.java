package dev.kalon.app.daos;

import dev.kalon.app.entities.User;

import java.util.List;

public interface AppUserDAO {

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
