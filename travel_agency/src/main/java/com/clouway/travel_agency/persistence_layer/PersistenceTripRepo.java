package com.clouway.travel_agency.persistence_layer;

import com.clouway.travel_agency.domain_layer.Trip;
import com.clouway.travel_agency.domain_layer.TripRepo;

import java.sql.Connection;
import java.util.List;

/**
 * Created by zumba on 15.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This is concreate implementation of TripRepo.
 */
public class PersistenceTripRepo implements TripRepo {
    private final DataStore dataStore;

    public PersistenceTripRepo(Connection connection) {
        this.dataStore = new DataStore(connection);
    }

    /**
     * Gets all Trips from database.
     *
     * @return list of trips.
     */
    @Override
    public List<Trip> getAll() {
        return dataStore.fetchRows("SELECT * FROM Trip", resultSet -> new Trip(resultSet.getLong(1), resultSet.getDate(2), resultSet.getDate(3), resultSet.getString(4)));
    }


    /**
     * Adds trip to Database.
     *
     * @param trip that is added.
     */
    @Override
    public void register(Trip trip) {
        dataStore.update("INSERT INTO Trip VALUES (?,?,?,?)", trip.getEgn(), trip.getDateOfArrival(), trip.getDateOfDeparture(), trip.getCity().getName());
    }

    /**
     * Updates trip in database.
     *
     * @param trip updated trip.
     */
    @Override
    public void updateTrip(Trip trip) {
        dataStore.update("UPDATE Trip SET  DateOfArrival= ?, DateOfDeparture = ?, City = ? WHERE EGN = ?", trip.getDateOfArrival(), trip.getDateOfDeparture(), trip.getCity().getName(), trip.getEgn());
    }

    /**
     * Deletes trip from Database by egn.
     *
     * @param egn given for the delete.
     */
    @Override
    public void deleteTripByEGN(Long egn) {
        dataStore.update("DELETE FROM Trip WHERE EGN = ?", egn);
    }

    /**
     * Creates Table from Trip class to Database.
     */
    @Override
    public void createTable() {
        dataStore.update("CREATE TABLE Trip ( EGN BIGINT NOT NULL, DateOfArrival DATE NOT NULL, DateOfDeparture DATE NOT NULL, City VARCHAR(56), FOREIGN KEY (EGN) REFERENCES People(EGN))");
    }

    /**
     * Deletes Table of Trip class from Database.
     */
    @Override
    public void deleteTable() {
        dataStore.update("DROP TABLE IF EXISTS Trip");
    }
}