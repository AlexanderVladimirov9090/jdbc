package com.clouway.travel_agency.entity;

import com.clouway.travel_agency.domain.Person;
import com.clouway.travel_agency.domain.PersonRepo;
import com.google.common.collect.Lists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by clouway on 09.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class PersistencePersonRepo implements PersonRepo {

    private final Connection connection;

    public PersistencePersonRepo(Connection connection) {
        this.connection = connection;
    }

    /**
     * Gets People from table People from Database.
     *
     * @return list of people.
     */
    @Override
    public List<Person> getAll() {
        List list = Lists.newArrayList();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM People")) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM People");
            while (resultSet.next()) {
                Person person = new Person(resultSet.getString(1), resultSet.getLong(2), resultSet.getInt(3), resultSet.getString(4));
                list.add(person);
            }
        } catch (SQLException e) {

            throw new IllegalStateException("Connection to the database wasn't established");
        }
        return list;
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
    public List<Person> peopleInSameCity(String city, String date) {
        List list = Lists.newArrayList();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM People INNER JOIN Trip ON People.EGN=Trip.EGN" +
                " WHERE Trip.City=? AND Trip.DateOfArrival = ?")) {
            statement.setString(1, city);
            statement.setString(2, date);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person(resultSet.getString(1), resultSet.getLong(2), resultSet.getInt(3), resultSet.getString(4));
                list.add(person);
            }
        } catch (SQLException e) {
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
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO People VALUES (?,?,?,?)")) {
            statement.setString(1, person.getName());
            statement.setLong(2, person.getEgn());
            statement.setInt(3, person.getAge());
            statement.setString(4, person.getEmail());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Connection to the database wasn't established");
        }
    }

    /**
     * Updates Given Person.
     *
     * @param person person that is has to be updated.
     */
    @Override
    public void updatePerson(Person person) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE People SET Name = ?, AGE = ?, Email = ? WHERE EGN = ?")) {
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());
            statement.setLong(4, person.getEgn());
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("Connection to the database wasn't established");
        }
    }

    /**
     * Deletes person by EGN.
     *
     * @param egn given egn.
     */
    @Override
    public void deletePersonByEGN(Long egn) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM People WHERE EGN = ?")) {
            statement.setLong(1, egn);
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("Connection to the database wasn't established");
        }
    }

    /**
     * Creates Table for person.
     */
    @Override
    public void createTable() {
        try (PreparedStatement statement = connection.prepareStatement("CREATE TABLE People " +
                "( Name VARCHAR(255)," +
                "EGN BIGINT NOT NULL," +
                "AGE INT NOT NULL," +
                "Email VARCHAR(255)," +
                "PRIMARY KEY (EGN))")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Connection to the database wasn't established");
        }
    }

    /**
     * Deletes Table from Database.
     */
    @Override
    public void deleteTable() {
        try (PreparedStatement statement = connection.prepareStatement("DROP TABLE IF EXISTS People")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Connection to the database wasn't established");
        }
    }
}

