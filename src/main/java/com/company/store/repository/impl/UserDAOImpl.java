package com.company.store.repository.impl;


import com.company.store.repository.UserDAO;
import com.company.store.entities.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

public class UserDAOImpl implements UserDAO {

    private static final Logger log = LogManager.getLogger(UserDAOImpl.class);

    private static final String GET_ALL_USERS = "SELECT * FROM USERS";
    private static final String GET_USER_BY_ID = "SELECT * FROM USERS WHERE USER_ID = ?";
    private static final String GET_USER_BY_CREDENTIALS = "SELECT * FROM USERS WHERE USER_EMAIL = ? AND USER_PASSWORD = ?";

    /**
     * Parameters in this order: USER_NAME, USER_SURNAME, USER_EMAIL, USER_PHONE, USER_PASSWORD, USER_ADDRESS, ISADMIN
     */
    private static final String INSERT_USER = "INSERT INTO USERS VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_CREDENTIALS = "UPDATE users SET user_name=?, user_surname=?, user_email=?, " +
            "user_phone=?, user_password=?, user_address=?, isadmin=? WHERE user_id=?";

    private static final String DELETE_USER = "DELETE FROM USERS WHERE USER_ID = ?";

    /**
     * Instance of global datasource to get connection from pool.
     */
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * parse cortege with user credentials to object User.
     */
    private User parseUser(ResultSet resultSet) {
        User user = null;
        int adminFlag = 1;
        try {
             user = User.newBuilder()
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2))
                    .setSurname(resultSet.getString(3))
                    .setEmail(resultSet.getString(4))
                    .setPhone(resultSet.getString(5))
                    .setPassword(resultSet.getString(6))
                    .setAddress(resultSet.getString(7))
                    .build();
            if (adminFlag == resultSet.getInt(8))
                user.setAdmin(true);
            else user.setAdmin(false);
        } catch (SQLException e) {
            log.error("Parsing of user was failed! ", e);
        } return user;
    }

    /**
     * save user credentials to database
     */
    @Override
    public boolean saveUser(User user) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(user.getId() != 0 ? UPDATE_USER_CREDENTIALS : INSERT_USER)){
             ps.setString(1, user.getName());
             ps.setString(2, user.getSurname());
             ps.setString(3, user.getEmail());
             ps.setString(4, user.getPhone());
             ps.setString(5, user.getPassword());
             ps.setString(6, user.getAddress());
             if (!user.isAdmin())
                 ps.setInt(7, 0);
             else
                 ps.setInt(7, 1);
             if (user.getId() != 0)
                 ps.setInt(8, user.getId());
             ps.executeUpdate();
             log.debug("User was successfully inserted into db!, Info: " + user.toString());
        } catch (SQLException e) {
            log.error("Inserting user into db was failed! User: " + user.toString(), e);
            return false;
        }
        return true;
    }

    /**
     * return all users from database
     */
    @Override
    public Collection<User> getAllUsers() {
        Collection<User> users = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                users.add(parseUser(resultSet));
            } log.debug("Successfully received all users from database!");
        } catch (SQLException e) {
            log.error("Receiving users from database was failed: ", e);
        }
        return users;
    }

    /**
     * return user from database by specify id
     */
    @Override
    public User getById(int user_id) {
        User user = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID)) {
            ps.setInt(1, user_id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                user = parseUser(resultSet);
                log.debug("User was successfully received from database by id: " + user_id);
            }
        } catch (SQLException e) {
            log.error("Receiving user was failed, user_id: " + user_id, e);
        } return user;
    }

    /**
     * return user from database by specify credentials
     */
    @Override
    public User getByCredentials(String email, String password) {
        User user = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_USER_BY_CREDENTIALS)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                user = parseUser(resultSet);
                log.debug("Received a user by credentials, user: " + user.toString());
            }
        } catch (SQLException e) {
            log.error("Receiving user by credentials was failed, email: " + email, e);
        } return user;
    }

    /**
     * remove user from database by id
     */
    @Override
    public boolean removeUser(int user_id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_USER)) {
            ps.setInt(1, user_id);
            ps.executeUpdate();
            log.debug("user was deleted by id: " + user_id);
        } catch (SQLException e) {
            log.error("deleting user was failed, id: " + user_id, e);
            return false;
        }
        return true;
    }
}
