package com.clouway.jdbcqueries;

import java.util.Objects;

/**
 * Created by clouway on 02.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class User {
    private int iD;
    private String userName;
    private String password;
    private String email;

    public User(int iD, String userName, String password, String email) {
        this.iD = iD;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return this.iD;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean equal(User user){
        return  Objects.equals(this.iD ,user.iD) &&
                Objects.equals(this.userName, user.userName) &&
                Objects.equals(this.password,user.password) &&
                Objects.equals(this.email , user.email);
    }
}
