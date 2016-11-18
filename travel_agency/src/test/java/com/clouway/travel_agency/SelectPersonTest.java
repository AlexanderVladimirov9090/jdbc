package com.clouway.travel_agency;

import com.clouway.travel_agency.domain_layer.Person;
import com.clouway.travel_agency.domain_layer.PersonRepository;
import com.clouway.travel_agency.domain_layer.TripRepository;
import com.clouway.travel_agency.persistence_layer.DataStore;
import com.clouway.travel_agency.persistence_layer.PersistencePersonRepository;
import com.clouway.travel_agency.persistence_layer.PersistenceTripRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
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
    @Rule
    public DataBaseConnectionRule dataBaseConnectionRule = new DataBaseConnectionRule();
    private Connection connection = dataBaseConnectionRule.connection;
    private PersonRepository personRepository = new PersistencePersonRepository(connection);
    private TripRepository tripRepository = new PersistenceTripRepository(connection);
    private DataStore dataStore = new DataStore(connection);

    public SelectPersonTest() throws SQLException {
    }

    @Before
    public void createPeopleTableAndPopulate() {
        dataStore.update("DROP TABLE IF EXISTS Trip");
        dataStore.update("TRUNCATE TABLE People");
        personRepository.register("Gosho", 9090909090L, 23, "email@email.com");
        personRepository.register("Pesho", 9191919191L, 27, "gemail@gemail.com");
    }

    @Test
    public void happyPath() throws ClassNotFoundException, SQLException {
        Person expectedFirst = new Person("Gosho", 9090909090L, 23, "email@email.com");
        Person expectedSecond = new Person("Pesho", 9191919191L, 27, "gemail@gemail.com");
        List actual = personRepository.getAll();
        Person actualFirst = (Person) actual.get(0);
        Person actualSecond = (Person) actual.get(1);
        assertThat(actualFirst.equals(expectedFirst), is(true));
        assertThat(actualSecond.equals(expectedSecond), is(true));
    }

    @Test
    public void peopleStartsWith() throws ClassNotFoundException, SQLException {
        Person expectedFirst = new Person("Pesho", 9191919191L, 27, "gemail@gemail.com");
        List actual = personRepository.peopleStartsWith("P");
        Person actualFirst = (Person) actual.get(0);
        assertThat(actualFirst.equals(expectedFirst), is(true));
    }


    @Test
    public void bySameCity() {
        dataStore.update("CREATE TABLE Trip ( EGN BIGINT NOT NULL, DateOfArrival DATE NOT NULL, DateOfDeparture DATE NOT NULL, City VARCHAR(56), FOREIGN KEY (EGN) REFERENCES People(EGN))");
        tripRepository.register(9090909090L, new Date(1290262492000L), new Date(1290694492000L), "Pleven");
        tripRepository.register(9191919191L, new Date(1290262492000L), new Date(1290694492000L), "Pleven");
        Person expectedFirst = new Person("Gosho", 9090909090L, 23, "email@email.com");
        Person expectedSecond = new Person("Pesho", 9191919191L, 27, "gemail@gemail.com");
        List actual = personRepository.peopleInSameCity("Pleven", 1290262492000L);
        Person actualFirst = (Person) actual.get(0);
        Person actualSecond = (Person) actual.get(1);
        assertThat(actualFirst.equals(expectedFirst), is(true));
        assertThat(actualSecond.equals(expectedSecond), is(true));
    }
}