package com.clouway.jdbtqueries;

import org.junit.Test;

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
public class UserDataProviderTest {
    private JDBCConnector jdbcConnector = new JDBCConnector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jdbc_database", "root", "clouway.com");

    @Test
    public void getFirstId() throws NoConnectionException {

        UserDataProvider userDataProvider = new UserDataProvider(jdbcConnector.getConnection(), new LinkedList());
        List actual = userDataProvider.fetchRecords("SELECT * FROM  users");
        User actualUser = (User) actual.get(0);
        assertThat(actualUser.getId(), is(equalTo(1)));
    }

    @Test
    public void getFirstUserName() throws NoConnectionException {
        String expected = "Root";
        UserDataProvider userDataProvider = new UserDataProvider(jdbcConnector.getConnection(), new LinkedList());
        List actual = userDataProvider.fetchRecords("SELECT * FROM  users");
        User actualUser = (User) actual.get(0);
        assertThat(actualUser.getUserName(), is(equalTo(expected)));
    }

    @Test
    public void getFirstUserPassword() {
        String expected = "password";
        UserDataProvider userDataProvider = new UserDataProvider(jdbcConnector.getConnection(), new LinkedList());
        List actual = userDataProvider.fetchRecords("SELECT * FROM  users");
        User actualUser = (User) actual.get(0);
        assertThat(actualUser.getPassword(), is(equalTo(expected)));
    }

    @Test
    public void getFirstUserEmail() {
        String expected = "emai@email.com";
        UserDataProvider userDataProvider = new UserDataProvider(jdbcConnector.getConnection(), new LinkedList());
        List actual = userDataProvider.fetchRecords("SELECT * FROM  users");
        User actualUser = (User) actual.get(0);
        assertThat(actualUser.getEmail(), is(equalTo(expected)));
    }


    @Test
    public void getSecondUser() throws NoConnectionException {

        UserDataProvider userDataProvider = new UserDataProvider(jdbcConnector.getConnection(), new LinkedList());
        List actual = userDataProvider.fetchRecords("SELECT * FROM  users");
        User actualUser = (User) actual.get(1);
        assertThat(actualUser.getId(), is(equalTo(2)));
    }

    @Test(expected = NoRecordFoundException.class)
    public void getNoneExistingUser() throws NoConnectionException {
        UserDataProvider userDataProvider = new UserDataProvider(jdbcConnector.getConnection(), new LinkedList());
        userDataProvider.fetchRecords("SELECT * FROM  users WHERE USER_NAME = nouser");
    }
  /*
    @Test
    public void getUserPassword(){
        String expected = "password";
        UserDataProvider userDataProvider = new UserDataProvider(jdbcConnector.getConnection(), new LinkedList());
        User actual = userDataProvider.getRecord("users", 1);

        assertThat(actual.getPassword(), is(equalTo(expected)));

    }

    @Test
    public void getEmail(){
        String expected = "emai@email.com";
        UserDataProvider userDataProvider = new UserDataProvider(jdbcConnector.getConnection(), new LinkedList());
        User actual = userDataProvider.getRecord("users", 1);

        assertThat(actual.getEmail(), is(equalTo(expected)));

    }

*/
}