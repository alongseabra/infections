package com.AnsonLongSeabra;

/**
 * Tests the components of Khan Academy infection project
 */


public class TestSuite {

    public static void main(String[] args) throws Exception {


        TopologicalSorterTest topTest = new TopologicalSorterTest();
        topTest.testOrderDepthFirst();
        System.out.println("Topological Sorter passed all tests!");

        UserGraphTest graphTest = new UserGraphTest();
        graphTest.testAdd();
        graphTest.testContains();
        graphTest.testConnect();
        System.out.println("User graph passed all tests!");

        InfectorTest infectorTest = new InfectorTest();
        infectorTest.setup();
        infectorTest.testLimitedInfection();
        infectorTest.testTotalInfection();
        infectorTest.testExactInfection();

        System.out.println();
        System.out.println();
        System.out.println("Infector passed all tests!");




    }

}
