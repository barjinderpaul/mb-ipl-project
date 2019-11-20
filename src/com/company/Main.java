package com.company;
import java.io.*;
import java.util.*;
public class Main {


    private ArrayList<String[]> readCSV(String fileName, String splitter){
        ArrayList<String[]> fileData = new ArrayList<>();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
               String[] currentMatch = line.split(splitter);
                fileData.add(currentMatch);
            }
//            System.out.println(matchesPerYear);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Creating file with arraylist of size =" + fileData.size());
        return fileData;
    }

    private void readArrayList(ArrayList<String[]> fileData) {
        fileData.forEach(match->System.out.println(match[0]));
    }

    private Map<String,Integer> calculateMatchPerYear(ArrayList<String[]> fileData){
        Map<String,Integer> matchesPerYear= new TreeMap<String,Integer>();
        fileData.forEach(match -> {
            int value = 0;
            if(matchesPerYear.get(match[1])!=null){
                value = matchesPerYear.get(match[1]);
            }

            matchesPerYear.put(match[1],++value) ;
        });
        return matchesPerYear;

    }


    private Map<String,Integer> calculateWinners(ArrayList<String[]> fileData) {
        Map<String,Integer> matchesWonByTeams = new HashMap<>();
        fileData.forEach(match -> {
            int value = 0;
            if(matchesWonByTeams.get(match[10])!=null){
                value = matchesWonByTeams.get(match[10]);
            }
            if(match[10].length() > 0) {
                matchesWonByTeams.put(match[10], ++value);
            }
        });

        return matchesWonByTeams;

    }

    private ArrayList<String> getMatchIds(int yearNumber, ArrayList<String[]> matchesFileData) {
        ArrayList<String> ids = new ArrayList<>();
        for(String[] s: matchesFileData){
            Integer year = Integer.parseInt(s[1]);
            if(year == yearNumber){
                ids.add(s[0]);
            }
        }

//        matchesFileData.stream().filter(match -> Integer.parseInt(match[1])==2016).map(match -> ids.add(match[1]));
//        System.out.println("size = " + ids.size());
        return ids;
    }

    private Map<String,Integer> calculateExtraRuns(ArrayList<String> matchIds, ArrayList<String[]> deliveriesData) {
        Map<String,Integer> extraRunsPerTeam = new HashMap<String,Integer>();
        for(String[] s : deliveriesData) {
            String id = s[0];
            String team = s[3];
            int runs = Integer.parseInt(s[16]);
            for(int i=0;i<matchIds.size();i++){
                if (id.equals(matchIds.get(i))){
                    int currRuns = runs;
                    if(extraRunsPerTeam.get(team)!=null){
                        currRuns += extraRunsPerTeam.get(team);
                    }
                    extraRunsPerTeam.put(team,currRuns);
                }
            }
        }
        System.out.println("size = " + extraRunsPerTeam.size());
        return extraRunsPerTeam;

    }

    private void calculateEconomicBowler(ArrayList<String>matchIds, ArrayList<String[]> deliveriesData){
        Map<String,Integer> economicBowlersRuns = new HashMap<String,Integer>();
        Map<String,Integer> economicBowlersOvers = new HashMap<String,Integer>();
        for(String[] s : deliveriesData) {
            String bowlerName = s[8];
            String id = s[0];
            int totalRuns = Integer.parseInt(s[17]);
            for( int i=0;i<matchIds.size();i++) {
                if( id.equals(matchIds.get(i)) ){
                    int currRuns = totalRuns;
                    if(economicBowlersRuns.get(bowlerName)!=null){
                        currRuns+=economicBowlersRuns.get(bowlerName);
                    }
                    economicBowlersRuns.put(bowlerName,currRuns);

                    int balls = 1;
                    if(economicBowlersOvers.get(bowlerName)!=null){
                        balls += economicBowlersOvers.get(bowlerName);
                    }
                    economicBowlersOvers.put(bowlerName,balls);
                }
            }
        }
        System.out.println("runs, over size = " + economicBowlersRuns.size() + " " + economicBowlersOvers.size());
        Map<String,Double> economicBowler = new HashMap<String,Double>();
        economicBowlersRuns.forEach((key,value)->{
            String bowler = key;
            int totalRuns = value;
            int totalBalls = economicBowlersOvers.get(bowler);
            double overs = (double) totalBalls/ 6.0;
            double economy = totalRuns/overs;
            economicBowler.put(bowler,economy);
        });

        String leastEconomicBowler = "";
        Double leastEconomy = 100.0;

        Iterator<Map.Entry<String, Double>> itr = economicBowler.entrySet().iterator();

        while(itr.hasNext())
        {
            Map.Entry<String, Double> entry = itr.next();
            String bowlerName = entry.getKey();
            Double economy = entry.getValue();
            if(economy < leastEconomy) {
                leastEconomy = economy;
                leastEconomicBowler = bowlerName;
            }
        }


        System.out.println("Bowler , economy = " + leastEconomicBowler + " " + leastEconomy);

    }

    public static void main(String[] args) {
        Main iplProject = new Main();

        // Configuring files;
        String splitter = ",";
        String matchesFile = "matches.csv";
        String deliveriesFile = "deliveries.csv";
        ArrayList<String[]> matchesFileData = iplProject.readCSV(matchesFile,splitter);
        ArrayList<String[]> deliveriesFileData = iplProject.readCSV(deliveriesFile,splitter);

        // Question 1
        Map<String,Integer> matchesPerYear = iplProject.calculateMatchPerYear(matchesFileData);
        System.out.println("Question : Number of matches played per year of all the years in IPL :");
        for(Map.Entry<String,Integer> entry: matchesPerYear.entrySet() ){
            String year = entry.getKey();
            Integer matches = entry.getValue();
            System.out.println(year + " ==> " + matches);
        }
        System.out.println();

        // Question 2
        System.out.println("Question 2");
        Map<String,Integer> matchWinners = iplProject.calculateWinners(matchesFileData);
        for(Map.Entry<String,Integer> entry: matchWinners.entrySet() ){
            String team = entry.getKey();
            Integer matches = entry.getValue();
            System.out.println(team + " ==> " + matches);
        }

        // Question 3
        /*
        * Better approach could be to just know the id from where the year 2016 is starting and the id where
        * 2016 is ending as the data in the matches.csv is sorted, but if we take this approach, this might fail
        * when the data is not sorted according to the year and continuous ids are not given to the year.
        * */
        System.out.println("Question 3 ");
        final int year2016 = 2016;
        ArrayList<String> matchIds2016 = iplProject.getMatchIds(year2016,matchesFileData);
        System.out.println("idssize = " + matchIds2016.size());
        Map<String,Integer> extraRunsPerTeam = iplProject.calculateExtraRuns(matchIds2016,deliveriesFileData);
        for(Map.Entry<String,Integer> entry: extraRunsPerTeam.entrySet() ){
            String team = entry.getKey();
            Integer runs = entry.getValue();
            System.out.println(team + " ==> " + runs);
        }


        //Question 4
        System.out.println("Question 4");
        final int year2015= 2015;
        ArrayList<String> matchIds2015 = iplProject.getMatchIds(year2015,matchesFileData);
        System.out.println("2015 ids size = " + matchIds2015.size());
        iplProject.calculateEconomicBowler(matchIds2015,deliveriesFileData);
    }

}
