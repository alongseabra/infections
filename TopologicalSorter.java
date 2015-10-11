package com.AnsonLongSeabra;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * A Utility class for topologically sorting
 */
public class TopologicalSorter {

    //The names that we have added to our sorted graph
    private ArrayList<String> markedNames;

    //The graph we will store output in
    private UserGraph reverse;

    /**
     * Topologically sorts a graph
     * @param graph The graph to be sorted
     * @return The graph, sorted, "deepest node (no students)" at index 0
     */
    public UserGraph orderDepthFirst(UserGraph graph) {

        reverse = new UserGraph();
        markedNames = new ArrayList<String>();

        for (User user : graph.users) {

            if (!markedNames.contains(user.name)) {

                depthFirstSearch(graph, user.name);
            }
        }

        return reverse;
    }

    /**
     * Does a depth-first search of the graph, stopping when we've gotten deepest
     * @param graph The graph we are sorting
     * @param name Name of the node to start the DFS from
     */
    public void depthFirstSearch(UserGraph graph, String name) {

        //Mark the current node to signify we have traversed it
        markedNames.add(name);

        //Go further down the graph if we can
        for (User student : graph.get(name).students) {

            if (!markedNames.contains(student.name)) {

                depthFirstSearch(graph, student.name);
            }
        }

        //Add the node to our output list, in sorted order
        reverse.add(graph.get(name));
    }
}
