package com.clouway.jdbtqueries;
import static org.hamcrest.CoreMatchers.equalTo;
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
/*
    public void setId(int iD) {
        this.iD = iD;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
*/
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

}
