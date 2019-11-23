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

    public ReadInput() {
        readCSV();
    }

    public void readCSV(){
        String line = "";
        //reaeding matchesFile
        try (BufferedReader br = new BufferedReader(new FileReader(MATCH_FILE))) {
            // Ignoring headers
            line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(SPLITTER);
                Match match = new Match();
                match.setId(Integer.parseInt(currentLine[ID_COLUMN]));
                match.setSeason(currentLine[SEASON_COLUMN]);
                match.setWinner(currentLine[WINNER_COLUMN]);
                matchData.add(match);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        line = "";
        //reading deliveries file
        try (BufferedReader br = new BufferedReader(new FileReader(DELIVERIES_FILE))) {
            //ignoring headers
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(SPLITTER);
                Delivery delivery = new Delivery();

                delivery.setBowler(currentLine[BOWLER_COLUMN]);
                delivery.setBowlingTeam(currentLine[BOWLING_TEAM_COLUMN]);
                delivery.setExtraRuns(Integer.parseInt(currentLine[EXTRA_RUNS_COLUMN]));
                delivery.setTotalRuns(Integer.parseInt(currentLine[TOTAL_RUNS_COLUMN]));
                delivery.setMatchId(Integer.parseInt(currentLine[ID_COLUMN_DELIVERY_FILE]));

                deliveryData.add(delivery);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Match> getMatchData(){
        return matchData;
    }

    public List<Delivery> getDeliveryData() {
        return deliveryData;
    }
}
