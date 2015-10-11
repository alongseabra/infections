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

    //All users this user is coaching
    public ArrayList<User> students;

    //All users this user is being coached by
    public ArrayList<User> teachers;


    /**
     * Sets up a new user
     * @param aName
     */
    public User(String aName) {

        name = aName;
        version = 0;
        students = new ArrayList<User>();
        teachers = new ArrayList<User>();
    }

    /**
     * Checks user's infection status
     * @return Whether the user is infected or not
     */
    public boolean isInfected() {

        if (version > 0) {
            return true;
        }

        return false;
    }

    /**
     * Checks if a user has no teachers
     * @return Whether the user has no teachers or not
     */
    public boolean hasNoTeachers() {
        if (teachers.size() == 0) {
            return true;
        }

        return false;
    }

    /**
     * A user is "confused" if it has a teacher with a different version than its own
     * or if it has a student with different version than its own.
     * @return Whether or not the user is confused.
     */
    public boolean isConfused() {

        //We only check uninfected users because otherwise we would double count,
        //once from the infected user's perspective and once from the uninfected user's
        //perspective
        if (!isInfected()) {

            for (User student : students) {

                if (student.isInfected()) {

                    return true;
                }
            }
        }

        return false;
    }

}
