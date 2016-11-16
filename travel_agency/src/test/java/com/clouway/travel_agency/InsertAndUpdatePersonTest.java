package com.clouway.travel_agency;

import com.clouway.travel_agency.domain_layer.Person;
import com.clouway.travel_agency.domain_layer.PersonRepository;
import com.clouway.travel_agency.persistence_layer.DataStore;
import com.clouway.travel_agency.persistence_layer.PersistencePersonRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zumba on 14.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class InsertAndUpdatePersonTest {
    @Rule
    public DataBaseConnectionRule dataBaseConnectionRule = new DataBaseConnectionRule();
    @Rule
    public DatabaseTableRule databaseTableRule = new DatabaseTableRule(new DataStore(dataBaseConnectionRule.connection), new LinkedList<String>() {{
        add("Trip");
        add("People");
    }});
    private Connection connection = dataBaseConnectionRule.connection;
    private PersonRepository personRepository = new PersistencePersonRepository(connection);

    public InsertAndUpdatePersonTest() throws SQLException {
    }

    @Test
    public void addPerson() {
        Person expectedFirst = new Person("Ivan", 1212121212L, 15, "food@email.com");
        personRepository.register(new Person("Ivan", 1212121212L, 15, "food@email.com"));
        List<Person> expected = new LinkedList<>();
        expected.add(expectedFirst);
        List<Person> actual = personRepository.getAll();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void addMoreThanOne() {
        Person expectedFirst = new Person("Wut", 00000000000L, 56, "@email.com");
        Person expectedSecond = new Person("Zimbabe", 9999999999L, 65, "food@.com");
        personRepository.register(new Person("Wut", 00000000000L, 56, "@email.com"));
        personRepository.register(new Person("Zimbabe", 9999999999L, 65, "food@.com"));
        List<Person> expected = new LinkedList<>();
        expected.add(expectedFirst);
        expected.add(expectedSecond);
        List<Person> actual = personRepository.getAll();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void updatePerson() {
        personRepository.register(new Person("Gogo", 3333333333L, 13, "no0"));
        Person updatedPerson = new Person("Zozo", 3333333333L, 99, "yes");
        personRepository.update(new Person("Zozo", 3333333333L, 99, "yes"));
        List actual = personRepository.startsWith("Zozo");
        Person actualPerson = (Person) actual.get(0);
        assertThat(actualPerson.equals(updatedPerson), is(true));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deletePerson() {
        personRepository.deleteByEGN(1111111111L);
        List actual = personRepository.startsWith("Delete");
        actual.get(0);
    }
}