package com.company;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//CONSTANTS
import static com.company.enumConstants.Status.*;

public class Main {


    public static void main(String[] args) {

        /*
         * Constructor to read Input.
         * One input function to read both matches.csv as well as deliveries.csv
         */
        readInput input = new readInput();
        ArrayList<HashMap<String, String>> matchData = input.getMatchData();
        ArrayList<HashMap<String, String>> deliveriesData = input.getDeliveriesData();

        //All scenarios are solved in solveScenarios class;
        solveScenarios solved = new solveScenarios();

        //question 1 and 2
        Map<String, Integer> matchesPlayedPerSeason = solved.matchesPlayedPerYear(matchData);
        Map<String, Integer> matchesWon = solved.matchesWonOfAllTeams(matchData);

        //getting matchIds for 2015 and 2016
        matchIds matchid2016 = new matchIds(matchData, EXTRA_RUNS_YEAR.value());
        ArrayList<String> matchIds2016 = matchid2016.getMatchIds();

        matchIds matchid2015 = new matchIds(matchData, ECONOMIC_BOWLER_YEAR.value());
        ArrayList<String> matchIds2015 = matchid2015.getMatchIds();

        //question 3 and 4
        Map<String, Integer> extraRuns = solved.extraRuns(matchIds2016, deliveriesData);
        Map<String, Double> economicBowlers = solved.economicBowlers(matchIds2015, deliveriesData);

        Map<String, Map<String, ?>> finalOut = new LinkedHashMap<String, Map<String, ?>>();
        finalOut.put(QUESTION1.value(), matchesPlayedPerSeason);
        finalOut.put(QUESTION2.value(), matchesWon);
        finalOut.put(QUESTION3.value(), extraRuns);
        finalOut.put(QUESTION4.value(), economicBowlers);

        scenarioResults result = new scenarioResults(finalOut);

    }
}
