package com.AnsonLongSeabra;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class KhanAcademyInfections {


    public static UserGraph userGraph;
    public static Scanner in;
    public static Infector infector;

    public static void main(String[] args) throws FileNotFoundException {

        userGraph = new UserGraph();
        infector = new Infector();

        in = new Scanner(System.in);

        System.out.println("Welcome to Anson's Khan Academy project.");
        System.out.println("Please enter the filename you will be building your user graph with: ");

        String fileName = in.nextLine();

        try {
            buildGraph(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




        runSimulation();


        System.out.println("Would you like to try a new scenario? Enter 'Y' to go again or 'N' to quit." );

        String goAgain = in.nextLine().toLowerCase().trim();

        if (goAgain.equals("n"))  {


        } else {

            userGraph.resetVersions();
            infector.reset();
            runSimulation();
        }



        System.out.println("We hope you enjoyed Anson's project as much as he enjoyed making it. Goodbye!");

    }

    public static void runSimulation() {

        System.out.println("Would you like to perform a total infection of a limited infection? Enter 'T' or 'L': ");

        String infectionType = in.nextLine().toLowerCase().trim();


        if (infectionType.equals("t")) {

            System.out.println("Enter the name of the user who you would like to infect first: ");

            String name = in.nextLine().trim();

            if (userGraph.contains(name)) {

                infector.totalInfect(userGraph, name);

            } else {

                System.out.println("Sorry, the graph does not contain this user. Please restart the program and try again.");
            }


        } else if (infectionType.equals("l")) {

            System.out.println("Please enter the number of users you would like to try and infect.");

            int toInfect = in.nextInt();

            infector.limitedInfect(userGraph, toInfect);


        }

        runAnalysis();
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




    }

    public static void runAnalysis() {

        double confused = userGraph.countConfused();
        double total = userGraph.users.size();
        double happy = userGraph.countClassrooms(true);
        double unhappy = userGraph.countClassrooms(false);

        double infected = userGraph.countInfected();


        System.out.println(userGraph.countInfected() + " users are now infected");
        System.out.println("There are " + total + " users in this graph.");
        System.out.println(confused + " are confused.");
        System.out.println((confused / total) + "% of the graph is confused");
        //System.out.println((total - confused / total) + "% of the graph is not confused");
        System.out.println("There are + " + happy + " happy classrooms in the graph");
        System.out.println("There are + " + unhappy + " unhappy classroms in the graph");
    }



}
