package com.AnsonLongSeabra;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for the TopologicalSorter class
 */
public class TopologicalSorterTest {

    /**
     * Tests if topological ordering is correct
     * @throws Exception
     */
    @Test
    public void testOrderDepthFirst() throws Exception {

        UserGraph graph = new UserGraph();

        for (int i = 0; i < 7; i ++) {
            graph.add(String.valueOf(i));
        }

        graph.connect("0", "1");
        graph.connect("0", "5");
        graph.connect("3", "5");
        graph.connect("6", "0");
        graph.connect("5", "2");
        graph.connect("1", "4");
        graph.connect("0", "2");
        graph.connect("3", "6");
        graph.connect("3", "4");
        graph.connect("6", "4");
        graph.connect("3", "2");

        TopologicalSorter sorter = new TopologicalSorter();

        UserGraph newGraph = sorter.orderDepthFirst(graph);

        //Note that there is more than one way to topsort a graph,
        //this is only one correct way
        assertEquals(graph.size(),newGraph.size());
        assertEquals("4", newGraph.get(0).name);
        assertEquals("1", newGraph.get(1).name);
        assertEquals("2", newGraph.get(2).name);
        assertEquals("5", newGraph.get(3).name);
        assertEquals("0", newGraph.get(4).name);
        assertEquals("6", newGraph.get(5).name);
        assertEquals("3", newGraph.get(6).name);

    }
}