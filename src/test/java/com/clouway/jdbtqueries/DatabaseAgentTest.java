package com.clouway.jdbtqueries;

import org.junit.Test;

import java.sql.SQLException;
import java.sql.Savepoint;

import static org.junit.Assert.*;

/**
 * Created by clouway on 26.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class DatabaseAgentTest {
    private String expected = "";
    @Test
    public void getFullTable() throws NoConnectionException, SQLException {
        JDBCConnector jdbcConnector = new JDBCConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/jdbc_database","root","clouway.com");
        DatabaseAgent databaseAgent = new DatabaseAgent(jdbcConnector.getConnection());
        String actual = databaseAgent.getTableToString("users");
        System.out.println(actual);
    }

}