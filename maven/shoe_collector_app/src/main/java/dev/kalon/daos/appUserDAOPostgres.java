package dev.kalon.daos;

import dev.kalon.entities.appUsers;
import dev.kalon.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class appUserDAOPostgres implements appUserDAO{
    @Override
    public appUsers createAppUser(appUsers user) {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "insert into shoe_collection_app.app_users values (default,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setDate(3, user.getBirthDate());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getUsername());
            ps.setInt(6, user.getStatusId());
            ps.setString(7, user.getPassword());

            ps.execute();

            //getting generated primary key value
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("id");

            user.setUserId(generatedId); //the user is changing from 0 to a non-zero value means it is saved
            return user;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
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
            user.setUserId(rs.getInt("id"));
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

            List<appUsers> users = new ArrayList<>();

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

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "update shoe_collection_app.app_users set first_name = ?, last_name = ?, birth_date = ?, email = ?, username = ?, status_id = ?, pword = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setDate(3, user.getBirthDate());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getUsername());
            ps.setInt(6, user.getStatusId());
            ps.setString(7, user.getPassword());
            ps.setInt(8, user.getUserId());

            ps.execute();

            return user;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteAppUserById(int id) {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "delete from shoe_collection_app.app_users where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return true;
    }
}
