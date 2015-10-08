package com.AnsonLongSeabra;

/**
 * Respresents a user of Khan Academy's product.
 */
public class User {

    //The version of the website this user sees
    public int version;

    //The user's name
    public String name;

    public User(String aName) {

        name = aName;
        version = 1;
    }
}
