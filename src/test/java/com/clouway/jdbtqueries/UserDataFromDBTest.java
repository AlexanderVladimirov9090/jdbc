package com.clouway.jdbtqueries;

import org.junit.Test;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by clouway on 02.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class UserDataFromDBTest {
    private Connector connector = new Connector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jdbc_database", "root", "clouway.com");

    @Test
    public void getFirstUser() throws NoConnectionException {
        User expected = new User(1, "Root", "password", "emai@email.com");
        DataStore userDataStore = new DataStore(new LinkedList(), connector.getConnection());
        List actual = userDataStore.fetchRows("SELECT * FROM  users", resultSet -> {
            try {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        User actualUser = (User) actual.get(0);
        assertTrue(actualUser.equal(expected));
    }

    @Test
    public void getSecondUser() throws NoConnectionException {
        User expected = new User(2, "user", "password", "emai@email.com");
        DataStore userDataStore = new DataStore(new LinkedList(), connector.getConnection());
        List actual = userDataStore.fetchRows("SELECT * FROM  users", resultSet -> {
            try {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        User actualUser = (User) actual.get(1);
        assertTrue(actualUser.equal(expected));
    }

    @Test
    public void getUserById() {
        User expected = new User(1, "Root", "password", "emai@email.com");
        DataStore userDataStore = new DataStore(new LinkedList(), connector.getConnection());
        List actual = userDataStore.fetchRows("SELECT * FROM users WHERE ID  = 1", resultSet -> {
            try {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        User actualUser = (User) actual.get(0);
        assertTrue(actualUser.equal(expected));
    }

    @Test(expected = IllegalStateException.class)
    public void getNonExistingUser() {
        DataStore userDataStore = new DataStore(new LinkedList(), connector.getConnection());
        List actual = userDataStore.fetchRows("SELECT * FROM  users WHERE ID = 5", resultSet -> {
            try {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        User actualUser = (User) actual.get(0);
    }
}