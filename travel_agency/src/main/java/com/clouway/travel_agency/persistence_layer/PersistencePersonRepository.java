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
    public List<Person> peopleStartsWith(String startsWith) {
       return dataStore.fetchRows("SELECT * FROM People WHERE Name LIKE ?",
               resultSet -> new Person(resultSet.getString(1),resultSet.getLong(2),resultSet.getInt(3),resultSet.getString(4)),startsWith+"%");
    }

    /**
     * Gets people by the city that they are from Arrived Date.
     *
     * @param city where they are.
     * @param date Arrived date of person.
     * @return people that are in the same city.
     */
    @Override
    public List<Person> peopleInSameCity(String city, Long date) {
        List list = Lists.newArrayList();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM People INNER JOIN Trip ON People.EGN=Trip.EGN WHERE Trip.City = ? AND Trip.DateOfArrival <= ? AND ? < Trip.DateOfDeparture")) {
            statement.setString(1, city);
            statement.setDate(2, new Date(1290262492000L));
            statement.setDate(3, new Date(1290262492000L));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person(resultSet.getString(1), resultSet.getLong(2), resultSet.getInt(3), resultSet.getString(4));
                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Connection to the database wasn't established");
        }
        return list;
    }

    /**
     * Register person to database.
     *
     * @param name  name of new person.
     * @param egn   egn of new person.
     * @param age   age of the new person.
     * @param email email of new person
     */
    @Override
    public void register(String name, Long egn, int age, String email) {
        dataStore.update("INSERT INTO People VALUES (?,?,?,?)", name, egn, age, email);
    }

    /**
     * Updates existing person from database.
     *
     * @param name  new name of person.
     * @param egn   by eng is been searched.
     * @param age   new age of person.
     * @param email new email of person
     */
    @Override
    public void updatePerson(String name, Long egn, int age, String email) {
        dataStore.update("UPDATE People SET Name = ?, AGE = ?, Email = ? WHERE EGN = ?", name, age, email, egn);
    }

    /**
     * Deletes person by EGN.
     *
     * @param egn given egn.
     */
    @Override
    public void deletePersonByEGN(Long egn) {
        dataStore.update("DELETE FROM People WHERE EGN = ?", egn);
    }
}