package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        System.out.println();
	    //reading files
        readCSV fileData = new readCSV("matches.csv",",");
        HashMap<Integer,HashMap<String, String>> dataFile = fileData.getCompleteData();


        System.out.println("Number of matches played per year of all the years in IPL.");
        matchesPlayedPerYear totalMatchesPerYearObj = new matchesPlayedPerYear(dataFile);
        TreeMap<String,Integer> matchesPlayerPerSeason =  totalMatchesPerYearObj.getMatchesPlayedPerSeason();

        for(Map.Entry<String,Integer> season: matchesPlayerPerSeason.entrySet()) {
            System.out.println(season.getKey() + " ==> " + season.getValue());
        }

        System.out.println();

        System.out.println("Number of matches won of all teams over all the years of IPL.");
        matchesWonByTeams matchesWonByTeamsObj = new matchesWonByTeams(dataFile);
        HashMap<String,Integer> matchesWon = matchesWonByTeamsObj.getMatchesWonOverAllYears();
        for(Map.Entry<String,Integer> season: matchesWon.entrySet()) {
            System.out.println(season.getKey() + " ==> " + season.getValue());
        }


    }
}
