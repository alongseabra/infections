package com.AnsonLongSeabra;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class KhanAcademyInfections {


    public static UserGraph userGraph;

    public static void main(String[] args) throws FileNotFoundException {

        userGraph = new UserGraph();

        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to Anson's Khan Academy project.");
        System.out.println("Please enter the filename you will be building your user graph with: ");

        String fileName = in.nextLine();


        try {
            buildGraph(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void buildGraph(String fileName) throws FileNotFoundException {

        File graphFile = new File(fileName);

        Scanner fileScanner = new Scanner(graphFile);

        System.out.println("Building graph from input file...this may take a few moments with very large files.");

        while (fileScanner.hasNextLine()) {
            String nextLine = fileScanner.nextLine();
            String[] words = nextLine.split("\"");



            String firstName = words[1];
            String secondName = words [3];


            if (!userGraph.contains(firstName)) {
                userGraph.add(firstName);

            }

            if (!userGraph.contains(secondName)) {
                userGraph.add(secondName);
            }

            userGraph.connect(firstName, secondName);


        }

        System.out.println("Graph built!");

        Infector infector = new Infector();

        infector.limitedInfect(userGraph, 9);

        double confused = userGraph.countConfused();
        double total = userGraph.users.size();
        double happy = userGraph.countClassrooms(true);
        double unhappy = userGraph.countClassrooms(false);

        System.out.println("There are " + total + " users in this graph.");
        System.out.println(confused + " are confused.");
        System.out.println((confused / total) + "% of the graph is confused");
        //System.out.println((total - confused / total) + "% of the graph is not confused");
        System.out.println("There are + " + happy + " happy classrooms in the graph");
        System.out.println("There are + " + unhappy + " unhappy classroms in the graph");
        //System.out.println(userGraph.countInfected() + " users are now infected");



    }



}
