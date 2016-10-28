package com.clouway.jdbtqueries;

import org.junit.Test;

import java.sql.SQLException;
import java.sql.Savepoint;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by clouway on 26.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class DatabaseAgentTest {
   private JDBCConnector jdbcConnector = new JDBCConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/jdbc_database","root","clouway.com");

    @Test
    public void getFullTable() throws NoConnectionException, SQLException {
        DatabaseAgent databaseAgent = new DatabaseAgent(jdbcConnector.getConnection());
        String actual = databaseAgent.getTableToString("users");

    }

    @Test
    public void getRow() throws NoConnectionException, SQLException {
        String expected = "\nID: 1\nID: 2\nID: 4\nID: 5\nID: 6\nID: 7\nID: 8\nID: 9\nID: 10\nID: 11\nID: 12\nID: 13\nID: 14\nID: 15\nID: 16\nID: 17\nID: 18";
        DatabaseAgent databaseAgent = new DatabaseAgent(jdbcConnector.getConnection());
       String actual =  databaseAgent.getColumn("ID","users");
        assertThat(actual,is(expected));
    }


}