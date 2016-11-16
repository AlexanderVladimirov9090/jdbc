package com.clouway.travel_agency.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by clouway on 09.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *  This object represents a record from Database.
 *  Only contains get method to show content pulled from database.
 */
public class Person implements Serializable {
    private final String name;
    private final Long egn;
    private final int age;
    private final String email;

    public Person(String name, Long egn,int age, String email) {
        this.name = name;
        this.egn = egn;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Long getEgn() {
        return egn;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Custom matcher for objects from class Person.
     * @param person compare to.
     * @return true if two object are equal to all its fields.
     */
    public boolean equal(Person person) {
        return Objects.equals(this.egn, person.egn) &&
                Objects.equals(this.name, person.name) &&
                Objects.equals(this.age, person.age) &&
                Objects.equals(this.email, person.email);
    }
}
