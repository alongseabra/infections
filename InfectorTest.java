package com.AnsonLongSeabra;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ansonlong-seabra on 10/8/15.
 */
public class InfectorTest {

    public UserGraph graph;

    @Before
    public void setup() throws Exception {

        graph = new UserGraph();
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.add("D");
        graph.add("E");
        graph.add("F");
        graph.add("G");

        graph.connect("A", "B");
        graph.connect("C", "D");
        graph.connect("E", "F");
        graph.connect("F", "G");
        graph.connect("E", "G");


    }

    @Test
    public void testGetNumberOfSubgraphs() throws Exception {

        Infector infector = new Infector();
        assertEquals(3, infector.getNumberOfSubgraphs(graph));

    }

    @Test
    public void testColorUser() throws Exception {

    }
}