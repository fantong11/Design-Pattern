package org.ntutssl.termfrequency;

import java.util.Map.Entry;

public class WordFrequencyController {
    public static void main(String[] args) {
        String stopWordFile = args[0];
        String textFile = args[1];
        String range = args[2];
        String order = args[3];

        DataStorageManager dataStorageManager = new DataStorageManager(textFile);
        StopWordManager stopWordManager = new StopWordManager(stopWordFile);
        WordFrequencyManager wordFrequencyManager = new WordFrequencyManager();

        // 把字加進wfm
        for (String word : dataStorageManager.getWords()) {
            if (!stopWordManager.isStopWordSet(word)) {
                wordFrequencyManager.incrementCount(word);
            }
        }

        if (wordFrequencyManager.getNumOfWords() < Integer.parseInt(range)) {
            return;    
        }

        // 範圍
        int count = 0;
        if (order.equals("des")) {

            for (Entry<String, Integer> entry : wordFrequencyManager.getWordFrequencyDescending().entrySet()) {
                if (count >= Integer.parseInt(range))
                    break;
                System.out.println(entry.getKey() + ": " + entry.getValue());
                count++;
            }
        } else if (order.equals("asc")) {

            for (Entry<String, Integer> entry : wordFrequencyManager.getWordFrequencyAscending().entrySet()) {
                if (count >= Integer.parseInt(range))
                    break;
                System.out.println(entry.getKey() + ": " + entry.getValue());
                count++;
            }
        }

    }
}