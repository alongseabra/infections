package com.AnsonLongSeabra;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by ansonlong-seabra on 10/8/15.
 */
public class InfectorTest {

    public UserGraph graph;
    public Infector infector;

    @Before
    public void setup() throws Exception {

        graph = UserGraphBuilder.buildGraph("51nodes.txt");
        infector = new Infector();


    }


    @Test
    public void testLimitedInfection() throws Exception {


        infector.limitedInfect(graph, 14, 1.0);
        assertEquals(17, graph.countInfected());

        reset();

        infector.limitedInfect(graph, 14, 0.5);
        assertEquals(9, graph.countInfected());

        reset();

        infector.limitedInfect(graph, 2, 1.0);
        assertEquals(9, graph.countInfected());

        reset();

        infector.limitedInfect(graph, 51, 1.0);
        assertEquals(46, graph.countInfected());

        reset();

        infector.limitedInfect(graph, 1, 1.0);
        assertEquals(9, graph.countInfected());

        reset();

        infector.limitedInfect(graph, 25, 0.1);
        assertEquals(9, graph.countInfected());


    }

    public void reset() {

        infector.reset();
        graph.resetVersions();
    }

    @Test
    public void testTotalInfection() throws FileNotFoundException{

        //first connected component has 31 users
        for (int i = 1; i < 31; i++) {

            String name = Integer.toString(i).trim();
            infector.totalInfect(graph,  name);
            assertEquals(31, graph.countInfected());
            reset();
        }

        //second component has 10 connected users
        for (int j = 31; j < 41; j++) {

            String name = Integer.toString(j).trim();
            infector.totalInfect(graph,  name);
            assertEquals(10, graph.countInfected());
            reset();
        }

        //third component has 10 connected users
        for (int k = 41; k < 51; k++) {

            String name = Integer.toString(k).trim();
            infector.totalInfect(graph,  name);
            assertEquals(10, graph.countInfected());
            reset();
        }

        //node 51 is an odball, in the first component
        infector.totalInfect(graph, "51");
        assertEquals(31, graph.countInfected());
    }
}