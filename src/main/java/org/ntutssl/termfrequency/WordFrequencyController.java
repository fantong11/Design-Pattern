package org.ntutssl.termfrequency;

import java.util.Map;
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
    }
}