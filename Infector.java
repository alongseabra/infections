package com.AnsonLongSeabra;

import java.util.Stack;
import java.util.ArrayList;

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

    public Infector() {

        currentColor = 0;
        distinctSubgraphs = 0;
        subgraphUsers = new ArrayList<User>();
        lengths = new ArrayList<Integer>();

    }

    public void totalInfect(UserGraph graph, String name) {

        User toInfect = graph.get(name);
        currentColor++;
        colorUser(toInfect);


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
                for (User connection: current.connections) {
                    if (connection.color == 0) {
                        toBeColored.push(connection);
                    }
                }
            }

        }

        System.out.println("The length of a distinct subgraph is " + currentLength);


    }
}
