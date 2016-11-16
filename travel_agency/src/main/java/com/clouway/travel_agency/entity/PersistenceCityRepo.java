package com.clouway.travel_agency.entity;

import com.clouway.travel_agency.domain.City;
import com.clouway.travel_agency.domain.CityRepo;
import com.google.common.collect.Lists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by zumba on 16.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *             Actual implementation of CityRepo interface.
 */
public class PersistenceCityRepo implements CityRepo {
    private final Connection connection;

    public PersistenceCityRepo(Connection connection) {
        this.connection = connection;
    }

    /**
     * Gets all cities whit duplicates.
     * @return list of cities.
     */
    @Override
    public List<City> getAll() {
        List list = Lists.newArrayList();
        try (PreparedStatement statement = connection.prepareStatement("SELECT City FROM Trip")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                City city = new City(resultSet.getString(1));
                list.add(city);
            }
        } catch (SQLException e) {

            throw new IllegalStateException("Connection to the database wasn't established");
        }
        return list;
    }

    /**
     * Return sorted by ascending order list of cities.
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
