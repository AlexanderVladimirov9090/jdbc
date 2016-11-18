package com.clouway.travel_agency;

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
    private Connection connection = dataBaseConnectionRule.connection;
    private TripRepository tripRepository = new PersistenceTripRepository(connection);
    private PersonRepository personRepository = new PersistencePersonRepository(connection);
    private DataStore dataStore = new DataStore(connection);

    @Before
    public void setup() {
        dataStore.update("DROP TABLE IF EXISTS Trip");
        dataStore.update("DROP TABLE IF EXISTS People");
        dataStore.update("CREATE TABLE People ( Name VARCHAR(255), EGN BIGINT NOT NULL, AGE INT NOT NULL, Email VARCHAR(255), PRIMARY KEY (EGN))");
        dataStore.update("CREATE TABLE Trip ( EGN BIGINT NOT NULL, DateOfArrival DATE NOT NULL, DateOfDeparture DATE NOT NULL, City VARCHAR(56), FOREIGN KEY (EGN) REFERENCES People(EGN))");
        personRepository.register("Pesho", 9090909090L, 12, "mail.com");
        personRepository.register("Pesho", 9292929292L, 12, "mail.com");
        tripRepository.register(9292929292L, new Date(1290262492000L), new Date(1290694492000L), "Sofia");
        tripRepository.register(9090909090L, new Date(1290262492000L), new Date(1290694492000L), "Pleven");
        tripRepository.register(9090909090L, new Date(1290262492000L), new Date(1290694492000L), "Sofia");
    }

    public SelectTripTest() throws SQLException {
    }

    @Test
    public void happyPath() {
        Trip expectedFirst = new Trip(9292929292L, new Date(1290262492000L), new Date(1290694492000L), "Sofia");
        Trip expectedSecond = new Trip(9090909090L, new Date(1290262492000L), new Date(1290694492000L), "Pleven");
        Trip expectedThird = new Trip(9090909090L, new Date(1290262492000L), new Date(1290694492000L), "Sofia");
        List trips = tripRepository.getAll();
        Trip actual = (Trip) trips.get(0);
        Trip actualSecond = (Trip) trips.get(1);
        Trip actualThird = (Trip) trips.get(2);
        assertThat(actual.equals(expectedFirst), is(true));
        assertThat(actualSecond.equals(expectedSecond), is(true));
        assertThat(actualThird.equals(expectedThird), is(true));
    }

    @Test
    public void getCitiesByOrder() {
        String expectedFirst = "Sofia";
        String expectedSecond = "Pleven";
        List<String> cities = tripRepository.citiesByVisit();
        String actualFirst = cities.get(0);
        String actualSecond = cities.get(1);
        assertThat(actualFirst, is(equalTo(expectedFirst)));
        assertThat(actualSecond, is(equalTo(expectedSecond)));

    }
}
