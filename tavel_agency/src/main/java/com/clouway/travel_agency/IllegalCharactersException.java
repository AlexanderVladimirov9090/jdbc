package com.clouway.travel_agency;

/**
 * Created by clouway on 09.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class IllegalCharactersException extends RuntimeException {
    public IllegalCharactersException(String message) {
        super(message);
    }
}
