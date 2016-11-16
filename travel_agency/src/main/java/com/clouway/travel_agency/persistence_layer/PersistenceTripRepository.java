package com.clouway.travel_agency.persistence_layer;

import com.clouway.travel_agency.domain_layer.Trip;
import com.clouway.travel_agency.domain_layer.TripRepository;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * Created by zumba on 15.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This is concreate implementation of TripRepository.
 */
public class PersistenceTripRepository implements TripRepository {
    private final DataStore dataStore;

    public PersistenceTripRepository(Connection connection) {
        this.dataStore = new DataStore(connection);
    }

    /**
     * Gets all Trips from database.
     *
     * @return list of trips.
     */
    @Override
    public List<Trip> getAll() {
        return dataStore.fetchRows("SELECT * FROM Trip", resultSet -> new Trip(resultSet.getLong(1),
                java.sql.Date.valueOf(resultSet.getDate(2).toString()),
                java.sql.Date.valueOf(resultSet.getDate(3).toString()),
                resultSet.getString(4)));
    }


    /**
     * Registers trip to database.
     *
     * @param trip  new trip that is going to be register in database.
     */
    @Override
    public void register(Trip trip) {
        dataStore.update("INSERT INTO Trip VALUES (?,?,?,?)", trip.egn, trip.dateOfArrival, trip.dateOfDeparture, trip.city);
    }

    /**
     * Updates existing trip in the Database by egn.
     *
     * @param trip is used to update existing trip in database.
     */
    @Override
    public void updateTrip(Trip trip) {
        dataStore.update("UPDATE Trip SET  DateOfArrival= ?, DateOfDeparture = ?, City = ? WHERE EGN = ?", trip.dateOfArrival, trip.dateOfDeparture, trip.city, trip.egn);
    }

    /**
     * Deletes trip from Database by egn.
     *
     * @param egn given for the delete.
     */
    @Override
    public void delete
    (Long egn) {
        dataStore.update("DELETE FROM Trip WHERE EGN = ?", egn);
    }

    /**
     * Gets name of cities
     *
     * @return
     */
    @Override
    public List citiesByVisit() {
        return dataStore.fetchRows("SELECT City, COUNT(*) FROM Trip GROUP BY City ORDER BY 1 DESC", resultSet -> resultSet.getString(1));
    }


}