package com.clouway.jdbtqueries;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by clouway on 02.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public interface DataAccess {
    <T> List<T> getAllRecord(String table, Class typeOfRecord) throws NoRecordFoundException, NoConnectionException;

    <T> User getRecord(String table, T record) throws NoRecordFoundException, NoConnectionException;

    <T> void updateRecord(String table, T record, T updatedRecord);

    <T> void deleteRecord(String table, T record);

    <T> void createTable(String table, T record);

    void deleteTable(String table);


}
