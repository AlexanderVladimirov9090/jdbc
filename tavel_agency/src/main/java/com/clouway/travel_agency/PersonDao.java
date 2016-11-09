package com.clouway.travel_agency;

import java.util.List;

/**
 * Created by clouway on 09.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public interface PersonDao {
    List<Person> getPersons();

    List<Person> personsStartsWith(String startsWith);

    List<Person> personsByWhereSameCity(String city);

    void addPerosn(Person person);
}
