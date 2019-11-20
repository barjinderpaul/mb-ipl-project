package com.company;

import java.util.HashMap;
import java.util.Map;

public class matchesWonByTeams {
    private HashMap<String,Integer> matchesWon = new HashMap<String, Integer>();

    public matchesWonByTeams(HashMap<Integer, HashMap<String, String>> dataFile) {
        for(Map.Entry<Integer,HashMap<String,String>> entry : dataFile.entrySet()) {

            HashMap<String,String> nested = entry.getValue();
            String teamName = nested.get("winner");

            int value = 0;
            if (matchesWon.get(teamName) != null ){
                value = matchesWon.get(teamName);
            }
            if( !(teamName.equals("")) ) {
                matchesWon.put(teamName, ++value);
            }
        }
    }

    public HashMap<String, Integer> getMatchesWonOverAllYears() {
        return matchesWon;
    }
}
