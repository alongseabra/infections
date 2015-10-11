package com.AnsonLongSeabra;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class KhanAcademyInfections {


    public static UserGraph userGraph;
    public static Scanner in;
    public static Infector infector;

    public static void main(String[] args) throws FileNotFoundException {

        userGraph = new UserGraph();
        infector = new Infector();

        in = new Scanner(System.in);

        System.out.println("Welcome to Anson's Khan Academy Infection project.");
        System.out.println("Today we will be infecting some of our users.");
        System.out.println("Sadly, this is not a zombie simulator, rather, our infection will spread " +
                "a certain version of Khan Academy's site. ");
        System.out.println("Please enter the complete file path you will be building your user graph with: ");

        String fileName = in.nextLine();

        try {

           userGraph = UserGraphBuilder.buildGraph(fileName);

        } catch (FileNotFoundException e) {

            System.out.println("Sorry, the file was not found. Exiting.");
            return;
        }



        while (true) {

            runSimulation();

            System.out.println("Would you like to try a new scenario? Enter 'Y' to go again or 'N' to quit: " );

            String goAgain = in.nextLine().toLowerCase();


            if (goAgain.equals("y"))  {

                userGraph.resetVersions();
                infector.reset();

            } else {

                break;

            }
        }

        System.out.println("We hope you enjoyed Anson's project as much as he enjoyed making it. Goodbye!");

        in.close();
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
                return;
            }


        } else if (infectionType.equals("l")) {

            System.out.println("Please enter the number of users you would like to try and infect.");

            int toInfect = in.nextInt();
            in.nextLine();

            System.out.println("Please enter a threshold value to stop the search early. For example, if you \n " +
                    "are satisfied with infecting 70% of the number of users you specified above, enter '0.7' ");


            double threshold = in.nextDouble();
            in.nextLine();

            infector.limitedInfect(userGraph, toInfect, threshold);

        } else {

            System.out.println("Sorry, that's not a valid input. Please try again.");
            return;

        }

        runAnalysis();
    }




    public static void runAnalysis() {

        int confused = userGraph.countConfused();
        int total = userGraph.users.size();
        int happy = userGraph.countClassrooms(true);
        int unhappy = userGraph.countClassrooms(false);
        int infected = userGraph.countInfected();

        double fracConfused = ((double)confused / (double)total);

        System.out.println(infected + " users are now infected");
        System.out.println("There are " + total + " users in this graph.");
        System.out.println(confused + " are confused.");
        System.out.println(String.format("%.2f", fracConfused) + "% of the graph is confused");
        System.out.println("There are " + happy + " happy classrooms in the graph");
        System.out.println("There are " + unhappy + " unhappy classroms in the graph");
    }

}
