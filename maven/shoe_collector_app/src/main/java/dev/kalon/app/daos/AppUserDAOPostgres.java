package dev.kalon.app.daos;

import dev.kalon.app.entities.User;
import dev.kalon.app.utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppUserDAOPostgres implements AppUserDAO {
    @Override
    public int create(User userToBeRegistered) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO app_users (first_name, last_name, birth_date, email, username, status_id, password) " +
                    "VALUES (?,?,?,?,?,?,?) " +
                    "RETURNING app_users.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userToBeRegistered.getFirstName());
            ps.setString(2, userToBeRegistered.getLastName());
            ps.setDate(3, userToBeRegistered.getBirthDate());
            ps.setString(4, userToBeRegistered.getEmail());
            ps.setString(5, userToBeRegistered.getUsername());
            ps.setInt(6, userToBeRegistered.getStatusId());
            ps.setString(7, userToBeRegistered.getPassword());

            ResultSet resultSet;

            if ((resultSet = ps.executeQuery()) != null) {
                System.out.println("Welcome to the Team!");
                resultSet.next();
                return resultSet.getInt(1);
            }

        } catch (SQLException exception) {
            System.out.println("User creation failed");
            exception.printStackTrace();
        }
        return userToBeRegistered.getId();
    }

    @Override
    public User getById(int id) {
        //try with resources. Automatically closes the connection once the try block finishes
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM app_users WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); //JDBC actually interacts with the DB

            //Get first record
            if (rs.next()) {

                //Create a user and set the values of that user to the information in the result set
                return new User(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("birth_date"),
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getInt("status_id"),
                    rs.getString("password")
                );
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong with the database!");
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM app_users";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            List<User> userList = new ArrayList<>();

            while (rs.next()){
                userList.add( new User(
                    rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birth_date"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getInt("status_id"),
                        rs.getString("password")
                ));
            }
            return userList;

        } catch (SQLException exception) {
            System.out.println("Something went wrong obtaining all the users!");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public User getByUsername(String username) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM app_users WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birth_date"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getInt("status_id"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong with the database!");
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User updateUser(User user) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "UPDATE app_users SET first_name = ?, last_name = ?, birth_date = ?, email = ?, username = ?, status_id = ?, password = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setDate(3, user.getBirthDate());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getUsername());
            ps.setInt(6, user.getStatusId());
            ps.setString(7, user.getPassword());
            ps.setInt(8, user.getId());

            ps.execute();

            return user;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteUserById(int id) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "DELETE FROM app_users WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }
}
