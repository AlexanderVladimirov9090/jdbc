package com.clouway.travel_agency;

import com.clouway.travel_agency.domain_layer.Person;
import com.clouway.travel_agency.domain_layer.PersonRepository;
import com.clouway.travel_agency.domain_layer.Trip;
import com.clouway.travel_agency.domain_layer.TripRepository;
import com.clouway.travel_agency.persistence_layer.DataStore;
import com.clouway.travel_agency.persistence_layer.PersistencePersonRepository;
import com.clouway.travel_agency.persistence_layer.PersistenceTripRepository;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
    @Rule
    public DatabaseTableRule databaseTableRule = new DatabaseTableRule(new DataStore(dataBaseConnectionRule.connection), new LinkedList<String>() {{
        add("Trip");
        add("People");
    }});

    private Connection connection = dataBaseConnectionRule.connection;
    private TripRepository tripRepository = new PersistenceTripRepository(connection);
    private PersonRepository personRepository = new PersistencePersonRepository(connection);

    public InsertAndUpdateTripTest() throws SQLException {
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteTrip() {
        personRepository.register(new Person("Pesho", 9090909090L, 27, "gemail@gemail.com"));
        personRepository.register(new Person("Petur", 9292929292L, 28, "semail@semail.com"));
        tripRepository.register(new Trip(9090909090L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Pleven"));
        tripRepository.delete(9090909090L);
        List<Trip> trips = tripRepository.getAll();

        trips.get(0);
    }

    @Test
    public void updateTrip() {
        personRepository.register(new Person("Pesho", 9090909090L, 27, "gemail@gemail.com"));
        personRepository.register(new Person("Petur", 9292929292L, 28, "semail@semail.com"));
        tripRepository.register(new Trip(9090909090L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Pleven"));
        Trip expected = new Trip(9090909090L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Sofia");
        tripRepository.updateTrip(new Trip(9090909090L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Sofia"));
        List<Trip> trips = tripRepository.getAll();
        Trip actual = trips.get(0);
        assertThat(actual.equals(expected), is(true));
    }
}