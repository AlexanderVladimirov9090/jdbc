package com.clouway.jdbtqueries;

import com.mysql.jdbc.JDBC4Connection;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by clouway on 26.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class JDBCConnectorTest {

@Test
    public void happyPath() throws NoConnectionException {
    JDBCConnector jdbcConnector = new JDBCConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/jdbc_database","root","clouway.com");
    Connection connection = jdbcConnector.getConnection();
    assertThat(connection,is(instanceOf(JDBC4Connection.class)));
}

@Test(expected = NoConnectionException.class)
public void noDriver() throws SQLException, ClassNotFoundException, NoConnectionException {
    JDBCConnector jdbcConnector = new JDBCConnector("","jdbc:mysql://localhost/jdbc_database","root","clouway.com");
    Connection connection = jdbcConnector.getConnection();
}

    @Test(expected = NoConnectionException.class)
    public void noURLOdDatabase() throws SQLException, ClassNotFoundException, NoConnectionException {
        JDBCConnector jdbcConnector = new JDBCConnector("com.mysql.jdbc.Driver","","root","clouway.com");
        Connection connection = jdbcConnector.getConnection();
    }

    @Test(expected = NoConnectionException.class)
    public void noUser() throws SQLException, ClassNotFoundException, NoConnectionException {
        JDBCConnector jdbcConnector = new JDBCConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/jdbc_database","","clouway.com");
        Connection connection = jdbcConnector.getConnection();
    }

    @Test(expected = NoConnectionException.class)
    public void noPassword() throws NoConnectionException {
        JDBCConnector jdbcConnector = new JDBCConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/jdbc_database","root","");
        Connection connection = jdbcConnector.getConnection();

    }
}