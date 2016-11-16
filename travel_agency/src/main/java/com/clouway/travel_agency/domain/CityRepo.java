package com.clouway.travel_agency.domain;

import java.util.List;

/**
 * Created by zumba on 15.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public interface CityRepo {

   List<City> getAll();
   List<City> sortByMoustVisited();

}
