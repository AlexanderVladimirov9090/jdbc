package com.clouway.travel_agency.domain_layer;

import java.util.Objects;

/**
 * Created by clouway on 09.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This object represents a record from Database.
 */
public class Person {
    public final String name;
    public final Long egn;
    public final int age;
    public final String email;

    public Person(String name, Long egn, int age, String email) {
        this.name = name;
        this.egn = egn;
        this.age = age;
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Person.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Person other = (Person) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return !(!Objects.equals(this.name, other.name) && !Objects.equals(this.egn, other.egn) && this.age != other.age && !Objects.equals(this.email, other.email));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.egn != null ? this.egn.hashCode() : 0);
        hash = 53 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 53 * hash + this.age;
        return hash;
    }
}