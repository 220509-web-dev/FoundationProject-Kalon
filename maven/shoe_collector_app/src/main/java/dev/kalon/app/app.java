package dev.kalon.app;

import com.github.lalyos.jfiglet.FigletFont;
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

        Connection conn = ConnectionUtil.getConnection();

        String sql = "select * from app_users";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();

        System.out.println(rs);
    }
}
