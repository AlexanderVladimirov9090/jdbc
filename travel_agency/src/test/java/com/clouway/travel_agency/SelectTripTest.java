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
 * Created by zumba on 15.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class SelectTripTest {
    @Rule
    public DataBaseConnectionRule dataBaseConnectionRule = new DataBaseConnectionRule();
    @Rule
    public DatabaseTableRule databaseTableRule = new DatabaseTableRule(new DataStore(dataBaseConnectionRule.connection), new LinkedList<String>(){{add("Trip");add("People");}});
    private Connection connection = dataBaseConnectionRule.connection;
    private TripRepository tripRepository = new PersistenceTripRepository(connection);
    private PersonRepository personRepository = new PersistencePersonRepository(connection);

    public SelectTripTest() throws SQLException {
    }

    @Test
    public void happyPath() {
        personRepository.register(new Person("Pesho", 9090909090L, 12, "mail.com"));
        personRepository.register(new Person("Pesho", 9292929292L, 12, "mail.com"));

        Trip expectedFirst = new Trip(9292929292L, new Date(1290262492000L), new Date(1290694492000L), "Sofia");
        Trip expectedSecond = new Trip(9090909090L, new Date(1290262492000L), new Date(1290694492000L), "Pleven");
        Trip expectedThird = new Trip(9090909090L, new Date(1290262492000L), new Date(1290694492000L), "Sofia");

        tripRepository.register(new Trip(9292929292L, new Date(1290262492000L), new Date(1290694492000L), "Sofia"));
        tripRepository.register(new Trip(9090909090L, new Date(1290262492000L), new Date(1290694492000L), "Pleven"));
        tripRepository.register(new Trip(9090909090L, new Date(1290262492000L), new Date(1290694492000L), "Sofia"));

        List<Trip> trips = tripRepository.getAll();
        Trip actual = trips.get(0);
        Trip actualSecond = trips.get(1);
        Trip actualThird = trips.get(2);
        assertThat(actual.equals(expectedFirst), is(true));
        assertThat(actualSecond.equals(expectedSecond), is(true));
        assertThat(actualThird.equals(expectedThird), is(true));
    }

    @Test
    public void getCitiesByOrder() {
        personRepository.register(new Person("Pesho", 9090909090L, 12, "mail.com"));
        personRepository.register(new Person("Pesho", 9292929292L, 12, "mail.com"));

        tripRepository.register(new Trip(9292929292L, new Date(1290262492000L), new Date(1290694492000L), "Sofia"));
        tripRepository.register(new Trip(9090909090L, new Date(1290262492000L), new Date(1290694492000L), "Pleven"));
        tripRepository.register(new Trip(9090909090L, new Date(1290262492000L), new Date(1290694492000L), "Sofia"));

        String expectedFirst = "Sofia";
        String expectedSecond = "Pleven";
        List<String> cities = tripRepository.citiesByVisit();
        String actualFirst = cities.get(0);
        String actualSecond = cities.get(1);
        assertThat(actualFirst, is(equalTo(expectedFirst)));
        assertThat(actualSecond, is(equalTo(expectedSecond)));

    }
}