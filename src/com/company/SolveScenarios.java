package com.company;

import java.util.*;

import static com.company.Constants.*;

public class SolveScenarios {

    private ArrayList<String> getMatchIds(List<Match> matchData, String year) {
        ArrayList<String> ids = new ArrayList<String>();
        matchData.forEach(matchObject -> {
            String currSeason = matchObject.getSeason();
            String currId = matchObject.getId().toString();

            if (year.equals(currSeason)) {
                ids.add(currId);
            }
        });
        return ids;
    }

    //    Question 1
    public HashMap<String, Integer> matchesPlayedPerYear(List<Match> matchData) {

        HashMap<String, Integer> totalMatches = new HashMap<String, Integer>();

        matchData.forEach(matchObject -> {
            String season = matchObject.getSeason();
            int counter = 0;
            if (totalMatches.get(season) != null) {
                counter = totalMatches.get(season);
            }
            totalMatches.put(season, ++counter);
        });

        return totalMatches;
    }


    //    Question 2
    public HashMap<String, Integer> matchesWonOfAllTeams(List<Match> matchData) {
        HashMap<String, Integer> matchesWon = new HashMap<String, Integer>();

        matchData.forEach(matchObject -> {
            String winningTeam = matchObject.getWinner();
            int counter = 0;
            if (matchesWon.get(winningTeam) != null) {
                counter = matchesWon.get(winningTeam);
            }
            matchesWon.put(winningTeam, ++counter);
        });
        matchesWon.remove("");

        return matchesWon;
    }


    //    Question 3
    public HashMap<String, Integer> extraRuns(List<Match> matchData, List<Delivery> deliveryData) {

        ArrayList<String> matchIds = getMatchIds(matchData, EXTRA_RUNS_YEAR);
        HashMap<String, Integer> extraRunsPerTeam = new HashMap<String, Integer>();
        deliveryData.forEach(deliveryObject -> {
            String id = deliveryObject.getMatchId().toString();
            String team = deliveryObject.getBowlingTeam();
            Integer runs = deliveryObject.getExtraRuns();

            for (int i = 0; i < matchIds.size(); i++) {
                if (id.equals(matchIds.get(i))) {
                    int currRuns = runs;
                    if (extraRunsPerTeam.get(team) != null) {
                        currRuns += extraRunsPerTeam.get(team);
                    }
                    extraRunsPerTeam.put(team, currRuns);
                }
            }
        });

        return extraRunsPerTeam;
    }


    //    Question 4
    public HashMap<String, Double> economicBowlers(List<Match> matchData, List<Delivery> deliveryData) {
        ArrayList<String> matchIds = getMatchIds(matchData, ECONOMIC_BOWLER_YEAR);

        HashMap<String, Integer> economicBowlersRuns = new HashMap<String, Integer>();
        HashMap<String, Integer> economicBowlersOvers = new HashMap<String, Integer>();

        // This block of code maps all bowlers to the number of ball they have bowled respectively.
        // economicBowlersOvers holds this information
        deliveryData.forEach(deliveryObject -> {
            String bowlerName = deliveryObject.getBowler();
            String id = deliveryObject.getMatchId().toString();
            int totalRuns = deliveryObject.getTotalRuns();
            for (int i = 0; i < matchIds.size(); i++) {
                if (id.equals(matchIds.get(i))) {
                    int currRuns = totalRuns;
                    if (economicBowlersRuns.get(bowlerName) != null) {
                        currRuns += economicBowlersRuns.get(bowlerName);
                    }
                    economicBowlersRuns.put(bowlerName, currRuns);

                    int balls = 1;
                    if (economicBowlersOvers.get(bowlerName) != null) {
                        balls += economicBowlersOvers.get(bowlerName);
                    }
                    economicBowlersOvers.put(bowlerName, balls);
                }
            }
        });

        // This block of code calculates economy for each bowler and maps it to that specific bowler.
        // bowlersWithEconomy hashmap holds this information
        LinkedHashMap<String, Double> bowlersWithEconomy = new LinkedHashMap<String, Double>();
        economicBowlersRuns.forEach((key, value) -> {
            String bowler = key;
            int totalRuns = value;
            int totalBalls = economicBowlersOvers.get(bowler);
            double overs = (double) totalBalls / 6.0;
            double economy = totalRuns / overs;
            bowlersWithEconomy.put(bowler, economy);
        });

        //This code block sorts the bowlersWithEconomy hashmap and inserts the sorted rows into sortedBowlersWithEconomy hashmap
        // LinkedHashMap is used to maintain the ordering of the sorted elements;
        LinkedHashMap<String, Double> sortedBowlersWithEconomy = new LinkedHashMap<String, Double>();
        bowlersWithEconomy.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(entry -> sortedBowlersWithEconomy.put(entry.getKey(), entry.getValue()));


        // This block of code gets the top 'N' economic bowlers where 'N' is the NUMBER_OF_BOWLERS in enumConstants
        LinkedHashMap<String, Double> topEconomicBowlers = new LinkedHashMap<String, Double>();
        Integer count = 0;

        Iterator<Map.Entry<String, Double>> itr2 = sortedBowlersWithEconomy.entrySet().iterator();
        while (itr2.hasNext()) {
            if (count++ >= NUMBER_OF_BOWLERS ) {
                break;
            }
            Map.Entry<String, Double> entry = itr2.next();
            String bowlerName = entry.getKey();
            Double economy = entry.getValue();
            topEconomicBowlers.put(bowlerName, economy);
        }

        return topEconomicBowlers;
    }

}
