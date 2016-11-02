package com.clouway.jdbtqueries;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Test;

import java.sql.SQLException;
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
    public void getRecords() {
        context.checking(new Expectations() {{
            oneOf(dataAccessObject).getAllRecord("users", User.class);
            will(returnValue(users));
        }});
        dataAccessObject.getAllRecord("users", User.class);
    }

    @Test
    public void getRecord() {
        final User user = new User();
        context.checking(new Expectations() {{
            oneOf(dataAccessObject).getRecord("users", user);
            will(returnValue(user));
        }});
        dataAccessObject.getRecord("users", user);
    }

    @Test
    public void updateRecord() {
        final User user = new User();
        final User updatedUser = new User();
        context.checking(new Expectations() {{
            oneOf(dataAccessObject).updateRecord("users", user, updatedUser);
            oneOf(dataAccessObject).getRecord("users", user);
            will(returnValue(new User()));
        }});
        dataAccessObject.updateRecord("users", user, updatedUser);
        dataAccessObject.getRecord("users", user);
    }

    @Test(expected = NoRecordFoundException.class)
    public void deleteRecord(){
        final User user = new User();
        context.checking(new Expectations() {{
            oneOf(dataAccessObject).deleteRecord("users", user);
            oneOf(dataAccessObject).getRecord("users", user);
            will(throwException(new NoRecordFoundException("")));
        }});
        dataAccessObject.deleteRecord("users", user);
        dataAccessObject.getRecord("users", user);
    }

    @Test
    public void createTable(){
        final User user = new User();
        context.checking(new Expectations() {{
            oneOf(dataAccessObject).createTable("users", user);
            oneOf(dataAccessObject).getAllRecord("users", User.class);
            will(returnValue(users));
        }});
        dataAccessObject.createTable("users", user);
        dataAccessObject.getAllRecord("users", User.class);
    }

    @Test(expected = NoTableOrDroppedTableException.class)
    public void deleteTable(){
        context.checking(new Expectations() {{
            oneOf(dataAccessObject).deleteTable("users");
            oneOf(dataAccessObject).getAllRecord("users", User.class);
            will(throwException(new NoTableOrDroppedTableException("")));
        }});
        dataAccessObject.deleteTable("users");
        dataAccessObject.getAllRecord("users", User.class);
    }
}