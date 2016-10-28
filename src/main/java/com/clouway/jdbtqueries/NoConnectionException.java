package com.clouway.jdbtqueries;

/**
 * Created by clouway on 26.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class NoConnectionException extends Throwable {
    public NoConnectionException(String message) {
        super(message);
    }
}
