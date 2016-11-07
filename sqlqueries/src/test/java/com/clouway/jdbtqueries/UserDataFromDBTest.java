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
<<<<<<< HEAD:src/test/java/com/clouway/jdbtqueries/UserDataFromDBTest.java
    private Connector connector = new Connector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jdbc_database", "root", "clouway.com");
=======
    private Connector jdbcConnector = new Connector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jdbc_database", "root", "123123");
>>>>>>> 8e00e3887f385bbf91711b7cbddc39d7d0371f88:sqlqueries/src/test/java/com/clouway/jdbtqueries/UserDataFromDBTest.java

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

<<<<<<< HEAD:src/test/java/com/clouway/jdbtqueries/UserDataFromDBTest.java
    @Test(expected = IllegalStateException.class)
    public void getNonExistingUser() {
        DataStore userDataStore = new DataStore(new LinkedList(), connector.getConnection());
=======
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

    @Test(expected = IndexOutOfBoundsException.class)
    public void getNonExistingUser(){
        DataStore userDataStore = new DataStore(new LinkedList(), jdbcConnector.getConnection());
>>>>>>> 8e00e3887f385bbf91711b7cbddc39d7d0371f88:sqlqueries/src/test/java/com/clouway/jdbtqueries/UserDataFromDBTest.java
        List actual = userDataStore.fetchRows("SELECT * FROM  users WHERE ID = 5", resultSet -> {
            try {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        User actualUser = (User) actual.get(0);
<<<<<<< HEAD:src/test/java/com/clouway/jdbtqueries/UserDataFromDBTest.java
=======
        assertThat(actualUser.getEmail(), is(equalTo("emai@email.com")));
>>>>>>> 8e00e3887f385bbf91711b7cbddc39d7d0371f88:sqlqueries/src/test/java/com/clouway/jdbtqueries/UserDataFromDBTest.java
    }
}