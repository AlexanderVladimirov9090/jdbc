package com.clouway.jdbtqueries;

/**
 * Created by clouway on 02.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class User {
    private final int iD;
    private final String userName;
    private final String password;
    private final String email;

    public User(int iD, String userName, String password, String email) {
        this.iD = iD;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public int getId() { return iD; }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
