package com.company;

public class enumConstants {
    public enum Status {

        MATCH_FILE("matches.csv"),DELIVERIES_FILE("deliveries.csv"),SPLITTER(","),
        EXTRA_RUNS_YEAR("2015"),ECONOMIC_BOWLER_YEAR("2016"),
        QUESTION1("Number of matches played per year of all the years in IPL."),
        QUESTION2("Number of matches won of all teams over all the years of IPL."),
        QUESTION3("For the year 2016 get the extra runs conceded per team."),
        QUESTION4("For the year 2015 get the top economical bowlers.");

        private String statusValue;


        private Status (String statusValue) {
            this. statusValue = statusValue;
        }

        String value (){
            return this.statusValue;
        }

        @Override
        public String toString(){
            return statusValue;
        }


    }
}
