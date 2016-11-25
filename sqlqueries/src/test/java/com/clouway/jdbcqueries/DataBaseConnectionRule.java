package com.clouway.jdbcqueries;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by zumba on 10.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class DataBaseConnectionRule implements TestRule {
    public   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jdbc_database", "root", "123123");;

    public DataBaseConnectionRule() throws SQLException {
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Class.forName("com.mysql.jdbc.Driver");
                base.evaluate();
                closeConnection();
            }
        };
    }

    private void closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}