package com.clouway.travel_agency.domain_layer;

import javax.xml.crypto.Data;
import java.sql.Date;
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
    List<Person> startsWith(String startsWith);

    /**
     * Gets people by the city that they are from Arrived Date.
     *
     * @param city where they are.
     * @param date Arrived date of person.
     * @return people that are in the same city.
     */
    List<Person> inSameCity(String city, Date date);

    /**
     * Register person to database.
     *
     * @param person that is going to be register.
     */
    void register(Person person);

    /**
     * Updates existing person from database.
     *
     * @param person new updated version of person that is going to be updated.
     */
    void update(Person person);

    /**
     * Deletes person by EGN.
     *
     * @param egn given egn.
     */
    void deleteByEGN(Long egn);
}
