package com.clouway.travel_agency.domain;


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

    public Long getEgn() {
        return egn;
    }

    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public City getCity() {
        return city;
    }

    public boolean equal(Trip trip) {
        return Objects.equals(this.egn, trip.egn) &&
                Objects.equals(this.dateOfArrival.toString(), trip.dateOfArrival.toString())&&
                Objects.equals(this.dateOfDeparture.toString(), trip.dateOfDeparture.toString()) &&
                Objects.equals(this.city.getName(), trip.city.getName());
    }
}