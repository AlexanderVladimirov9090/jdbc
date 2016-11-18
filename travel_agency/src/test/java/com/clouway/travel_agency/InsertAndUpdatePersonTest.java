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
import java.util.List;

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
    private Connection connection = dataBaseConnectionRule.connection;
    private PersonRepository personRepository = new PersistencePersonRepository(connection);
    private DataStore dataStore = new DataStore(connection);
    public InsertAndUpdatePersonTest() throws SQLException {
    }

    @Before
    public void createAndPopulate() {
        dataStore.update("DROP TABLE IF EXISTS Trip");
        dataStore.update("TRUNCATE TABLE People");

        personRepository.register("Gogo", 3333333333L, 13, "no0");
        personRepository.register("Delete", 1111111111L, 44, "d");
    }

    @Test
    public void addPerson() {
        Person expected = new Person("Ivan", 1212121212L, 15, "food@email.com");
        personRepository.register("Ivan", 1212121212L, 15, "food@email.com");
        List actual = personRepository.getAll();
        Person actualPerson = (Person) actual.get(1);
        assertThat(actualPerson.equals(expected), is(true));
    }

    @Test
    public void updatePerson() {
        Person updatedPerson = new Person("Zozo", 3333333333L, 99, "yes");
        personRepository.updatePerson("Zozo", 3333333333L, 99, "yes");
        List actual = personRepository.peopleStartsWith("Zozo");
        Person actualPerson = (Person) actual.get(0);
        assertThat(actualPerson.equals(updatedPerson), is(true));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deletePerson() {
        personRepository.deletePersonByEGN(1111111111L);
        List actual = personRepository.peopleStartsWith("Delete");
        actual.get(0);
    }
}
