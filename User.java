package com.AnsonLongSeabra;

import java.util.ArrayList;
/**
 * Respresents a user of Khan Academy's product.
 */
public class User {

    //The version of the website this user sees
    public int version;

    //The user's name
    public String name;

    //used for identifying if a user is part of a subgraph
    public int color;

    //All users this user coaches or is coached by
    public ArrayList<User> connections;

    public User(String aName) {

        name = aName;
        version = 1;
        color = 0;
        connections = new ArrayList<User>();
    }

    public boolean isInfected() {
        if (color > 0) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object other){

        if (other == null) {

            return false;
        }
        if (other == this) {

            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User otherUser = (User)other;
        if (otherUser.name.equals(name)) {
            return true;
        }

        return false;
    }
}
