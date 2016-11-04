package com.clouway.jdbtqueries;

import org.junit.Test;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 02.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class UserDataFromDBTest {
    private JDBCConnector jdbcConnector = new JDBCConnector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jdbc_database", "root", "clouway.com");

    @Test
    public void getFirstUserId() throws NoConnectionException {
        DataStore userDataStore = new DataStore(new LinkedList(), jdbcConnector.getConnection());
        List actual = userDataStore.fetchRows("SELECT * FROM  users", resultSet -> {
            try {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        User actualUser = (User) actual.get(0);
        assertThat(actualUser.getId(), is(equalTo(1)));
    }

    @Test
    public void getFirstUserName() throws NoConnectionException {
        DataStore userDataStore = new DataStore(new LinkedList(), jdbcConnector.getConnection());
        List actual = userDataStore.fetchRows("SELECT * FROM  users", resultSet -> {
            try {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        User actualUser = (User) actual.get(0);
        assertThat(actualUser.getUserName(), is(equalTo("Root")));
    }

    @Test
    public void getFirstUserPassword() throws NoConnectionException {
        DataStore userDataStore = new DataStore(new LinkedList(), jdbcConnector.getConnection());
        List actual = userDataStore.fetchRows("SELECT * FROM  users", resultSet -> {
            try {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        User actualUser = (User) actual.get(0);
        assertThat(actualUser.getPassword(), is(equalTo("password")));
    }

    @Test
    public void getFirstUserEmail() throws NoConnectionException {
        DataStore userDataStore = new DataStore(new LinkedList(), jdbcConnector.getConnection());
        List actual = userDataStore.fetchRows("SELECT * FROM  users", resultSet -> {
            try {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        User actualUser = (User) actual.get(0);
        assertThat(actualUser.getEmail(), is(equalTo("emai@email.com")));
    }

    @Test(expected = NoRecordFoundException.class)
    public void getNonExistingUser(){
        DataStore userDataStore = new DataStore(new LinkedList(), jdbcConnector.getConnection());
        List actual = userDataStore.fetchRows("SELECT * FROM  users WHERE ID = 5", resultSet -> {
            try {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        User actualUser = (User) actual.get(0);
        assertThat(actualUser.getEmail(), is(equalTo("emai@email.com")));

    }
}