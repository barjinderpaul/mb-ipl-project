package com.company;

import javax.swing.*;
import java.util.*;

public class solveScenarios {

    public HashMap<String,Integer> matchesPlayedPerYear(ArrayList<HashMap<String,String>> matchData){

        HashMap<String,Integer> totalMatches = new HashMap<String,Integer>();

        matchData.forEach(currentMap->{
            String season = currentMap.get("season");
            int counter = 0;
            if(totalMatches.get(season) != null) {
                counter = totalMatches.get(season);
            }
            totalMatches.put(season,++counter);
        });

        return totalMatches;
    }

    public HashMap<String,Integer> matchesWonOfAllTeams(ArrayList<HashMap<String,String>> matchData) {
        HashMap<String,Integer> matchesWon = new HashMap<String,Integer>();

        matchData.forEach(currentMap->{
            String winningTeam = currentMap.get("winner");
            int counter = 0;
            if(matchesWon.get(winningTeam)!=null) {
                counter = matchesWon.get(winningTeam);
            }
            matchesWon.put(winningTeam,++counter);
        });
        matchesWon.remove("");
        /*System.out.println(matchesWon.size());
        for(Map.Entry<String,Integer> entry : matchesWon.entrySet()) {
            System.out.println(entry.getKey() + "  " + entry.getValue());
        }*/
        return matchesWon;
    }

    public HashMap<String,Integer> extraRuns(ArrayList<String> matchIds, ArrayList<HashMap<String,String>> matchData){
        HashMap<String,Integer> extraRunsPerTeam = new HashMap<String,Integer>();
        matchData.forEach(map -> {
            String id = map.get("match_id");
            String team = map.get("bowling_team");
            Integer runs = Integer.parseInt(map.get("extra_runs"));
            for(int i=0;i<matchIds.size();i++){
                if (id.equals(matchIds.get(i))){
                    int currRuns = runs;
                    if(extraRunsPerTeam.get(team)!=null){
                        currRuns += extraRunsPerTeam.get(team);
                    }
                    extraRunsPerTeam.put(team,currRuns);
                }
            }
        });
        return extraRunsPerTeam;
    }


    public HashMap<String,Double> economicBowlers(ArrayList<String> matchIds, ArrayList<HashMap<String,String>> matchData){
        HashMap<String,Integer> economicBowlers = new HashMap<String,Integer>();

        HashMap<String,Integer> economicBowlersRuns = new HashMap<String,Integer>();
        HashMap<String,Integer> economicBowlersOvers = new HashMap<String,Integer>();

        matchData.forEach(map -> {
        String bowlerName = map.get("bowler");
        String id = map.get("match_id");
        int totalRuns = Integer.parseInt(map.get("total_runs"));
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

        LinkedHashMap<String,Double> economicBowler = new LinkedHashMap<String,Double>();
        economicBowlersRuns.forEach((key,value)->{
            String bowler = key;
            int totalRuns = value;
            int totalBalls = economicBowlersOvers.get(bowler);
            double overs = (double) totalBalls/ 6.0;
            double economy = totalRuns/overs;
            economicBowler.put(bowler,economy);
        });

        LinkedHashMap<String,Double> revEconomicBowler = new LinkedHashMap<String,Double>();
        economicBowler.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(entry -> revEconomicBowler.put(entry.getKey(), entry.getValue()));



        LinkedHashMap<String,Double> topEconmicBowlers = new LinkedHashMap<String,Double>();
        int count=0;

        Iterator<Map.Entry<String, Double>> itr2 = revEconomicBowler.entrySet().iterator();

        while(itr2.hasNext())
        {
            if(count++ > 5){
                break;
            }
            Map.Entry<String, Double> entry = itr2.next();
            String bowlerName = entry.getKey();
            Double economy = entry.getValue();
            topEconmicBowlers.put(bowlerName,economy);
        }

        return topEconmicBowlers;
    }
}
