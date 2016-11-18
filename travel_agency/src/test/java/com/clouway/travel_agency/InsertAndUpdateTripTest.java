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
public class InsertAndUpdateTripTest {
    @Rule
    public DataBaseConnectionRule dataBaseConnectionRule = new DataBaseConnectionRule();
    private Connection connection = dataBaseConnectionRule.connection;
    private TripRepository tripRepository = new PersistenceTripRepository(connection);
    private PersonRepository personRepository = new PersistencePersonRepository(connection);
    private DataStore dataStore = new DataStore(connection);
    public InsertAndUpdateTripTest() throws SQLException {
    }

    @Before
    public void createPeopleTableAndPopulate() {
        dataStore.update("DROP TABLE IF EXISTS Trip");
        dataStore.update("DROP TABLE IF EXISTS People");
        dataStore.update("CREATE TABLE People ( Name VARCHAR(255), EGN BIGINT NOT NULL, AGE INT NOT NULL, Email VARCHAR(255), PRIMARY KEY (EGN))");
        dataStore.update("CREATE TABLE Trip ( EGN BIGINT NOT NULL, DateOfArrival DATE NOT NULL, DateOfDeparture DATE NOT NULL, City VARCHAR(56), FOREIGN KEY (EGN) REFERENCES People(EGN))");
        personRepository.register("Gosho", 9090909090L, 23, "email@email.com");
        personRepository.register("Pesho", 9191919191L, 27, "gemail@gemail.com");
        personRepository.register("Petur", 9292929292L, 28, "semail@semail.com");
        tripRepository.register(9090909090L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Pleven");
        tripRepository.register(9191919191L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Pleven");
        tripRepository.register(9292929292L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Pleven");
    }

    @Test
    public void happyPath() {

        tripRepository.register(9090909090L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Pleven");
        tripRepository.deleteTripByEGN(9090909090L);
        List<Trip> trips = tripRepository.getAll();
        assertThat(trips.size(), is(equalTo(2)));
    }

    @Test
    public void updateTrip() {
        Trip expected = new Trip(9090909090L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Sofia");
        tripRepository.updateTrip(9090909090L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Sofia");
        List<Trip> trips = tripRepository.getAll();
        Trip actual = trips.get(0);
        assertThat(actual.equals(expected), is(true));
    }

    @Test
    public void deleteTrip() {
        tripRepository.deleteTripByEGN(9191919191L);
        List<Trip> trips = tripRepository.getAll();
        assertThat(trips.size(), is(equalTo(2)));
    }

}