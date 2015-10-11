package com.AnsonLongSeabra;

import java.util.Stack;

/**
 * A class that will infect a given UserGraph, meaning it will spread a new version of a site to its users
 */
public class Infector {

    //The version of the site we will be giving infected users
    public int currentVersion;

    //The number of users we have infected so far
    public int infectedCount;


    /**
     * Sets up our infector object
     */
    public Infector() {

        currentVersion = 0;
        infectedCount = 0;

    }

    /**
     * Resets version and count of infected users
     */
    public void reset() {

        currentVersion = 0;
        infectedCount = 0;
    }

    /**
     * Infects a user and its ENTIRE connected component.
     * @param graph The graph to infect
     * @param name The name of the user to start the infection with
     */
    public void totalInfect(UserGraph graph, String name) {

        User toInfect = graph.get(name);
        currentVersion++;
        infectThisAndAllConnections(toInfect);


    }

    public void infectThisAndStudents(User user) {

        if (user.version == 0) {

            user.version++;

            infectedCount++;

                for (User student : user.students) {

                    infectThisAndStudents(student);
                }

        }

    }

    /**
     * Attempts to infect close to a given amount of users. Note that for this algorithm,
     * we have interpreted infection to only transfer through the "coaches" relationship.
     * It may be useful to know that this approach also assumes that the user graph is acyclic.
     * For a full rundown on these assumptions, check out the ReadMe
     * @param graph The graph we wish to infect
     * @param numToInfect The number of users we would ideally like to infect
     * @param threshold The acceptable quitting point for infected users, in terms of a fraction of the original
     *                  desired infection count
     */
    public void limitedInfect (UserGraph graph, int numToInfect, double threshold) {

        //We will sort our graph with this
        TopologicalSorter sorter = new TopologicalSorter();


        /**
         * A topological sort will allow us to pick first the users who will transmit
         *the infection to the least amount of people, because they are the lowest
         *precedence in the graph (in the best case they are coaching nobody).
         *Since we have interpreted infection in this case
         *to be only transferred by the "coaches" relation, users at the "bottom" of
         *have a shorter infection chain
         */
        graph = sorter.orderDepthFirst(graph);


        for (User user : graph.users) {

            double fractionOfInfected = ((double) infectedCount) / ((double) numToInfect);

            //Check if the infection count is within the allotted threshold or over our desired number
            if (infectedCount >= numToInfect || fractionOfInfected >= threshold) {

                break;

            } else {

                //If the next user in topological order is not yet infected,
                //we want to infect the user and all its teachers. We infect the teachers
                //to ensure that user every in this user's classroom is infected as well.
                if (!user.isInfected()) {

                    for (User teacher : user.teachers) {

                        //infect this user's teacher's students (its teacher and classmates)
                        infectThisAndStudents(teacher);

                    }
                }

            }
        }
    }

    /**
     * Does most of the work for the totalInfect method, essentially a depth first traversal of the tree
     * @param user The user we want to infect
     */
    public void infectThisAndAllConnections(User user) {

        //Stack of users that need to be infected
        Stack<User> toBeInfected = new Stack<User>();

        toBeInfected.push(user);

        while(!toBeInfected.isEmpty()) {

            User current = toBeInfected.pop();

            //If a user we're visiting isn't infected, infect them
            if (current.version == 0) {

                current.version = currentVersion;

                //Infect all of this user's students
                for (User student: current.students) {

                    if (student.version == 0) {

                        toBeInfected.push(student);
                    }
                }

                //Infect all of this student's teachers
                for (User teacher: current.teachers) {

                    if (teacher.version == 0) {

                        toBeInfected.push(teacher);
                    }
                }
            }

        }

    }
}
