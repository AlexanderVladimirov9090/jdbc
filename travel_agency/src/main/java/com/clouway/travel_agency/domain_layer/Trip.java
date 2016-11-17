package com.clouway.travel_agency.domain_layer;


import java.sql.Date;
import java.util.Objects;

/**
 * Created by zumba on 15.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *  This object represents a record from Database.
 *  Only contains get method to show content pulled from database.
 */
public class Trip {
    private final Long egn;
    private final Date dateOfArrival;
    private final Date dateOfDeparture;
    private final City city;

    public Trip(Long egn, Date dateOfArrival, Date dateOfDeparture, String city) {
        this.egn = egn;
        this.dateOfArrival = dateOfArrival;
        this.dateOfDeparture = dateOfDeparture;
        this.city = new City(city);
    }

    /**
     * Gets egn of person of that trip.
     * @return egn.
     */
    public Long getEgn() {
        return egn;
    }

    /**
     * Gets date of arrival of the trip.
     * @return date of arrival.
     */
    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    /**
     * Gets date of departure of the trip.
     * @return date of departure.
     */
    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    /**
     * Gets city of the trip/
     * @return city.
     */
    public City getCity() {
        return city;
    }
    /**
     * Custom matcher for objects from class Trip.
     * @param trip compare to.
     * @return true if two object are equal to all its fields.
     */
    public boolean equal(Trip trip) {
        return Objects.equals(this.egn, trip.egn) &&
                Objects.equals(this.dateOfArrival.toString(), trip.dateOfArrival.toString())&&
                Objects.equals(this.dateOfDeparture.toString(), trip.dateOfDeparture.toString()) &&
                Objects.equals(this.city.getName(), trip.city.getName());
    }
}