package com.clouway.travel_agency.domain;

/**
 * Created by zumba on 15.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *        This class is used to get record from Database.
 */
public class City {
    private final String name;
    private Integer visits;
    public City(String name) {
        this.name = name;

    }

    /**
     * Sets visits to city.
     * @param visits counted visits to city.
     */
    public void setVisits(Integer visits){
        this.visits=visits;
    }

    public String getName() {
        return name;
    }
    public Integer getVisits(){
        return visits;
    }
}
