package com.AnsonLongSeabra;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The main class for the Khan Academy project-based interview
 */
public class KhanAcademyInfections {


    //The graph containing our users
    public static UserGraph userGraph;

    //For I/O
    public static Scanner in;

    //The object which will infect our graph
    public static Infector infector;

    public static void main(String[] args) throws FileNotFoundException {

        userGraph = new UserGraph();
        infector = new Infector();

        in = new Scanner(System.in);

        System.out.println("Welcome to Anson's Khan Academy Infection project.");
        System.out.println();
        System.out.println("Please enter the complete file path you will be building your user graph with,\n" +
                "or enter '6', '18', or '51' to use my pre-built graphs with 6, 18, or 51 users: ");

        String fileName = in.nextLine();

        try {

           if (fileName.equals("6")) {

               userGraph = UserGraphBuilder.buildGraph("6nodes.txt");

           } else if (fileName.equals("18")) {

               userGraph = UserGraphBuilder.buildGraph("18nodes.txt");

           } else if (fileName.equals("51")) {

               userGraph = UserGraphBuilder.buildGraph("51nodes.txt");

           }

           else {

               userGraph = UserGraphBuilder.buildGraph(fileName);
           }


        } catch (FileNotFoundException e) {

            System.out.println("Sorry, the file was not found. Exiting.");
            return;
        }



        while (true) {

            //Each time a simulation is run the graph is disinfected
            runSimulation();

            System.out.println("Would you like to try a new scenario? Enter 'Y' to go again or 'N' to quit: " );

            String goAgain = in.nextLine().toLowerCase();

            if (goAgain.equals("y"))  {

                //Sanitize the graph
                userGraph.resetVersions();
                infector.reset();

            } else {

                break;

            }
        }

        System.out.println("We hope you enjoyed Anson's project as much as he enjoyed making it. Goodbye!");

        in.close();
    }

    /**
     * Runs a new infection trial of the graph
     */
    public static void runSimulation() {

        System.out.println("Would you like to perform a total, limited infection, or exact infection? Enter 'T', 'L', or 'E': ");

        String infectionType = in.nextLine().toLowerCase().trim();

        //Total infection
        if (infectionType.equals("t")) {

            System.out.println("Enter the name of the user who you would like to infect first. \n" +
                    "If you are using one of the built in graphs, nodes are named numbers up to size - 1\n" +
                    "Ex: the 6 node graph contains '0'-'5':  ");

            String name = in.nextLine().trim();

            if (userGraph.contains(name)) {

                infector.totalInfect(userGraph, name);
                runAnalysis();

            } else {

                System.out.println("Sorry, the graph does not contain this user. Please restart the program and try again.");
                return;
            }


        } else if (infectionType.equals("l")) { //Limited infection

            System.out.println("Please enter the number of users you would like to try and infect.");

            int toInfect = in.nextInt();
            in.nextLine();

            System.out.println("Please enter a threshold value to stop the search early. For example, if you \n " +
                    "are satisfied with infecting 70% of the number of users you specified above, enter '0.7' ");


            double threshold = in.nextDouble();
            in.nextLine();

            infector.limitedInfect(userGraph, toInfect, threshold);
            runAnalysis();


        } else if (infectionType.equals("e")) {

            System.out.println("Enter the exact number of users you would like to infect \n(this will take a very long" +
                    "time on graphs bigger than 10 or so): ");

            int exact = in.nextInt();
            in.nextLine();

            if (!(exact > userGraph.size()) && !(exact < 0)) {

                infector.exactInfect(userGraph, exact);
                runAnalysis();

            } else {

                System.out.println("Sorry that number is not valid. Please try again.");

            }

        }

        else { //User error

            System.out.println("Sorry, that's not a valid input. Please try again.");
            return;

        }

    }


    /**
     * Computes and prints some facts about the graph that might be nice to know
     */
    public static void runAnalysis() {

        //Confused users are exposed to a different version than their own
        int confused = userGraph.countConfused();

        int total = userGraph.users.size();

        //Happy users are not confused
        int happy = userGraph.countClassrooms(true);

        int unhappy = userGraph.countClassrooms(false);

        int infected = userGraph.countInfected();

        double fracConfused = ((double)confused / (double)total);

        System.out.println();
        System.out.println(infected + " users are now infected");
        userGraph.printInfected();
        System.out.println();
        System.out.println("There are " + total + " users in this graph");
        System.out.println(confused + " are confused");
        System.out.println(String.format("%.2f", fracConfused) + "% of the graph is confused");
        System.out.println("There are " + happy + " happy classrooms in the graph");
        System.out.println("There are " + unhappy + " unhappy classroms in the graph");
    }

}
