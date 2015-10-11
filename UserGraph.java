package com.AnsonLongSeabra;

import java.util.ArrayList;


/**
 * The data structure we sill store our users in
 */
public class UserGraph {

    //The users represented in this graph
    public ArrayList<User> users;

    /**
     * Sets up a new UserGraph
     */
    public UserGraph() {

        users = new ArrayList<User>();
    }

    /**
     * Checks to see if a user with the given name is in the graph
     * @param name The name of the user we are checking for
     * @return Whether or not the user is in the graph
     */
    public boolean contains(String name) {

        for (User user : users) {

            if (user.name.equals(name)) {

                return true;

            }
        }

        return false;
    }

    /**
     * Counts the number of infected users in the graph
     * @return The number of infected users in the graph
     */
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

    /**
     * Counts the number of confused users in the graph
     * @return The number of confused users
     */
    public int countConfused() {

        int count = 0;

        for (User user : users) {

            if (user.isConfused()) {

                count++;

            }
        }

        return count;
    }

    /**
     * Counts the number of "classrooms" that are either happy or unhappy. A classroom is defined as a teacher and
     * all of its students. Members of a happy classroom all have the same version of the site, the opposite of which
     * is true for unhappy classrooms.
     * @param complete If complete is true, we are counting happy classrooms, otherwise we are counting unhappy
     * @return The number of classrooms matching the specified criteria
     */
    public int countClassrooms(Boolean complete) {

        int count = 0;

        for (User user : users) {

            boolean happy = true;

            boolean infectionStatus = user.isInfected();

            if (user.students.size() > 0) {

                for (User student: user.students) {

                    if (student.isInfected() != infectionStatus) {

                        happy = false;

                    }
                }

                //We want to count happy classrooms
                if (complete) {

                    if (happy) {

                        count++;
                    }
                } else { //We want to count unhappy classrooms

                    if (!happy) {

                        count++;
                    }
                }
            }

        }

        return count;
    }

    /**
     * Creates a new node and adds it to the graph
     * Pre condition: User of that name is not previously in the graph
     * @param name The name of the user to be created
     */
    public void add(String name) {

        User newUser = new User(name);
        users.add(newUser);

    }

    /**
     * Adds an existing user to the graph
     * @param user The user to add to the graph
     */
    public void add(User user) {

        users.add(user);
    }

    /**
     * Gets a user based on their name
     * @param name The name of the user
     * @return The user object that has the given name
     */
    public User get(String name) {

        for (User user: users) {

            if (user.name.equals(name)) {

                return user;

            }
        }

        return null;
    }

    /**
     * Gets a user based on their index in the graph
     * @param index The index at which the user resides in the list holding the nodes
     * @return The user at the given index
     */
    public User get(int index) {

        return users.get(index);

    }

    /**
     * Removes a user from the graph
     * @param name The name of the user to remove
     */
    public void remove(String name) {

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).name.equals(name)) {

                users.remove(i);

            }
        }
    }

    /**
     * Returns the index of the given user
     * @param user The user whose index we want
     * @return The index of the given user
     */
    public int indexOf(User user) {

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).name.equals(user.name)) {

                return i;

            }
        }

        return -1;
    }

    /**
     * Gets the size of the graph
     * @return the size of the graph
     */
    public int size() {

        return users.size();

    }

    /**
     * Resets all users in the graph to version 0
     */
    public void resetVersions() {

        for (User user : users) {

            user.version = 0;

        }

    }

    /**
     * Connects two users in the graph in a directed relationship.
     * Pre condition: Both users are already in the graph.
     * @param firstName The teacher
     * @param secondName The student
     */
    public void connect(String firstName, String secondName ) {

        User first = null;
        User second = null;

        first = get(firstName);
        second = get(secondName);

        first.students.add(second);
        second.teachers.add(first);
    }


}
