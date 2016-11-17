package com.clouway.travel_agency.domain_layer;

import java.util.List;

/**
 * Created by clouway on 09.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This interfaceis used to provide methods about Select, Update, Alter and Drop queries for Person object.
 */
public interface PersonRepo {

    /**
     * Gets People from table People from Database.
     *
     * @return list of people.
     */
    List<Person> getAll();

    /**
     * Gets people that Starts with some Characters.
     *
     * @param startsWith starting pattern.
     * @return
     */
    List<Person> peopleStartsWith(String startsWith);

    /**
     * Gets people by the city that they are from Arrived Date.
     *
     * @param city where they are.
     * @param date Arrived date of person.
     * @return people that are in the same city.
     */
    List<Person> peopleInSameCity(String city, Long date);

    /**
     * Adds person to Database.
     *
     * @param person that is going to be added to Database.
     */
    void register(Person person);

    /**
     * Updates Given Person.
     *
     * @param person person that is has to be updated.
     */
    void updatePerson(Person person);

    /**
     * Deletes person by EGN.
     *
     * @param egn given egn.
     */
    void deletePersonByEGN(Long egn);

    /**
     * Creates Table for person.
     */
    void createTable();

    /**
     * Deletes Table from Database.
     */
    void deleteTable();
}
