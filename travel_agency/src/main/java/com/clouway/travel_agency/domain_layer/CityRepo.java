package com.clouway.travel_agency.domain_layer;

import java.util.List;

/**
 * Created by zumba on 15.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *             This interface is used to get records from Database.
 */
public interface  CityRepo {

   /**
    * Gets all cities whit duplicates.
    * @return list of cities.
    */
   List getAll();

   /**
    * Return sorted by ascending order list of cities.
    * @return sorted list of cities.
    */
   List<City> sortByAscending();

}
