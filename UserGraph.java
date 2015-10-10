package com.AnsonLongSeabra;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by ansonlong-seabra on 10/8/15.
 */
public class UserGraph {

    public ArrayList<User> users;



    public UserGraph() {
        users = new ArrayList<User>();
    }

    public boolean contains(String name) {

        for (User user : users) {
            if (user.name.equals(name)) {
                return true;
            }
        }

        return false;
    }

    public int countInfected()
    {
        int count = 0;
        for (User user : users) {
            if (user.isInfected()) {
                count++;
            }
        }

        return count;
    }

    //a confused user is someone exposed to the wrong version of the site
    public int countConfused() {

        int count = 0;
        for (User user : users) {
            if (user.isConfused()) {
                count++;
            }
        }

        return count;
    }

    public int countClassrooms(Boolean complete) {

        int count = 0;
        for (User user : users) {
            boolean happy = true;
            boolean status = user.isInfected();
            for (User student: user.students) {
                if (student.isInfected() != status) {
                    happy = false;
                }
            }

            if (complete) {
                if (happy) {
                    count++;
                }
            } else {
                if (!happy) {
                    count++;
                }
            }

        }

        return count;
    }



    public void add(String name) {

        User newUser = new User(name);
        users.add(newUser);

    }

    public void add(User u) {
        users.add(u);
    }

    public User get(String name) {

        for (User user: users) {
            if (user.name.equals(name)) {
                return user;
            }
        }

        return null;
    }

    public User get(int index) {
        return users.get(index);
    }

    public void remove(String name) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).name.equals(name)) {
                users.remove(i);
            }
        }
    }

    public int indexOf(User user) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).name.equals(user.name)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return users.size();
    }


    public void resetVersions() {

        for (User user : users) {

            user.version = 0;

        }

    }
    //Connects two users on the graph. Assumes they are both in the graph.
    public void connect(String firstName, String secondName ) {

        User first = null;
        User second = null;

        int count = 0;

        //could optimize this
        first = get(firstName);
        second = get(secondName);

        first.students.add(second);
        second.teachers.add(first);
    }


}
