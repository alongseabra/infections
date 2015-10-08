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

    public void add(String name) {

        User newUser = new User(name);
        users.add(newUser);

    }

    public User get(String name) {

        for (User user: users) {
            if (user.name.equals(name)) {
                return user;
            }
        }

        return null;
    }

    //Connects two users on the graph. Assumes they are both in the graph.
    public void connect(String firstName, String secondName ) {

        User first = null;
        User second = null;

        int count = 0;

        //could optimize this
        first = get(firstName);
        second = get(secondName);

        first.connections.add(second);
        second.connections.add(first);
    }


}
