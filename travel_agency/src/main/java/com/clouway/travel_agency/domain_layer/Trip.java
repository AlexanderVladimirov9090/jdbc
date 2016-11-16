package com.clouway.travel_agency.domain_layer;


import java.util.Date;
import java.util.Objects;

/**
 * Created by zumba on 15.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This object represents a record from Database.
 *         Only contains get method to show content pulled from database.
 */
public class Trip {
    public final Long egn;
    public final Date dateOfArrival;
    public final Date dateOfDeparture;
    public final String city;

    public Trip(Long egn, Date dateOfArrival, Date dateOfDeparture, String city) {
        this.egn = egn;
        this.dateOfArrival = dateOfArrival;
        this.dateOfDeparture = dateOfDeparture;
        this.city = city;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (!Trip.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Trip other = (Trip) obj;

        if ((this.egn == null) ? (other.egn != null) : !this.egn.equals(other.egn)) {
            return false;
        }
        return !(this.egn != other.egn && !Objects.equals(this.dateOfArrival.toString(), other.dateOfArrival.toString()) && !Objects.equals(this.dateOfDeparture.toString(), other.dateOfDeparture.toString()) && !Objects.equals(this.city, other.city));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.egn != null ? this.egn.hashCode() : 0);
        hash = 53 * hash + (this.dateOfArrival.toString() != null ? this.dateOfDeparture.hashCode() : 0);
        hash = 53 * hash + (this.dateOfDeparture.toString() != null ? this.dateOfDeparture.hashCode() : 0);
        hash = 53 * hash + (this.city != null ? this.city.hashCode() : 0);
        return hash;
    }
}