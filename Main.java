package com.company;
import java.io.*;
import java.util.*;
public class Main {

    private ArrayList<String[]> fileData = new ArrayList<>();
    private Map<String,Integer> matchesPerYear= new TreeMap<String,Integer>();

    private void readCSV(String splitter){
        String csvFile = "matches.csv";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
               String[] currentMatch = line.split(splitter);
                fileData.add(currentMatch);
            }
//            System.out.println(matchesPerYear);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readArrayList() {
        fileData.forEach(match->System.out.println(match[0]));
    }

    private void calculateMatchPerYear(){
        fileData.forEach(match -> {
            int value = 0;
            if(matchesPerYear.get(match[1])!=null){
                value = matchesPerYear.get(match[1]);
            }
            matchesPerYear.put(match[1],++value) ;
        });

    }

    private void readMatchPerYearAnswer() {
        // System.out.println(matchesPerYear);
        System.out.println("Number of matches played per year of all the years in IPL :");
        for(Map.Entry<String,Integer> entry: matchesPerYear.entrySet() ){
            String year = entry.getKey();
            Integer matches = entry.getValue();
            System.out.println(year + " ==> " + matches);
        }

    }

    public static void main(String[] args) {
        Main iplProject = new Main();

        // Question 1
        String splitter = ",";
        iplProject.readCSV(splitter);
        iplProject.calculateMatchPerYear();
        iplProject.readMatchPerYearAnswer();


    }

}
