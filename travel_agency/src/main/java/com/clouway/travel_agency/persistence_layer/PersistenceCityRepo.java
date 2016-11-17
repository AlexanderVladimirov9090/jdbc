package com.clouway.travel_agency.persistence_layer;

import com.clouway.travel_agency.domain_layer.City;
import com.clouway.travel_agency.domain_layer.CityRepo;

import java.sql.Connection;
import java.util.*;

/**
 * Created by zumba on 16.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         Actual implementation of CityRepo interface.
 */
public class PersistenceCityRepo implements CityRepo {
    private final DataStore dataStore;

    public PersistenceCityRepo(Connection connection) {
        this.dataStore = new DataStore(connection);
    }

    /**
     * Gets all cities whit duplicates.
     *
     * @return list of cities.
     */
    @Override
    public List getAll() {
        return dataStore.fetchRows("SELECT City FROM Trip", resultSet -> new City(resultSet.getString(1)));
    }

    /**
     * Return sorted by ascending order list of cities.
     *
     * @return sorted list of cities.
     */
    @Override
    public List<City> sortByAscending() {
        List<City> fromDb = getAll();
        LinkedHashMap<String, Integer> cities = new LinkedHashMap<>();
        List<City> ascendingList = new LinkedList<>();
        countVisitsRemoveDup(cities, fromDb);
        addToList(cities, ascendingList);
        Collections.sort(ascendingList, (city, city2) -> city.getVisits().compareTo(city2.getVisits()));
        return ascendingList;
    }

    /**
     * Clears duplicates and count visits in  city.
     *
     * @param cities non bup Map of cities.
     * @param sorted list that is going to be sorted.
     */
    private void addToList(LinkedHashMap<String, Integer> cities, List<City> sorted) {
        Set<String> nameOfCities = cities.keySet();
        for (String each : nameOfCities) {
            City city = new City(each);
            city.setVisits(cities.get(each));
            sorted.add(city);
        }
    }

    /**
     * Counts Visits and removes dup of cities.
     *
     * @param cities is used to be add names of cities of free of dup and count visits.
     * @param fromDb content got from Database with dup.
     */
    private void countVisitsRemoveDup(LinkedHashMap<String, Integer> cities, List<City> fromDb) {
        for (City each : fromDb) {
            Integer visits = cities.get(each.getName());
            cities.put(each.getName(), (visits == null) ? 1 : visits + 1);
        }
    }
}