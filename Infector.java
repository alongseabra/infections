package com.AnsonLongSeabra;

import java.util.Stack;

/**
 * Created by ansonlong-seabra on 10/8/15.
 */
public class Infector {


    public int distinctSubgraphs;
    public int currentColor;

    public Infector() {

        currentColor = 0;
        distinctSubgraphs = 0;


    }


    public int getNumberOfSubgraphs(UserGraph graph) {

        for (User user : graph.users) {
            if (user.color == 0) {
                currentColor++;
                colorUser(user);
            }
        }

        System.out.println("This graph has " + (currentColor) + " distinct subgroups");
        return currentColor;
    }

    //flood fill algorithm
    public void colorUser(User user) {

        Stack<User> toBeColored = new Stack<User>();

        toBeColored.push(user);
        while(!toBeColored.isEmpty()) {
            User current = toBeColored.pop();
            //System.out.println("Coloring " + current.name);
            current.color = currentColor;
            for (User connection: current.connections) {
                if (connection.color == 0) {
                    toBeColored.push(connection);
                }
            }
        }


    }
}
