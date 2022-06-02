package dev.kalon.daos;

import dev.kalon.entities.appUsers;

import java.util.List;

public interface appUserDAO {

    //create
    appUsers createAppUser(appUsers user);

    //Read
    appUsers getAppUserById(int id);
    List<appUsers> getAllAppUsers();

    //Update
    appUsers updateAppUser(appUsers user);

    //Delete
    boolean deleteAppUserById(int id);
}
