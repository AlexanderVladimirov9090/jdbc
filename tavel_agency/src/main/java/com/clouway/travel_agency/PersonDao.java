package com.clouway.travel_agency;

import java.util.List;

/**
 * Created by clouway on 09.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public interface PersonDao {
    List<Person> getPeople();

    List<Person> peopleStartsWith(String startsWith);

    List<Person> peopleInSameCity(String city);

    void addPerson(Person person);
}
