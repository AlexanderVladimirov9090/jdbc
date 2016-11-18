package com.clouway.travel_agency.domain_layer;

import java.util.List;

/**
 * Created by clouway on 09.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This interfaceis used to provide methods about Select, Update, Alter and Drop queries for Person object.
 */
public interface PersonRepository {

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
     * Register person to database.
     * @param name name of new person.
     * @param egn egn of new person.
     * @param age age of the new person.
     * @param email email of new person
     */
    void register(String name, Long egn , int age, String email);

    /**
     * Updates existing person from database.
     * @param name new name of person.
     * @param egn by eng is been searched.
     * @param age new age of person.
     * @param email new email of person
     */
    void updatePerson(String name, Long egn , int age, String email);

    /**
     * Deletes person by EGN.
     *
     * @param egn given egn.
     */
    void deletePersonByEGN(Long egn);
}
