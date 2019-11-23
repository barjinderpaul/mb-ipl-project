package com.company;

import java.util.*;

import static com.company.Constants.*;

public class SolveScenarios {

    private ArrayList<String> getMatchIds(List<Match> matchData, String year) {
        ArrayList<String> ids = new ArrayList<String>();
        matchData.forEach(map -> {
            String currSeason = map.getSeason();
            String currId = map.getId().toString();

            if (year.equals(currSeason)) {
                ids.add(currId);
            }
        });
        return ids;
    }

    //    Question 1
    public HashMap<String, Integer> matchesPlayedPerYear(List<Match> matchData) {

        HashMap<String, Integer> totalMatches = new HashMap<String, Integer>();

        matchData.forEach(currentMap -> {
            String season = currentMap.getSeason();
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

        matchData.forEach(currentMap -> {
            String winningTeam = currentMap.getWinner();
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
        deliveryData.forEach(map -> {
            String id = map.getMatchId().toString();
            String team = map.getBowlingTeam();
            Integer runs = map.getExtraRuns();

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

        HashMap<String, Integer> economicBowlers = new HashMap<String, Integer>();
        HashMap<String, Integer> economicBowlersRuns = new HashMap<String, Integer>();
        HashMap<String, Integer> economicBowlersOvers = new HashMap<String, Integer>();

        // This block of code maps all bowlers to the number of ball they have bowled respectively.
        // economicBowlersOvers holds this information
        deliveryData.forEach(map -> {
            String bowlerName = map.getBowler();
            String id = map.getMatchId().toString();
            int totalRuns = map.getTotalRuns();
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
        // economicBowler hashmap holds this information
        LinkedHashMap<String, Double> economicBowler = new LinkedHashMap<String, Double>();
        economicBowlersRuns.forEach((key, value) -> {
            String bowler = key;
            int totalRuns = value;
            int totalBalls = economicBowlersOvers.get(bowler);
            double overs = (double) totalBalls / 6.0;
            double economy = totalRuns / overs;
            economicBowler.put(bowler, economy);
        });

        //This code block sorts the economicBowler hashmap and inserts the sorted rows into sortedEconomicBowlers hashmap
        // LinkedHashMap is used to maintain the ordering of the sorted elements;
        LinkedHashMap<String, Double> sortedEconomicBowlers = new LinkedHashMap<String, Double>();
        economicBowler.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(entry -> sortedEconomicBowlers.put(entry.getKey(), entry.getValue()));


        // This block of code gets the top 'N' economic bowlers where 'N' is the NUMBER_OF_BOWLERS in enumConstants
        LinkedHashMap<String, Double> topEconmicBowlers = new LinkedHashMap<String, Double>();
        Integer count = 0;

        Iterator<Map.Entry<String, Double>> itr2 = sortedEconomicBowlers.entrySet().iterator();
        while (itr2.hasNext()) {
            if (count++ >= NUMBER_OF_BOWLERS ) {
                break;
            }
            Map.Entry<String, Double> entry = itr2.next();
            String bowlerName = entry.getKey();
            Double economy = entry.getValue();
            topEconmicBowlers.put(bowlerName, economy);
        }

        return topEconmicBowlers;
    }

}
