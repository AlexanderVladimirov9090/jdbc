package com.clouway.jdbtqueries;

import java.util.List;

/**
 * Created by clouway on 02.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public interface DataAccessObject {

    <T> List<T> fetchRecords(String query) throws NoRecordFoundException, NoConnectionException;

    void update(String query);
}
