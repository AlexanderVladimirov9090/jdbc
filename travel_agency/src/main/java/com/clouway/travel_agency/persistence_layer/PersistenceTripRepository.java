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
        return dataStore.fetchRows("SELECT * FROM Trip", resultSet -> new Trip(resultSet.getLong(1), resultSet.getDate(2), resultSet.getDate(3), resultSet.getString(4)));
    }


    /**
     * Registers trip to database.
     *
     * @param egn             eng of person from database.
     * @param dateOfArrival   date of arrival in to the city.
     * @param dateOfDeparture date of departure of the city.
     * @param city            destination of the trip.
     */
    @Override
    public void register(Long egn, Date dateOfArrival, Date dateOfDeparture, String city) {
        dataStore.update("INSERT INTO Trip VALUES (?,?,?,?)", egn, dateOfArrival, dateOfDeparture, city);
    }

    /**
     * Updates existing trip in the Database by egn.
     *
     * @param egn             egn of person in database used as foreign key.
     * @param dateOfArrival   data of arrival to be changed.
     * @param dateOfDeparture data of departure to be change.
     * @param city            city to be change.
     */
    @Override
    public void updateTrip(Long egn, Date dateOfArrival, Date dateOfDeparture, String city) {
        dataStore.update("UPDATE Trip SET  DateOfArrival= ?, DateOfDeparture = ?, City = ? WHERE EGN = ?", dateOfArrival, dateOfDeparture, city, egn);
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
     * Gets name of cities
     *
     * @return
     */
    @Override
    public List citiesByVisit() {
        return dataStore.fetchRows("SELECT City, COUNT(*) FROM Trip GROUP BY City ORDER BY 1 DESC", resultSet -> resultSet.getString(1));
    }


}