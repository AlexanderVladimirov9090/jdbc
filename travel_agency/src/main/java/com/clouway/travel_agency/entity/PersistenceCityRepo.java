package com.clouway.travel_agency.entity;

import com.clouway.travel_agency.domain.City;
import com.clouway.travel_agency.domain.CityRepo;

import java.util.List;

/**
 * Created by zumba on 16.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class PersistenceCityRepo implements CityRepo {
    @Override
    public List<City> getAll() {
        return null;
    }

    @Override
    public List<City> sortByMoustVisited() {
        return null;
    }
}
