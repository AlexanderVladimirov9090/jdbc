package com.clouway.jdbcqueries;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 07.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class ChangesInDatabaseTest {
    @Rule
    public DataBaseConnectionRule databaseConnectionRule = new DataBaseConnectionRule();
    Connection connection = databaseConnectionRule.connection;
    private DataStore dataStore = new DataStore(connection);

    @Before
    public void clearDataBase() {
        dataStore.update("DROP TABLE IF EXISTS SomeTable;");
    }

    public ChangesInDatabaseTest() throws SQLException {
    }

    @Test
    public void updateStatement() {
        String expected = "Good to go";
        dataStore.update("CREATE TABLE SomeTable (SomeText TEXT, Age INT , Status VARCHAR(10));");
        dataStore.update("INSERT INTO SomeTable VALUES ('Bark', 18, 'something');");
        dataStore.update("INSERT INTO SomeTable VALUES ('Poe', 18, 'divorced');");
        dataStore.update("INSERT INTO SomeTable VALUES ('Poe', 99, 'something');");
        dataStore.update("UPDATE SomeTable SET SomeText = 'Good to go' WHERE Age < 37;");
        List actual = dataStore.fetchRows("SELECT SomeText FROM SomeTable WHERE SomeText = 'Good to go';", resultSet -> {
            return resultSet.getString(1);
        });

        assertThat(actual.get(0), is(equalTo(expected)));
        assertThat(actual.get(1), is(equalTo(expected)));
    }

    @Test
    public void alterTable() {
        dataStore.update("CREATE TABLE SomeTable (Number INT);");
        dataStore.update("ALTER TABLE SomeTable ADD Text Text;");
        dataStore.update("INSERT INTO SomeTable VALUES (1,'Some Text');");
        List actual = dataStore.fetchRows("SELECT Text FROM SomeTable WHERE Text = 'Some Text';", resultSet -> {return resultSet.getString(1);});

        String actualString = (String) actual.get(0);
        assertThat(actualString, is(equalTo("Some Text")));
    }

    @Test(expected = IllegalStateException.class)
    public void dropTable() {
        dataStore.update("CREATE TABLE SomeTable (SomeRow INT);");
        dataStore.update("INSERT INTO SomeTable VALUES (9);");
        dataStore.update("DROP TABLE SomeTable;");
        List actual = dataStore.fetchRows("SELECT SomeRow FROM someTableName WHERE SomeRow = 9", resultSet -> {return new Integer(resultSet.getInt(1));});
    }
}