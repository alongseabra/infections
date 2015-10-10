package com.AnsonLongSeabra;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Created by ansonlong-seabra on 10/9/15.
 */
public class TopologicalSorter {

    private ArrayList<String> markedNames;
    private UserGraph reverse;

    public UserGraph orderDepthFirst(UserGraph graph) {


        reverse = new UserGraph();
        markedNames = new ArrayList<String>();
        for (User user : graph.users) {

            if (!markedNames.contains(user.name)) {
                //System.out.println(user.name);
                depthFirstSearch(graph, user.name);
            }
        }




        return reverse;
    }

    public void depthFirstSearch(UserGraph graph, String name) {

        //System.out.println("Marking " + name);
        markedNames.add(name);
        //System.out.println("current node is " + graph.get(i).name);
        for (User student : graph.get(name).students) {
            //System.out.println("About to go to "+ student.name);
            //int indexOfStudent = graph.indexOf(student);
            if (!markedNames.contains(student.name)) {
              //  System.out.println("searching " + student.name);
                depthFirstSearch(graph, student.name);
            }
        }
        //System.out.println("Adding " + name);
        reverse.add(graph.get(name));
    }
}
