package com.clouway.travel_agency;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 09.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class SelectPersonTest {

    @Test
    public void happyPath() throws ClassNotFoundException, SQLException {
        Person expected = new Person("Goro", 20, 1010101010L);
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jdbc_database", "root", "clouway.com");
        PersonDao personDao = new PersonDaoImp(connection);
        List actual = personDao.getPeople();
        Person actualPerson = (Person) actual.get(0);
        assertThat(actualPerson.equal(expected), is(true));
    }

    @Test
    public void peopleStartsWith() throws ClassNotFoundException, SQLException {
        Person expected = new Person("Petur", 76, 1212121212L);
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jdbc_database", "root", "clouway.com");
        PersonDao personDao = new PersonDaoImp(connection);
        List actual = personDao.peopleStartsWith("P");
        Person actualPerson = (Person) actual.get(0);
        assertThat(actualPerson.equal(expected), is(true));
    }


    @Test(expected = IllegalStateException.class)
    public void sQLInjection() throws ClassNotFoundException, SQLException {
        Person expected = new Person("Petur", 76, 1212121212L);
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jdbc_database", "root", "clouway.com");
        PersonDao personDao = new PersonDaoImp(connection);
        List actual = personDao.peopleStartsWith("'; DROP TABLE post_comment; -- '");
    }

}