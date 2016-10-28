package com.clouway.jdbtqueries;

import java.sql.SQLException;

/**
 * Created by clouway on 26.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class Demo {
    public static void main(String[] args) {
        JDBCConnector jdbcConnector = new JDBCConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/jdbc_database","root","clouway.com");
        try {
            DatabaseAgent databaseAgent = new DatabaseAgent(jdbcConnector.getConnection());
            System.out.println(databaseAgent.getTableToString("users"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoConnectionException e) {
            e.printStackTrace();
        }
    }
}
