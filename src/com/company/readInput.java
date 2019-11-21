package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.company.enumConstants.Status.*;


public class readInput {

    private ArrayList<HashMap<String, String>> matchData = new ArrayList<HashMap<String, String>>();
    private ArrayList<HashMap<String, String>> deliveriesData = new ArrayList<HashMap<String, String>>();

    private ArrayList<String> headers = new ArrayList<String>();
    private ArrayList<String> headersDeliveries = new ArrayList<String>();

    //Constants;
    private final String matchFile = MATCH_FILE.value();
    private final String deliveriesFile = DELIVERIES_FILE.value();
    private final String splitter = SPLITTER.value();


    public readInput() {
        readCSV();
    }


    public ArrayList<HashMap<String, String>> getMatchData() {
        return matchData;
    }

    public ArrayList<HashMap<String, String>> getDeliveriesData() {
        return deliveriesData;
    }


    public void readCSV() {
        String line = "";
        //reaeding matchesFile
        try (BufferedReader br = new BufferedReader(new FileReader(matchFile))) {
            line = br.readLine();
            String[] headersLine = line.split(splitter);
            for (int i = 0; i < headersLine.length; ++i) {
                headers.add(headersLine[i]);
            }
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(splitter);
                HashMap<String, String> nested = new HashMap<String, String>();
                for (int i = 0; i < currentLine.length; ++i) {
                    nested.put(headers.get(i), currentLine[i]);
                }
                matchData.add(nested);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        line = "";
        //reading deliveries file
        try (BufferedReader br = new BufferedReader(new FileReader(deliveriesFile))) {
            line = br.readLine();

            String[] headersLine2 = line.split(splitter);
            for (int i = 0; i < headersLine2.length; ++i) {
                headersDeliveries.add(headersLine2[i]);
            }
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(splitter);

                HashMap<String, String> nested = new HashMap<String, String>();
                for (int i = 0; i < currentLine.length; ++i) {
                    nested.put(headersDeliveries.get(i), currentLine[i]);
                }
                deliveriesData.add(nested);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
