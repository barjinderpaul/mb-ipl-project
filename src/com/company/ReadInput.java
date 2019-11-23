package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.company.Constants.*;

public class ReadInput {
    private List<Match> matchData = new ArrayList<>();
    private List<Delivery> deliveryData = new ArrayList<>();

    private List<String[]> readLines(String fileName) {
        List<String[]> lines = new ArrayList<>();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Ignoring headers
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(SPLITTER);
                lines.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }


    public List<Match> getMatchData(){
        List<String[]> matchFileLines = readLines(MATCH_FILE);
        matchFileLines.forEach(currentLine-> {
            Match match = new Match();

            match.setId(Integer.parseInt(currentLine[ID_COLUMN]));
            match.setSeason(currentLine[SEASON_COLUMN]);
            match.setWinner(currentLine[WINNER_COLUMN]);

            matchData.add(match);
        });
        return matchData;
    }

    public List<Delivery> getDeliveryData() {
        List<String[]> deliveryFileLines = readLines(DELIVERIES_FILE);
        deliveryFileLines.forEach(currentLine -> {
            Delivery delivery = new Delivery();

            delivery.setBowler(currentLine[BOWLER_COLUMN]);
            delivery.setBowlingTeam(currentLine[BOWLING_TEAM_COLUMN]);
            delivery.setExtraRuns(Integer.parseInt(currentLine[EXTRA_RUNS_COLUMN]));
            delivery.setTotalRuns(Integer.parseInt(currentLine[TOTAL_RUNS_COLUMN]));
            delivery.setMatchId(Integer.parseInt(currentLine[ID_COLUMN_DELIVERY_FILE]));

            deliveryData.add(delivery);
        });
        return deliveryData;
    }
}
