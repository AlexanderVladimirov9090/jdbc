package com.clouway.travel_agency.entity;

import com.clouway.travel_agency.domain.Trip;
import com.clouway.travel_agency.domain.TripRepo;
import com.google.common.collect.Lists;

import java.sql.*;
import java.util.*;

/**
 * Created by zumba on 15.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *   This is concreate implementation of TripRepo.
 */
public class PersistenceTripRepo implements TripRepo {
    private final Connection connection;

    public PersistenceTripRepo(Connection connection) {
        this.connection = connection;
    }

    /**
     * Gets all Trips from database.
     *
     * @return list of trips.
     */
    @Override
    public List<Trip> getAll() {
        List list = Lists.newArrayList();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Trip")) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Trip");
            while (resultSet.next()) {
                Trip trip = new Trip(resultSet.getLong(1), resultSet.getDate(2), resultSet.getDate(3), resultSet.getString(4));
                list.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Connection to the database wasn't established");
        }
        return list;
    }
    /**
     * Gets sorted trips by number of people in cities.
     *
     * @return list of sorted trips.
     */
    @Override
    public List<Trip> sortByPeopleInCity() {
        return null;
    }

    /**
     * Adds trip to Database.
     *
     * @param trip that is added.
     */
    @Override
    public void register(Trip trip) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Trip VALUES (?,?,?,?)")) {

            statement.setLong(1, trip.getEgn());
            statement.setDate(2, trip.getDateOfArrival());
            statement.setDate(3, trip.getDateOfDeparture());
            statement.setString(4, trip.getCity().getName());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Connection to the database wasn't established");
        }
    }

    /**
     * Updates trip in database.
     *
     * @param trip updated trip.
     */
    @Override
    public void updateTrip(Trip trip) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE Trip SET  DateOfArrival= ?, DateOfDeparture = ?, Email = ? WHERE EGN = ?")) {
            statement.setDate(1, trip.getDateOfArrival());
            statement.setDate(2, trip.getDateOfDeparture());
            statement.setString(3,trip.getCity().getName());
            statement.setLong(4, trip.getEgn());
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("Connection to the database wasn't established");
        }
    }

    /**
     * Deletes trip from Database by egn.
     *
     * @param egn given for the delete.
     */
    @Override
    public void deleteTripByEGN(Long egn) {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM Trip WHERE EGN = ?")) {
            statement.setLong(1,egn);
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("Connection to the database wasn't established");
        }
    }

    /**
     * Creates Table from Trip class to Database.
     */
    @Override
    public void createTable() {
        try(PreparedStatement statement = connection.prepareStatement("CREATE TABLE Trip " +
                "( EGN BIGINT NOT NULL," +
                "DateOfArrival DATE NOT NULL," +
                "DateOfDeparture DATE NOT NULL," +
                "City VARCHAR(56)," +
                "FOREIGN KEY (EGN) REFERENCES People(EGN))")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Connection to the database wasn't established");
        }
    }

    /**
     * Deletes Table of Trip class from Database.
     */
    @Override
    public void deleteTable() {
        try(PreparedStatement statement = connection.prepareStatement("DROP TABLE IF EXISTS Trip")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Connection to the database wasn't established");
        }
    }

}
