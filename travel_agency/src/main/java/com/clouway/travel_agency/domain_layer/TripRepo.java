package com.clouway.travel_agency.domain_layer;

import java.util.List;

/**
 * Created by zumba on 15.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This interfaceis used to provide methods about Select, Update, Alter and Drop queries for Person object.
 */
public interface TripRepo {

    /**
     * Gets all Trips from database.
     *
     * @return list of trips.
     */
    List<Trip> getAll();

    /**
     * Adds trip to Database.
     *
     * @param trip that is added.
     */
    void register(Trip trip);

    /**
     * Updates trip in database.
     *
     * @param trip updated trip.
     */
    void updateTrip(Trip trip);

    /**
     * Deletes trip from Database by egn.
     *
     * @param egn given for the delete.
     */
    void deleteTripByEGN(Long egn);

    /**
     * Creates Table from Trip class to Database.
     */
    void createTable();

    /**
     * Deletes Table of Trip class from Database.
     */
    void deleteTable();
}
