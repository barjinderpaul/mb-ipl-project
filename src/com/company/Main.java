package com.company;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Main implements Constants{


    public static void main(String[] args) {

        // reading files
        readInput input = new readInput(MATCHFILE, DELIVERIESFILE,SPLITTER);
        ArrayList<HashMap<String,String>> matchData = input.getMatchData();
        ArrayList<HashMap<String,String>> deliveriesData = input.getDeliveriesData();

        //All scenarios are solved in solveScenarios class;
        solveScenarios solved = new solveScenarios();

        //question 1 and 2
        Map<String,Integer> matchesPlayedPerSeason = solved.matchesPlayedPerYear(matchData);
        Map<String,Integer> matchesWon = solved.matchesWonOfAllTeams(matchData);

        //getting matchIds for 2015 and 2016
        matchIds matchid = new matchIds(matchData,EXTRARUNSYEAR);
        ArrayList<String> matchIds2016 = matchid.getMatchIds();

        matchIds matchid2015 = new matchIds(matchData,ECONOMICBOWLERYEAR);
        ArrayList<String> matchIds2015 = matchid2015.getMatchIds();

        //question 3 and 4
        Map<String,Integer> extraRuns = solved.extraRuns(matchIds2016,deliveriesData);
        Map<String,Double> economicBowlers = solved.economicBowlers(matchIds2015,deliveriesData);

        Map<String,Map<String,? >> finalOut = new LinkedHashMap<String, Map<String, ?>>();
        finalOut.put(QUESTION1,matchesPlayedPerSeason);
        finalOut.put(QUESTION2,matchesWon);
        finalOut.put(QUESTION3,extraRuns);
        finalOut.put(QUESTION4,economicBowlers);

        scenarioResults result = new scenarioResults(finalOut);

    }
}
