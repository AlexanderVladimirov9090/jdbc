package com.clouway.travel_agency;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by clouway on 09.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class PersonDaoImp implements PersonDao {
    private final DataStore dataStore;

    public PersonDaoImp(Connection connection) {
        this.dataStore = new DataStore(connection);
    }

    @Override
    public List<Person> getPersons() {
        List persons = dataStore.fetchRows("SELECT * FROM  People", resultSet -> {
            try {
                return new Person(resultSet.getString(2), resultSet.getInt(3), resultSet.getLong(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        return persons;
    }

    @Override
    public List<Person> personsStartsWith(String startsWith) {
        if (startsWith.contains(";")) {
            throw new IllegalCharactersException("No sql injection");
        }
        List persons = dataStore.fetchRows("SELECT * FROM  People WHERE Name LIKE '" + startsWith + "%'", resultSet -> {
            try {
                return new Person(resultSet.getString(2), resultSet.getInt(3), resultSet.getLong(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        return persons;
    }

    @Override
    public List<Person> personsByWhereSameCity(String city) {
        return null;
    }

    @Override
    public void addPerosn(Person person) {

    }
}
