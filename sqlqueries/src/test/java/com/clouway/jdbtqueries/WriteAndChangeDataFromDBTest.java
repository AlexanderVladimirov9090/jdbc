package com.clouway.jdbtqueries;

import org.junit.Test;

import java.util.LinkedList;

/**
 * Created by zumba on 04.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class WriteAndChangeDataFromDBTest {
    private Connector jdbcConnector = new Connector("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jdbc_database", "root", "123123");

    @Test
    public void addUserToDataBase(){
        DataStore userDataStore = new DataStore(new LinkedList(), jdbcConnector.getConnection());
        User user = new User(3,"Jordan","verysecretapassword","jordan@email.com");
        userDataStore.update("INSERT INTO users VALUES ( ?, ?, ?, ? )",user.getId(),user.getUserName(),user.getPassword(),user.getEmail());
    }

    @Test
    public void  createTable(){
        DataStore userDataStore = new DataStore(new LinkedList(), jdbcConnector.getConnection());
        userDataStore.update("CREATE TABLE People ( Name VARCHAR (30) NOT NULL, EGN INT (10) NOT NULL, Age INT (3) NOT NULL, Email VARCHAR (254), PRIMARY KEY (EGN));");
    }

    @Test
    public void dropTable(){
        DataStore userDataStore = new DataStore(new LinkedList(), jdbcConnector.getConnection());
        userDataStore.update("DROP TABLE People;");
    }
}