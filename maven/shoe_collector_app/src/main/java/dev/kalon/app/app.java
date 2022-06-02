package dev.kalon.app;

import com.github.lalyos.jfiglet.FigletFont;
import dev.kalon.daos.appUserDAO;
import dev.kalon.daos.appUserDAOPostgres;
import dev.kalon.entities.appUsers;
import dev.kalon.utils.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class app {
    public static void main(String[] args) throws SQLException {

        String greeting = "Welcome to Shoe Collector!";
        try {
            String asciArt = FigletFont.convertOneLine(greeting);
            System.out.println(asciArt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*Connection conn = ConnectionUtil.getConnection();

        String sql = "select * from shoe_collection_app.app_users au where id = 1";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        String firstName = rs.getString("first_name");
        System.out.println(firstName);*/

        //Get user by id    ******column name 'id' not found*****
        /*appUserDAO appuserDAO = new appUserDAOPostgres();
        appUsers b = appuserDAO.getAppUserById(1);
        System.out.println(b);*/

       //Get all users      *****WORKS*****
        /*appUserDAO appuserdao = new appUserDAOPostgres();
        System.out.println(appuserdao.getAllAppUsers());*/

        //Insert a new user     ******complains about number of arguments*****
        /*appUserDAO appuserdao = new appUserDAOPostgres();
        System.out.println(appuserdao.createAppUser
                ("Tester", "tester", 1997-10-01,"kdp131@txstate.edu", "kdp131", 1, "password"));*/

        //Delete a user by id       *******WORKS but prints out true even if nothing is to be deleted*****
        /*appUserDAO appuserdao = new appUserDAOPostgres();
        System.out.println(appuserdao.deleteAppUserById(4));*/

        //Update a user by id       *******not sure whats wrong, actual and formal argument list differ in length******
        /*appUserDAO appuserdao = new appUserDAOPostgres();
        System.out.println(appuserdao.updateAppUser("kalon", "penagraph", 1997-10-01, "kdp131@txstate.edu", "kdp131", 1, "password", 1));*/
    }
}
