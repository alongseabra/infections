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

    public boolean confused;

    //All users this user is coaching
    public ArrayList<User> students;

    //All users this user is being coached by
    public ArrayList<User> teachers;



    public User(String aName) {

        name = aName;
        version = 0;
        color = 0;
        students = new ArrayList<User>();
        teachers = new ArrayList<User>();
        confused = false;
    }

    public boolean isInfected() {
        if (version > 0) {
            return true;
        }

        return false;
    }

    public boolean hasNoTeachers() {
        if (teachers.size() == 0) {
            return true;
        }

        return false;
    }

    public boolean isConfused() {

        if (!isInfected()) {
            for (User student : students) {
                if (student.isInfected()) {
                    return true;
                }
            }
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
