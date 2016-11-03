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
public class UserDataProvider implements DataAccess {
    private final Connection dbConnection;
    private final List records;

    public UserDataProvider(Connection dbConnection, List records) {
        this.dbConnection = dbConnection;
        this.records = records;
    }

    public <T> List<T> getAllRecord(String table, Class typeOfRecord) throws NoConnectionException {
        try (PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM " + table)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
               records.add(fillUserFromDB(resultSet));
            }
            return records;
        } catch (SQLException e) {
            throw new NoRecordFoundException("No record found in Database");
        } finally {
            close(dbConnection);

        }
    }

    public <T> User getRecord(String table, T iD) throws NoRecordFoundException, NoConnectionException {
        int id = (Integer) iD;
        try (PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM " + table + " WHERE ID = " + id);) {
            ResultSet resultSet = statement.executeQuery();
           User user = null;
            while (resultSet.next()) {
             user=fillUserFromDB(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new NoRecordFoundException("No record found in Database");
        } finally {
            close(dbConnection);
        }
    }

    private User fillUserFromDB(ResultSet resultSet) throws SQLException {
       return new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));

    }

    private void close(Connection dbConnection) {
        try {
            dbConnection.close();
        } catch (SQLException e1) {
            throw new NoConnectionException("Something went wrong.");
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