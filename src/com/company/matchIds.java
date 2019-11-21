package com.company;

import java.util.ArrayList;
import java.util.HashMap;


public class matchIds {
    private ArrayList<String> ids = new ArrayList<String>();


    public matchIds(ArrayList<HashMap<String, String>> matchData, String year) {
        matchData.forEach(map -> {
            String currSeason = map.get("season");
            String currId = map.get("id");

            if (year.equals(currSeason)) {
                ids.add(currId);
            }
        });
    }


    public ArrayList<String> getMatchIds() {
        return ids;
    }
}
