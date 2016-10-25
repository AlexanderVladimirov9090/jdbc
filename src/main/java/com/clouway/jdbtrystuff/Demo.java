package com.clouway.jdbtrystuff;

import java.sql.SQLException;

/**
 * Created by clouway on 25.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class Demo {
    public static void main(String[] args) {
        try {

            JDBCConnector jdbcConnector = new JDBCConnector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jdbc_database", "root", "clouway.com");
            TableAgent tableAgent = new TableAgent(jdbcConnector.getConnection());

            System.out.println("\n" + tableAgent.getTableToString("users"));
            System.out.println(tableAgent.getColumn("ID"));
            System.out.println(tableAgent.getColumn("UserName"));
            System.out.println(tableAgent.getColumn("Password"));
            System.out.println(tableAgent.getColumn("Email"));
            tableAgent.updateColumn("users", "UserName", "3","ID","'Gonzo'");
            System.out.println("\n" + tableAgent.getTableToString("users"));


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
