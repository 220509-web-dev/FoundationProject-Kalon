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

        /*appUserDAO appUserDAO = new appUserDAOPostgres();
        appUsers b = appUserDAO.getAppUserById(1);
        System.out.println(b);*/

        appUserDAO appuserdao = new appUserDAOPostgres();
        System.out.println(appuserdao.getAllAppUsers());
    }
}
