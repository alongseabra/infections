package com.AnsonLongSeabra;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Created by ansonlong-seabra on 10/8/15.
 */
public class UserGraphTest {


    @Before
    public void setup() throws Exception {

    }

    @Test
    public void testContains() throws Exception {

        UserGraph graph = new UserGraph();

        graph = new UserGraph();
        graph.add("Alex");
        graph.add("Joe");

        assertEquals(true, graph.contains("Alex"));
        assertEquals(true, graph.contains("Joe"));
        assertEquals(false, graph.contains("Jim"));

    }

    @Test
    public void testAdd() throws Exception {

        UserGraph graph = new UserGraph();


        graph = new UserGraph();
        graph.add("Alex");
        graph.add("Joe");

        assertEquals(2, graph.users.size());
        assertEquals(true, graph.users.get(0).name.equals("Alex"));
        assertEquals(true, graph.users.get(1).name.equals("Joe"));


    }

    @Test
    public void testConnect() throws Exception {

        UserGraph graph = new UserGraph();

        graph = new UserGraph();
        graph.add("Alex");
        graph.add("Joe");
        graph.add("Jill");

        graph.connect("Alex", "Joe");
        graph.connect("Alex", "Jill");
        graph.connect("Jill", "Joe");

        //ArrayList<User> alexConnections = graph.get("Alex").connections;
        //ArrayList<User> joeConnections = graph.get("Joe").connections;

//        assertEquals(2, alexConnections.size());
//        assertEquals(2, joeConnections.size());
//
//        assertEquals("Joe", alexConnections.get(0).name);
//        assertEquals("Alex", joeConnections.get(0).name);


    }
}