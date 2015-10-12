package com.AnsonLongSeabra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Utility class for building a UserGraph
 */
public class UserGraphBuilder {

    /**
     * Builds a UserGraph
     * @param fileName the name of the file that the graph information is in
     * @return The built graph
     * @throws FileNotFoundException
     */
    public static UserGraph buildGraph(String fileName) throws FileNotFoundException {

        UserGraph outputGraph = new UserGraph();

        File graphFile = new File(fileName);

        Scanner fileScanner = new Scanner(graphFile);

        System.out.println("Building graph from input file...this may take a few moments with very large files.");

        while (fileScanner.hasNextLine()) {

            //The input file contains a list of newline-sperated edges
            //formatted in the following way: "A" "B" corresponds to A coaches B
            String nextLine = fileScanner.nextLine();
            String[] words = nextLine.split("\"");

            String firstName = words[1];
            String secondName = words [3];

            if (!outputGraph.contains(firstName)) {

                outputGraph.add(firstName);

            }

            if (!outputGraph.contains(secondName)) {

                outputGraph.add(secondName);

            }

            outputGraph.connect(firstName, secondName);


        }

        System.out.println("Graph built!");

        return outputGraph;

    }
}
