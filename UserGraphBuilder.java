package com.AnsonLongSeabra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by ansonlong-seabra on 10/10/15.
 */
public class UserGraphBuilder {

    public static UserGraph buildGraph(String fileName) throws FileNotFoundException {

        UserGraph outputGraph = new UserGraph();

        File graphFile = new File(fileName);

        Scanner fileScanner = new Scanner(graphFile);

        System.out.println("Building graph from input file...this may take a few moments with very large files.");

        while (fileScanner.hasNextLine()) {
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
