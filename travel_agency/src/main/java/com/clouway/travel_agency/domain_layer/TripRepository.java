package com.clouway.travel_agency.domain_layer;

import java.util.List;

/**
 * Created by zumba on 15.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This interfaceis used to provide methods about Select, Update, Alter and Drop queries for Person object.
 */
public interface TripRepository {

    /**
     * Gets all Trips from database.
     *
     * @return list of trips.
     */
    List<Trip> getAll();

    /**
     * Registers trip to database.
     *
     * @param trip new trip to be register in database.
     */
    void register(Trip trip);

    /**
     * Updates existing trip from database.
     *
     * @param trip new trip with changes for trip in the database.
     */
    void updateTrip(Trip trip);

    /**
     * Deletes trip from Database by egn.
     *
     * @param egn given for the delete.
     */
    void delete(Long egn);

    /**
     * Gets name of cities sorted  ascending by visits.
     *
     * @return list of Sorted names of cities.
     */
    List citiesByVisit();
}