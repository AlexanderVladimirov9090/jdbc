package com.clouway.jdbcqueries;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by clouway on 04.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public interface RowFetcher<T> {
    T fetchRow(ResultSet resultSet) throws SQLException;
}
