package com.clouway.travel_agency;

import com.clouway.travel_agency.domain_layer.*;
import com.clouway.travel_agency.persistence_layer.PersistenceCityRepo;
import com.clouway.travel_agency.persistence_layer.PersistencePersonRepo;
import com.clouway.travel_agency.persistence_layer.PersistenceTripRepo;
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
 * Created by zumba on 16.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class SelectCityTest {
    @Rule
    public DataBaseConnectionRule dataBaseConnectionRule = new DataBaseConnectionRule();
    private Connection connection = dataBaseConnectionRule.connection;
    private PersonRepo personRepo = new PersistencePersonRepo(connection);
    private TripRepo tripRepo = new PersistenceTripRepo(connection);
    private CityRepo cityRepo = new PersistenceCityRepo(connection);

    public SelectCityTest() throws SQLException {
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
        tripRepo.register(new Trip(9191919191L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Pleven"));
        tripRepo.register(new Trip(9292929292L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Sofia"));
        tripRepo.register(new Trip(9292929292L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Pleven"));
        tripRepo.register(new Trip(9191919191L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Pleven"));
        tripRepo.register(new Trip(9292929292L, new java.sql.Date(1290262492000L), new java.sql.Date(1290694492000L), "Pleven"));
    }

    @Test
    public void getAll() {
        City expectedFirst = new City("Pleven");
        City expectedSecond = new City("Sofia");
        List<City> cities = cityRepo.getAll();
        City actualFirst = cities.get(0);
        City actualSecond = cities.get(1);
        City actualThird = cities.get(2);
        assertThat(actualFirst.getName(), is(equalTo(expectedFirst.getName())));
        assertThat(actualSecond.getName(), is(equalTo(expectedSecond.getName())));
        assertThat(actualThird.getName(), is(equalTo(expectedFirst.getName())));
    }

    @Test
    public void getCities() {
        City expectedFirst = new City("Sofia");
        City expectedSecond  = new City("Pleven");
        List<City> actualCities =  cityRepo.sortByAscending();
        City actualFirst = actualCities.get(0);
        City actualSecond = actualCities.get(1);

        assertThat(actualFirst.getName(),is(equalTo(expectedFirst.getName())));
        assertThat(actualSecond.getName(),is(equalTo(expectedSecond.getName())));
         }
}