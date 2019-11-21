package com.company;

import java.util.Iterator;
import java.util.Map;

public class scenarioResults {
    public scenarioResults(Map<String, Map<String, ?>> output) {
        Iterator<Map.Entry<String, Map<String, ?>>> itr2 = output.entrySet().iterator();

        while (itr2.hasNext()) {
            Map.Entry<String, Map<String, ?>> entry = itr2.next();
            String question = entry.getKey();
            Map<String, ?> answer = entry.getValue();
            System.out.println(" >  " + question + " : ");
            for (Map.Entry<String, ?> entry1 : answer.entrySet()) {
                System.out.println("\t" + entry1.getKey() + " " + entry1.getValue());
            }
            System.out.println();
        }
    }
}
