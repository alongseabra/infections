package com.AnsonLongSeabra;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Tests the UserGraph class
 */
public class UserGraphTest {

    /**
     * Tests adding functionality
     * @throws Exception
     */
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

    /**
     * Tests the contain functionality
     * @throws Exception
     */
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


    /**
     * Tests the connecting of users
     * @throws Exception
     */
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

        assertEquals(2, graph.get("Alex").students.size());
        assertEquals("Jill", graph.get("Alex").students.get(1).name);


    }
}