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
public class JDBCConnectorTest {

    @Test
    public void getConnector() throws NoConnectionException {
        JDBCConnector jdbcConnector = new JDBCConnector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jdbc_database", "root", "clouway.com");
        assertThat(jdbcConnector.getConnection(), is(instanceOf(Connection.class)));
    }

    @Test(expected = NoConnectionException.class)
    public void noDriver() throws NoConnectionException {
        JDBCConnector jdbcConnector = new JDBCConnector("", "jdbc:mysql://localhost/jdbc_database", "root", "clouway.com");
        jdbcConnector.getConnection();
    }

    @Test(expected = NoConnectionException.class)
    public void noURL() throws NoConnectionException {
        JDBCConnector jdbcConnector = new JDBCConnector("com.mysql.jdbc.Driver", "", "root", "clouway.com");
        jdbcConnector.getConnection();
    }

    @Test(expected = NoConnectionException.class)
    public void noUser() throws NoConnectionException {
        JDBCConnector jdbcConnector = new JDBCConnector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jdbc_database", "", "clouway.com");
        jdbcConnector.getConnection();
    }
    @Test(expected = NoConnectionException.class)
    public void noPassword() throws NoConnectionException {
        JDBCConnector jdbcConnector = new JDBCConnector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jdbc_database", "root", "");
        jdbcConnector.getConnection();
    }
}