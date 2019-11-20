package com.company;

import java.util.*;
import java.io.*;

public class readCSV {
    private ArrayList<String[]> fileData = new ArrayList<>();
    private HashMap<Integer, HashMap<String,String>> dataFile = new HashMap<Integer,HashMap<String, String>>();
    private ArrayList<String> headers = new ArrayList<String>();

    public  readCSV(String fileName, String splitter) {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            line = br.readLine();

            String[] headersLine = line.split(splitter);
            for (int i = 0; i < headersLine.length; ++i) {
               headers.add(headersLine[i]);
            }


            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(splitter);

                HashMap<String, String> nested = new HashMap<String, String>();
                for(int i=1;i<currentLine.length;++i){
                    nested.put(headers.get(i),currentLine[i]);
                }
                dataFile.put(Integer.parseInt(currentLine[0]),nested);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public HashMap<Integer, HashMap<String,String>> getCompleteData() {
        return dataFile;
    }


}
