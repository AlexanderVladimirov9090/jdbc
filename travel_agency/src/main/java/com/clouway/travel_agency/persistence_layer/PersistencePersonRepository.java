package com.clouway.travel_agency.persistence_layer;

import com.clouway.travel_agency.domain_layer.Person;
import com.clouway.travel_agency.domain_layer.PersonRepository;
import com.google.common.collect.Lists;

import java.sql.*;
import java.util.List;

/**
 * Created by clouway on 09.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class PersistencePersonRepository implements PersonRepository {
    private final Connection connection;
    private final DataStore dataStore;

    public PersistencePersonRepository(Connection connection) {
        this.connection = connection;
        this.dataStore = new DataStore(connection);
    }

    /**
     * Gets People from table People from Database.
     *
     * @return list of people.
     */
    @Override
    public List<Person> getAll() {
        return dataStore.fetchRows("SELECT * FROM People", resultSet -> new Person(resultSet.getString(1), resultSet.getLong(2), resultSet.getInt(3), resultSet.getString(4)));
    }

    /**
     * Gets people that Starts with some Characters.
     *
     * @param startsWith starting pattern.
     * @return
     */
    @Override
    public List<Person> startsWith(String startsWith) {
        return dataStore.fetchRows("SELECT * FROM People WHERE Name LIKE ?",
                resultSet -> new Person(resultSet.getString(1), resultSet.getLong(2), resultSet.getInt(3), resultSet.getString(4)), startsWith + "%");
    }

    /**
     * Gets people by the city that they are from Arrived Date.
     *
     * @param city where they are.
     * @param date Arrived date of person.
     * @return people that are in the same city.
     */
    @Override
    public List<Person> inSameCity(String city, Date date) {
     return    dataStore.fetchRows("SELECT * FROM People INNER JOIN Trip ON People.EGN=Trip.EGN WHERE Trip.City = ? AND Trip.DateOfArrival <= ? AND ? < Trip.DateOfDeparture"
             , resultSet -> new Person(resultSet.getString(1),resultSet.getLong(2),resultSet.getInt(3),resultSet.getString(4)), city, date, date);
    }

    /**
     * Register person to database.
     *
     * @param person new person that is going to be register to database.
     */
    @Override
    public void register(Person person) {
        dataStore.update("INSERT INTO People VALUES (?,?,?,?)", person.name, person.egn, person.age, person.email);
    }

    /**
     * Updates existing person from database.
     *
     * @param person new person with changes for existing person in database.
     */
    @Override
    public void update(Person person) {
        dataStore.update("UPDATE People SET Name = ?, AGE = ?, Email = ? WHERE EGN = ?", person.name, person.age, person.email, person.egn);
    }

    /**
     * Deletes person by EGN.
     *
     * @param egn given egn.
     */
    @Override
    public void deleteByEGN(Long egn) {
        dataStore.update("DELETE FROM People WHERE EGN = ?", egn);
    }
}