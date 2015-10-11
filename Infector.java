package com.AnsonLongSeabra;

import java.util.Stack;
import java.util.ArrayList;

/**
 * Created by ansonlong-seabra on 10/8/15.
 */
public class Infector {



    public int currentVersion;


    private int infectedCount;


    public Infector() {

        currentVersion = 0;
        infectedCount = 0;

    }

    public void reset() {

        currentVersion = 0;
        infectedCount = 0;
    }

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

    public void limitedInfect (UserGraph graph, int numToInfect, double threshold) {

        TopologicalSorter sorter = new TopologicalSorter();

        graph = sorter.orderDepthFirst(graph);

        for (User user : graph.users) {

            double fractionOfInfected = ((double) infectedCount) / ((double) numToInfect);

            if (infectedCount >= numToInfect || fractionOfInfected >= threshold) {

                break;

            } else {

                if (!user.isInfected()) {

                    for (User teacher : user.teachers) {

                        infectThisAndStudents(teacher);

                    }
                }

            }
        }
    }



    public void infectThisAndAllConnections(User user) {

        Stack<User> toBeInfected = new Stack<User>();

        int currentLength = 0;
        toBeInfected.push(user);

        while(!toBeInfected.isEmpty()) {

            User current = toBeInfected.pop();

            if (current.version == 0) {

                current.version = currentVersion;

                for (User student: current.students) {

                    if (student.version == 0) {

                        toBeInfected.push(student);
                    }
                }

                for (User teacher: current.teachers) {

                    if (teacher.version == 0) {

                        toBeInfected.push(teacher);
                    }
                }
            }

        }

    }
}
