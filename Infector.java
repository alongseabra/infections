package com.AnsonLongSeabra;

import java.util.Stack;
import java.util.ArrayList;
import com.AnsonLongSeabra.TopologicalSorter;

/**
 * Created by ansonlong-seabra on 10/8/15.
 */
public class Infector {


    //the number of unconnected subgraphs
    public int distinctSubgraphs;
    public int currentColor;

    //"Entry" users to each of the distinct subgraphs
    public ArrayList<User> subgraphUsers;
    public ArrayList<Integer> lengths;

    private int infectedCount;
    private int desiredInfectionCount;

    public Infector() {

        currentColor = 0;
        distinctSubgraphs = 0;
        subgraphUsers = new ArrayList<User>();
        lengths = new ArrayList<Integer>();
        infectedCount = 0;
        desiredInfectionCount = 0;

    }

    public void totalInfect(UserGraph graph, String name) {

        User toInfect = graph.get(name);
        currentColor++;
        colorUser(toInfect);


    }

    public void infect(User user) {

        if (user.color == 0) {
            System.out.println("Infecting " + user.name);
            user.color++;
            infectedCount++;

               // System.out.println("infected count is " + infectedCount);
                for (User student : user.students) {
                    infect(student);
                }

        }

    }

    public void limitedInfect (UserGraph graph, int numToInfect) {
        TopologicalSorter sorter = new TopologicalSorter();

        //postorder (node with least precedence is first)
        desiredInfectionCount = numToInfect;
        graph = sorter.orderDepthFirst(graph);
        for (User user : graph.users) {
            //System.out.println("inspecting " + user.name);
            if (infectedCount >= desiredInfectionCount) {
                System.out.println("breaking");
                break;
            } else {
               // System.out.println("teacher count is " + user.teachers.size());
                //System.out.println("student count is " + user.students.size());

                for (User teacher : user.teachers) {
                    infect(teacher);
                }
            }
        }
        System.out.println(infectedCount + " infected");
    }



    public int getNumberOfSubgraphs(UserGraph graph) {

        for (User user : graph.users) {
            if (user.color == 0) {
                currentColor++;
                subgraphUsers.add(user);
                colorUser(user);
            }
        }

        System.out.println("This graph has " + (subgraphUsers.size()) + " distinct subgroups");
        return currentColor;
    }

    //flood fill algorithm
    public void colorUser(User user) {

        Stack<User> toBeColored = new Stack<User>();

        int currentLength = 0;
        toBeColored.push(user);
        while(!toBeColored.isEmpty()) {
            User current = toBeColored.pop();
           // System.out.println("Coloring " + current.name);
            if (current.color == 0) {
                currentLength++;
                current.color = currentColor;
                for (User student: current.students) {
                    if (student.color == 0) {
                        toBeColored.push(student);
                    }
                }
                for (User teacher: current.teachers) {
                    if (teacher.color == 0) {
                        toBeColored.push(teacher);
                    }
                }
            }

        }

        System.out.println("The length of a distinct subgraph is " + currentLength);


    }
}
