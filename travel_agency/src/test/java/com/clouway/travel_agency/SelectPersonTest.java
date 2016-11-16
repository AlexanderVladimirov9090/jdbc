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
    private PersonRepo personRepo = new PersistencePersonRepo(connection);
    private TripRepo tripRepo = new PersistenceTripRepo(connection);
    public SelectPersonTest() throws SQLException {
    }
    @Before
    public void createPeopleTableAndPopulate() {
        tripRepo.deleteTable();
        personRepo.deleteTable();
        personRepo.createTable();
        tripRepo.createTable();
        personRepo.register(new Person("Gosho",9090909090L, 23, "email@email.com"));
        personRepo.register(new Person("Pesho", 9191919191L, 27, "gemail@gemail.com"));
        personRepo.register(new Person("Petur",9292929292L,28,"semail@semail.com"));
        tripRepo.register(new Trip(9090909090L,new Date(1290262492000L),new Date(1290694492000L), "Pleven"));
        tripRepo.register(new Trip(9191919191L,new Date(1290262492000L),new Date(1290694492000L), "Pleven"));
    }
    @Test
    public void happyPath() throws ClassNotFoundException, SQLException {
        Person expected = new Person("Gosho", 9090909090L, 23, "email@email.com");
        List actual = personRepo.getAll();
        Person actualPerson = (Person) actual.get(0);
        assertThat(actualPerson.equal(expected), is(true));
    }

    @Test
    public void peopleStartsWith() throws ClassNotFoundException, SQLException {
        Person expectedFirst = new Person("Pesho", 9191919191L, 27, "gemail@gemail.com");
        Person expectedSecond=new Person("Petur",9292929292L,28,"semail@semail.com");
        List actual = personRepo.peopleStartsWith("P");
        Person actualFirst = (Person) actual.get(0);
        Person actualSecond = (Person) actual.get(1);
        assertThat(actualFirst.equal(expectedFirst), is(true));
        assertThat(actualSecond.equal(expectedSecond),is(true));
    }


    @Test
    public void byCity() {
        Person expectedFirst = new Person("Gosho", 9090909090L, 23,"email@email.com" );
        Person expectedSecond = new Person("Pesho", 9191919191L, 27, "gemail@gemail.com");
        List actual = personRepo.peopleInSameCity("Pleven","2010-12-12");
        Person actualFirst = (Person) actual.get(0);
        Person actualSecond = (Person) actual.get(1);
        assertThat(actualFirst.equal(expectedFirst),is(true));
        assertThat(actualSecond.equal(expectedSecond),is(true));
    }
}