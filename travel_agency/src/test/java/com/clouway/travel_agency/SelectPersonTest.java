package com.clouway.travel_agency;

import com.clouway.travel_agency.domain_layer.Person;
import com.clouway.travel_agency.domain_layer.PersonRepository;
import com.clouway.travel_agency.domain_layer.Trip;
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
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
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
    @Rule
    public DatabaseTableRule databaseTableRule = new DatabaseTableRule(new DataStore(dataBaseConnectionRule.connection), new LinkedList<String>(){{add("Trip");add("People");}});
    private Connection connection = dataBaseConnectionRule.connection;
    private PersonRepository personRepository = new PersistencePersonRepository(connection);
    private TripRepository tripRepository = new PersistenceTripRepository(connection);

    public SelectPersonTest() throws SQLException {
    }

    @Test
    public void happyPath(){
        Person expectedFirst = new Person("Gosho", 9090909090L, 23, "email@email.com");
        Person expectedSecond = new Person("Pesho", 9191919191L, 27, "gemail@gemail.com");
        personRepository.register(new Person("Gosho", 9090909090L, 23, "email@email.com"));
        personRepository.register(new Person("Pesho", 9191919191L, 27, "gemail@gemail.com"));

        List<Person> actual = personRepository.getAll();

        Person actualFirst = actual.get(0);
        Person actualSecond = actual.get(1);
        assertThat(actualFirst.equals(expectedFirst), is(true));
        assertThat(actualSecond.equals(expectedSecond), is(true));
    }

    @Test
    public void peopleStartsWith(){
        Person expected = new Person("Pesho", 9191919191L, 27, "gemail@gemail.com");
        personRepository.register(expected);
        List<Person> people = personRepository.startsWith("P");
        Person actual =  people.get(0);
        assertThat(actual.equals(expected), is(true));
    }


    @Test
    public void bySameCity(){
        personRepository.register(new Person("Gosho", 9090909090L, 23, "email@email.com"));
        personRepository.register(new Person("Pesho", 9191919191L, 27, "gemail@gemail.com"));
        tripRepository.register(new Trip(9090909090L, new Date(1290262492000L), new Date(1290694492000L), "Sofia"));
        tripRepository.register(new Trip(9191919191L, new Date(1290262492000L), new Date(1290694492000L), "Sofia"));
        Person expectedFirst = new Person("Gosho", 9090909090L, 23, "email@email.com");
        Person expectedSecond = new Person("Pesho", 9191919191L, 27, "gemail@gemail.com");
        List<Person> expected = new LinkedList<>();
        expected.add(expectedFirst);
        expected.add(expectedSecond);
        List<Person> actual = personRepository.inSameCity("Sofia", new Date(1290262492000L));
        assertThat(actual,is(equalTo(expected)));
        }
}