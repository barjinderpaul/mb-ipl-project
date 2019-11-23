package com.company;

import java.util.*;

//Constants
import static com.company.Constants.*;

public class Main {

    public static void main(String[] args) {

        /*
         * Constructor to read Input.
         * One input function to read both matches.csv as well as deliveries.csv
         */

        ReadInput input = new ReadInput();
        List<Match> matchData = input.getMatchData();
        List<Delivery> deliveryData = input.getDeliveryData();

        //All scenarios are solved in solveScenarios class;
        SolveScenarios solved = new SolveScenarios();

        //questions
        Map<String, Integer> matchesPlayedPerSeason = solved.matchesPlayedPerYear(matchData);
        Map<String, Integer> matchesWon = solved.matchesWonOfAllTeams(matchData);
        Map<String, Integer> extraRuns = solved.extraRuns(matchData, deliveryData);
        Map<String, Double> economicBowlers = solved.economicBowlers(matchData, deliveryData);

        // Final output
        Map<String, Map<String, ?>> finalOutput = new LinkedHashMap<String, Map<String, ?>>();
        finalOutput.put(QUESTION1, matchesPlayedPerSeason);
        finalOutput.put(QUESTION2, matchesWon);
        finalOutput.put(QUESTION3, extraRuns);
        finalOutput.put(QUESTION4, economicBowlers);

        Iterator<Map.Entry<String, Map<String, ?>>> mapIterator = finalOutput.entrySet().iterator();

        while (mapIterator.hasNext()) {
            Map.Entry<String, Map<String, ?>> entry = mapIterator.next();
            String question = entry.getKey();
            Map<String, ?> answer = entry.getValue();
            System.out.println(" >  " + question + " : ");
            for (Map.Entry<String, ?> nestedEntry : answer.entrySet()) {
                System.out.println("\t" + nestedEntry.getKey() + " " + nestedEntry.getValue());
            }
            System.out.println();
        }

    }
}
