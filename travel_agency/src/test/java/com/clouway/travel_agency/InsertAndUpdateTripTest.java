package com.clouway.travel_agency;

import com.clouway.travel_agency.domain.Person;
import com.clouway.travel_agency.domain.PersonRepo;
import com.clouway.travel_agency.domain.Trip;
import com.clouway.travel_agency.domain.TripRepo;
import com.clouway.travel_agency.entity.PersistencePersonRepo;
import com.clouway.travel_agency.entity.PersistenceTripRepo;
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
    private TripRepo tripRepo = new PersistenceTripRepo(connection);
    private PersonRepo personRepo = new PersistencePersonRepo(connection);

    public InsertAndUpdateTripTest() throws SQLException {
    }

    @Before
    public void createPeopleTableAndPopulate() {
        tripRepo.deleteTable();
        personRepo.deleteTable();
        personRepo.createTable();
        tripRepo.createTable();
        personRepo.register(new Person("Gosho", 9090909090L, 23, "email@email.com"));
        personRepo.register(new Person("Pesho", 9191919191L, 27, "gemail@gemail.com"));
        personRepo.register(new Person("Petur", 9292929292L, 28, "semail@semail.com"));
        tripRepo.register(new Trip(9191919191L,new java.sql.Date(1290262492000L),new java.sql.Date(1290694492000L) , "Pleven"));
        tripRepo.register(new Trip(9292929292L,new java.sql.Date(1290262492000L),new java.sql.Date(1290694492000L) , "Pleven"));
    }

    @Test
    public void happyPath() {

        Trip expected = new Trip(9090909090L,new java.sql.Date(1290262492000L),new java.sql.Date(1290694492000L) , "Pleven");
        tripRepo.register(expected);
        tripRepo.deleteTripByEGN(9090909090L);
        List<Trip> trips = tripRepo.getAll();
        assertThat(trips.size(),is(equalTo(2)));
    }

    @Test
    public void updateTrip(){
        Trip expected = new Trip(9090909090L,new java.sql.Date(1290262492000L),new java.sql.Date(1290694492000L) , "Sofia");
        tripRepo.register(new Trip(9090909090L,new java.sql.Date(1290262492000L),new java.sql.Date(1290694492000L) , "Pleven"));
        tripRepo.updateTrip(expected);
        List<Trip> trips = tripRepo.getAll();
        Trip actual = trips.get(2);
        assertThat(actual.equal(expected),is(true));
    }

}