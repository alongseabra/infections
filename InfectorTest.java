package com.AnsonLongSeabra;

import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import static org.junit.Assert.*;

/**
 * A class to test the Infector class
 */
public class InfectorTest {

    public UserGraph graph;
    public Infector infector;

    /**
     * Builds graph before tests
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {

        graph = UserGraphBuilder.buildGraph("51nodes.txt");
        infector = new Infector();

    }


    /**
     * Tests limitedInfection algorithm
     * @throws Exception
     */
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

    /**
     * Un-infects all users and sets the version of infector back to 0
     */
    public void reset() {

        infector.reset();
        graph.resetVersions();
    }

    /**
     * Tests the totalInfection algorithm
     * @throws FileNotFoundException
     */
    @Test
    public void testTotalInfection() throws FileNotFoundException{

        //first connected component has 31 users
        for (int i = 0; i < 31; i++) {

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


    }

    /**
     * Tests the exactInfection algorithm
     * @throws FileNotFoundException
     */
    @Test
    public void testExactInfection() throws FileNotFoundException{

        graph = UserGraphBuilder.buildGraph("6nodes.txt");
        infector = new Infector();

        for (int i = 1; i < graph.size(); i++) {

            infector.exactInfect(graph, i);
            assertEquals(i, graph.countInfected());

            graph.resetVersions();
            infector.reset();

        }

    }
}