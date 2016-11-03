package com.clouway.jdbtqueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by clouway on 25.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class UserDataProvider implements DataAccessObject {
    private final Connection dbConnection;
    private final List records;

    public UserDataProvider(Connection dbConnection, List records) {
        this.dbConnection = dbConnection;
        this.records = records;
    }

    public <T> List<T> getAllRecord(String table) throws NoConnectionException {
        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareStatement("SELECT * FROM " + table);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                records.add(user);
            }
            dbConnection.close();
            return records;
        } catch (SQLException e) {
            throw new NoRecordFoundException("No record found in Database");
        }
    }

    public <T> User getRecord(String table, T iD) throws NoRecordFoundException, NoConnectionException {
        PreparedStatement statement = null;
        try {
            int id = (Integer) iD;
            statement = dbConnection.prepareStatement("SELECT * FROM " + table + " WHERE ID = " + id);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));

            }
            dbConnection.close();
            return user;
        } catch (SQLException e) {
            throw new NoRecordFoundException("No record found in Database");
        }
    }

    public <T> void updateRecord(String table, T record, T updatedRecord) {

    }

    public <T> void deleteRecord(String table, T record) {

    }

    public <T> void createTable(String table, T record) {

    }

    public void deleteTable(String table) {

    }
   /*


    public String getTableToString(String table) throws SQLException, NoConnectionException {
        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareStatement("SELECT * FROM " + table);
            ResultSet resultSet = statement.executeQuery();

            StringBuilder stringBuilder = new StringBuilder();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String userName = resultSet.getString(2);
                String password = resultSet.getString(3);
                String email = resultSet.getString(4);
                stringBuilder.append("\nID: ").append(id).append("\nUserName:").append(userName).append("\nPassword: ").append(password).append("\nEmail: ").append(email).append("\n");
            }

            return stringBuilder.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }
        throw new NoConnectionException("Did now get any content from Database.");
    }

    public String getRecord(String column, String table) throws NoConnectionException{
        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareStatement("SELECT " + column + " FROM " + table);
            ResultSet resultSet = statement.executeQuery();
            StringBuilder stringBuilder = new StringBuilder();
            while (resultSet.next()) {
                String result = resultSet.getString(1);
                stringBuilder.append("\n").append(column).append(": ").append(result);
            }
            return stringBuilder.toString();
        } catch (SQLException ignore) {
           throw new NoConnectionException("No");
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                throw new NoConnectionException("");
            }
        }
    }

    public void updateColumn(String table, String column, String content, String columnForWhere, String newContent) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareStatement("UPDATE " + table + " SET " + column + " =" + newContent + " WHERE " + columnForWhere + " = " + content);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }
    }

    public void deleteContent(String table, String column, String content) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareStatement("DELETE FROM " + table + " WHERE " + column + " = " + content);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }
    }

    public void insetContent(String table, String columns, String values) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareStatement("INSERT INTO " + table + " ( " + columns + " ) " + "VALUES " + " ( " + values + " ) ");
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }
    }

    public <T> T getAllRecord(String query) {
        return null;
    }

    public <T> T getRecord(String query, T data) {
        return null;
    }

    public <T> void addRecordTo(String query, T data) {

    }

    public <T> void updateTableColumn(String query, T data) {

    }

    public <T> void deleteRecord(String query, T data) {

    }

    /*public void dropTable(String table){
        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareStatement("DROP TABLE " + table);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }
    }

    public void addColumn(String table, String column, String dataType) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareStatement("ALTER TABLE " + table + " ADD " + column + " " + dataType);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }
    }

    public String getColumnByPattern(String table, String columns, String columnName, String pattern) throws SQLException, NoConnectionException {
        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareStatement("SELECT " + columns + " FROM " + table + " WHERE " + columnName + " LIKE " + pattern);
            StringBuilder stringBuilder = new StringBuilder();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String result = resultSet.getString(1);
                stringBuilder.append("\n").append(columnName).append(": ").append(result);
            }
            return stringBuilder.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }
        throw new NoConnectionException("Did not get any content from DataBase.");
    }*/
}