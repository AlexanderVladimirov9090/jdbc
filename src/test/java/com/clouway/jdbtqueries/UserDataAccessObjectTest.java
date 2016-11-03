package com.clouway.jdbtqueries;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by clouway on 02.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class UserDataAccessObjectTest {
    private JUnitRuleMockery context = new JUnitRuleMockery();
    private DataAccessObject dataAccessObject = context.mock(DataAccessObject.class);
    private List<User> users = new LinkedList<User>();

    @Test
    public void fetchRecords() {
        context.checking(new Expectations() {{
            oneOf(dataAccessObject).fetchRecords("SELECT * FROM  users");
            will(returnValue(users));
        }});
        dataAccessObject.fetchRecords("SELECT * FROM  users");
    }

    @Test
    public void updateRecord() {
        context.checking(new Expectations() {{
            oneOf(dataAccessObject).update("UPDATE users SET ID = 1,USER_NAME = 'gosho', PASSWORD = 'pass', EMAIL = 'email.@gmail.com");
        }});
        dataAccessObject.update("UPDATE users SET ID = 1,USER_NAME = 'gosho', PASSWORD = 'pass', EMAIL = 'email.@gmail.com");
    }

    @Test(expected = NoRecordFoundException.class)
    public void deleteRecord() {
        context.checking(new Expectations() {{
            oneOf(dataAccessObject).update("DELETE FROM users WHERE USER_NAME = 'user'");
            oneOf(dataAccessObject).fetchRecords("SELECT * FROM users WHERE USER_NAME = 'user'");
            will(throwException(new NoRecordFoundException("")));
        }});
        dataAccessObject.update("DELETE FROM users WHERE USER_NAME = 'user'");
        dataAccessObject.fetchRecords("SELECT * FROM users WHERE USER_NAME = 'user'");
    }

    @Test
    public void createTable() {
        context.checking(new Expectations() {{
            oneOf(dataAccessObject).createTable("CREATE TABLE table(TUTORIALS POINT column1 INT, PRIMARY KEY (column1));");
        }});
        dataAccessObject.createTable("CREATE TABLE table(TUTORIALS POINT column1 INT, PRIMARY KEY (column1));");
    }

    @Test(expected = NoTableOrDroppedTableException.class)
    public void deleteTable() {
        context.checking(new Expectations() {{
            oneOf(dataAccessObject).update("DROP TABLE name");
        }});
        dataAccessObject.update("DROP TABLE name");
    }
}