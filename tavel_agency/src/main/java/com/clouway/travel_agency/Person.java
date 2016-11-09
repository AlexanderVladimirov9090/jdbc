package com.clouway.travel_agency;

import java.util.Objects;

/**
 * Created by clouway on 09.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class Person {
    private final Long egn;
    private final String name;
    private final int age;

    public Person(String name, int age,Long egn) {
        this.name = name;
        this.age = age;
        this.egn = egn;
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

    public boolean equal(Person person) {
        return Objects.equals(this.egn, person.egn) &&
                Objects.equals(this.name, person.name) &&
                Objects.equals(this.age, person.age);
    }
}
