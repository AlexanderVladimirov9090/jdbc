package com.clouway.travel_agency;

import com.clouway.travel_agency.domain_layer.Person;
import com.clouway.travel_agency.domain_layer.PersonRepo;
import com.clouway.travel_agency.domain_layer.TripRepo;
import com.clouway.travel_agency.persistence_layer.PersistencePersonRepo;
import com.clouway.travel_agency.persistence_layer.PersistenceTripRepo;
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
    private PersonRepo personRepo = new PersistencePersonRepo(connection);
    private TripRepo tripRepo = new PersistenceTripRepo(connection);
    public InsertAndUpdatePersonTest() throws SQLException {
    }

    @Before
    public void createAndPopulate() {
        tripRepo.deleteTable();
        personRepo.deleteTable();
        personRepo.createTable();
        tripRepo.createTable();
        personRepo.register(new Person("Gogo", 3333333333L, 13, "no0"));
        personRepo.register(new Person("Delete", 1111111111L, 44, "d"));
    }

    @Test
    public void addPerson() {
        Person expected = new Person("Ivan", 1212121212L, 15, "food@email.com");
        personRepo.register(expected);
        List actual = personRepo.peopleStartsWith("Ivan");
        Person actualPerson = (Person) actual.get(0);
        assertThat(actualPerson.equal(expected), is(true));
    }

    @Test
    public void updatePerson() {
        Person updatedPerson = new Person("Zozo", 3333333333L, 99, "yes");
        personRepo.updatePerson(updatedPerson);
        List actual = personRepo.peopleStartsWith("Zozo");
        Person actualPerson = (Person) actual.get(0);
        assertThat(actualPerson.equal(updatedPerson), is(true));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deletePerson() {
        personRepo.deletePersonByEGN(1111111111L);
        List actual = personRepo.peopleStartsWith("Delete");
        actual.get(0);
    }
}
