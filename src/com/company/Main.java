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
        Map<String, Map<String, ?>> finalOut = new LinkedHashMap<String, Map<String, ?>>();
        finalOut.put(QUESTION1, matchesPlayedPerSeason);
        finalOut.put(QUESTION2, matchesWon);
        finalOut.put(QUESTION3, extraRuns);
        finalOut.put(QUESTION4, economicBowlers);

        Iterator<Map.Entry<String, Map<String, ?>>> itr2 = finalOut.entrySet().iterator();

        while (itr2.hasNext()) {
            Map.Entry<String, Map<String, ?>> entry = itr2.next();
            String question = entry.getKey();
            Map<String, ?> answer = entry.getValue();
            System.out.println(" >  " + question + " : ");
            for (Map.Entry<String, ?> entry1 : answer.entrySet()) {
                System.out.println("\t" + entry1.getKey() + " " + entry1.getValue());
            }
            System.out.println();
        }

    }
}
