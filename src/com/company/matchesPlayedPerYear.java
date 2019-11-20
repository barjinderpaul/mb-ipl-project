package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class matchesPlayedPerYear {
    private TreeMap<String, Integer> matches = new TreeMap<String, Integer>();

    public matchesPlayedPerYear(HashMap<Integer, HashMap<String,String>> dataFile){
        for(Map.Entry<Integer,HashMap<String,String>> entry : dataFile.entrySet()) {

            HashMap<String,String> nested = entry.getValue();
            String seasonYear = nested.get("season");

            int value = 0;
            if (matches.get(seasonYear) != null ){
                value = matches.get(seasonYear);
            }
            matches.put(seasonYear,++value);
        }
    }

    public TreeMap<String, Integer> getMatchesPlayedPerSeason(){
        return matches;
    }

}
