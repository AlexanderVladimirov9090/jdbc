package com.clouway.jdbtqueries;

import org.junit.Test;

import java.sql.Connection;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 02.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class ConnectorTest {

    @Test
    public void getConnector() throws NoConnectionException {
        Connector connector = new Connector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jdbc_database", "root", "123123");
        assertThat(connector.getConnection(), is(instanceOf(Connection.class)));
    }

    @Test(expected = NoConnectionException.class)
    public void noDriver() throws NoConnectionException {
        Connector connector = new Connector("", "jdbc:mysql://localhost/jdbc_database", "root", "clouway.com");
        connector.getConnection();
    }

    @Test(expected = NoConnectionException.class)
    public void noURL() throws NoConnectionException {
        Connector connector = new Connector("com.mysql.jdbc.Driver", "", "root", "clouway.com");
        connector.getConnection();
    }

    @Test(expected = NoConnectionException.class)
    public void noUser() throws NoConnectionException {
        Connector connector = new Connector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jdbc_database", "", "clouway.com");
        connector.getConnection();
    }
    @Test(expected = NoConnectionException.class)
    public void noPassword() throws NoConnectionException {
        Connector connector = new Connector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jdbc_database", "root", "");
        connector.getConnection();
    }
}