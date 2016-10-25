package com.clouway.jdbtrystuff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by clouway on 25.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class TableAgent {
    private final Connection dbConnection;

    public TableAgent(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public String getTableToString(String table) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM " + table);
        ResultSet resultSet = statement.executeQuery();
        StringBuilder stringBuilder = new StringBuilder();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String userName = resultSet.getString(2);
            String password = resultSet.getString(3);
            String email = resultSet.getString(4);
            stringBuilder.append("\nID: " + id + "\nUserName:" + userName + "\nPassword: " + password + "\nEmail: " + email + "\n");
        }
        return stringBuilder.toString();
    }

    public String getColumn(String column) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("SELECT " + column + " FROM users");
        ResultSet resultSet = statement.executeQuery();
        StringBuilder stringBuilder = new StringBuilder();
        while (resultSet.next()) {
            String result = resultSet.getString(1);
            stringBuilder.append("\n" + column + ": " + result);
        }
        return stringBuilder.toString();
    }

    public void updateColumn(String table, String column, String content, String columnForWhere, String newContent) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("UPDATE " + table + " SET " + column + " =" + newContent + " WHERE " + columnForWhere + " = " + content);
        statement.execute();
    }

}
