package com.clouway.travel_agency.persistence_layer;

import com.clouway.travel_agency.domain_layer.Person;
import com.clouway.travel_agency.domain_layer.PersonRepo;
import com.google.common.collect.Lists;

import java.sql.*;
import java.util.List;

/**
 * Created by clouway on 09.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class PersistencePersonRepo implements PersonRepo {
    private final Connection connection;
    private final DataStore dataStore;

    public PersistencePersonRepo(Connection connection) {
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
        List list = Lists.newArrayList();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM People WHERE Name LIKE ?")) {
            statement.setString(1, startsWith + "%");
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
     * Adds person to Database.
     *
     * @param person that is going to be added to Database.
     */
    @Override
    public void register(Person person) {
        dataStore.update("INSERT INTO People VALUES (?,?,?,?)", person.getName(), person.getEgn(), person.getAge(), person.getEmail());
    }

    /**
     * Updates Given Person.
     *
     * @param person person that is has to be updated.
     */
    @Override
    public void updatePerson(Person person) {
        dataStore.update("UPDATE People SET Name = ?, AGE = ?, Email = ? WHERE EGN = ?", person.getName(), person.getAge(), person.getEmail(), person.getEgn());
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

    /**
     * Creates Table for person.
     */
    @Override
    public void createTable() {
        dataStore.update("CREATE TABLE People ( Name VARCHAR(255), EGN BIGINT NOT NULL, AGE INT NOT NULL, Email VARCHAR(255), PRIMARY KEY (EGN))");
    }

    /**
     * Deletes Table from Database.
     */
    @Override
    public void deleteTable() {
        dataStore.update("DROP TABLE IF EXISTS People");
    }
}

