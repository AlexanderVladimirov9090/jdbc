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
public class UserDataAccessTest {
    private JUnitRuleMockery context = new JUnitRuleMockery();
    private DataAccess dataAccess = context.mock(DataAccess.class);
    private List<User> users = new LinkedList<User>();

    @Test
    public void getRecords() {
        context.checking(new Expectations() {{
            oneOf(dataAccess).getAllRecord("users", User.class);
            will(returnValue(users));
        }});
        dataAccess.getAllRecord("users", User.class);
    }

    @Test
    public void getRecord() {
        User user = new User(0,"","","");
        context.checking(new Expectations() {{
            oneOf(dataAccess).getRecord("users", user);
            will(returnValue(user));
        }});
        dataAccess.getRecord("users", user);
    }

    @Test
    public void updateRecord() {
        User user = new User(0,"","","");
        User updatedUser = new User(0,"","","");
        context.checking(new Expectations() {{
            oneOf(dataAccess).updateRecord("users", user, updatedUser);
            oneOf(dataAccess).getRecord("users", user);
            will(returnValue(new User(0,"","","")));
        }});
        dataAccess.updateRecord("users", user, updatedUser);
        dataAccess.getRecord("users", user);
    }

    @Test(expected = NoRecordFoundException.class)
    public void deleteRecord(){
        User user = new User(0,"","","");
        context.checking(new Expectations() {{
            oneOf(dataAccess).deleteRecord("users", user);
            oneOf(dataAccess).getRecord("users", user);
            will(throwException(new NoRecordFoundException("")));
        }});
        dataAccess.deleteRecord("users", user);
        dataAccess.getRecord("users", user);
    }

    @Test
    public void createTable(){
        User user = new User(0,"","","");
        context.checking(new Expectations() {{
            oneOf(dataAccess).createTable("users", user);
            oneOf(dataAccess).getAllRecord("users", User.class);
            will(returnValue(users));
        }});
        dataAccess.createTable("users", user);
        dataAccess.getAllRecord("users", User.class);
    }

    @Test(expected = NoTableOrDroppedTableException.class)
    public void deleteTable(){
        context.checking(new Expectations() {{
            oneOf(dataAccess).deleteTable("users");
            oneOf(dataAccess).getAllRecord("users", User.class);
            will(throwException(new NoTableOrDroppedTableException("")));
        }});
        dataAccess.deleteTable("users");
        dataAccess.getAllRecord("users", User.class);
    }
}