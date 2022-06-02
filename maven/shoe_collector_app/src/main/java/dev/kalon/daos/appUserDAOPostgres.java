package dev.kalon.daos;

import dev.kalon.entities.appUsers;
import dev.kalon.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class appUserDAOPostgres implements appUserDAO{
    @Override
    public appUsers createAppUser(appUsers user) {
        return null;
    }

    @Override
    public appUsers getAppUserById(int id) {
        //try with resources. Automatically closes the connection once the try block finishes
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select all from shoe_collection_app.app_users where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); //JDBC actually interacts with the DB

            //Get first record
            rs.next();

            //Create a user and set the values of that user to the information in the result set
            appUsers user = new appUsers();
            user.setUserId(id);
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setBirthDate(rs.getDate("birth_date"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setStatusId(String.valueOf(rs.getInt("status_id")));
            user.setPassword(rs.getString("pword"));
            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<appUsers> getAllAppUsers() {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select * from shoe_collection_app.app_users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<appUsers> users = new ArrayList<appUsers>();

            while (rs.next()){
                appUsers user = new appUsers();
                user.setUserId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setBirthDate(rs.getDate("birth_date"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setStatusId(String.valueOf(rs.getInt("status_id")));
                user.setPassword(rs.getString("pword"));
                users.add(user);
            }
            return users;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public appUsers updateAppUser(appUsers user) {
        return null;
    }

    @Override
    public void deleteAppUserById(int id) {

    }
}
