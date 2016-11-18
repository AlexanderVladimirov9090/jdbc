package com.clouway.travel_agency.domain_layer;

import java.sql.Date;
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
     * @param egn             eng of person from database.
     * @param dateOfArrival   date of arrival in to the city.
     * @param dateOfDeparture date of departure of the city.
     * @param city            destination of the trip.
     */
    void register(Long egn, Date dateOfArrival, Date dateOfDeparture, String city);

    /**
     * Updates existing trip from database.
     *
     * @param egn             eng of person from database used as a foreign key.
     * @param dateOfArrival   date of arrival in to the city.
     * @param dateOfDeparture date of departure of the city.
     * @param city            destination of the trip.
     */
    void updateTrip(Long egn, Date dateOfArrival, Date dateOfDeparture, String city);

    /**
     * Deletes trip from Database by egn.
     *
     * @param egn given for the delete.
     */
    void deleteTripByEGN(Long egn);

    /**
     * Gets name of cities sorted  ascending by visits.
     * @return list of Sorted names of cities.
     */
    List citiesByVisit();
}