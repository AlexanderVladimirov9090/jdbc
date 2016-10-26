package com.clouway.jdbcqueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by clouway on 25.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 * This class setups connection for sql databases.
 */
public class Connector {
    private final String jDBC_Driver;
    private final String db_url;
    private final String user;
    private final String password;

    public Connector(String jDBCDriver, String databaseURL, String user, String password) {
        this.jDBC_Driver = jDBCDriver;
        this.db_url = databaseURL;
        this.user = user;
        this.password = password;
    }

    /**
     * Setups connection for sql databases.
     * @return Connection for sql databases.
     * @throws NoConnectionException when Connection is inconsistent.
     */
    public Connection getConnection() throws NoConnectionException {
        try {
            Class.forName(jDBC_Driver);
            isUserProvided();
            isPasswordProvided();
            Connection connection = DriverManager.getConnection(db_url, user, password);
            return connection;
        } catch (ClassNotFoundException e) {
            throw new NoConnectionException("You did not provide SQL driver");
        } catch (SQLException e) {
            throw new NoConnectionException("You did not provide URL for Database");
        }
    }

    /**
     * Checks if password is provided.
     * @throws NoConnectionException when password is not provided.
     */
    private void isPasswordProvided() throws NoConnectionException {
        if (password == null) {
            throw new NoConnectionException("Did not provide password.");
        }
    }

    /**
     * Checks if user is provided.
     * @throws NoConnectionException when user is not provided.
     */
    private void isUserProvided() throws NoConnectionException {
        if (user == null) {
            throw new NoConnectionException("Did not provide user.");
        }
    }
}