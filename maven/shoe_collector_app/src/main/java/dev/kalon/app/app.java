package dev.kalon.app;

import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;


public class app {
    public static void main(String[] args) {

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

        //Get user by id
        /*appUserDAO appuserDAO = new appUserDAOPostgres();
        User b = appuserDAO.getById(1);
        System.out.println(b);*/

       //Get all users
        /*appUserDAO appuserdao = new appUserDAOPostgres();
        System.out.println(appuserdao.getAllUsers());*/

        //Create a new user
        /*appUserDAO appuserdao = new appUserDAOPostgres();
        User newUser = new User("Test", "TEST", new Date(1989, 0, 1), "kdkd@yahoo.com", "kdp132443", 1, "passworddddd");
        System.out.println(appuserdao.create(newUser));*/

        //Delete a user by id       *******WORKS but prints out true even if nothing is to be deleted*****
        /*appUserDAO appuserdao = new appUserDAOPostgres();
        System.out.println(appuserdao.deleteUserById(4));*/

        //Update a user by id
        /*appUserDAO appuserdao = new appUserDAOPostgres();
        User newUser = new User("Kalon", "Penagraph", new Date (97, 9, 1), "kdp131@txstate.edu", "kdp131", 1, "password", 1);
        System.out.println(appuserdao.updateUser(newUser));*/
    }
}
