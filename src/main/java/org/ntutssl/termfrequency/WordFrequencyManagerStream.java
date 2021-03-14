package org.ntutssl.termfrequency;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.SortOrder;

public class WordFrequencyManagerStream implements IWordFrequencyManager {
    private Map<String, Integer> words;

    public WordFrequencyManagerStream() {
        this.words = new HashMap<>();
    }

    @Override
    public void incrementCount(String word) {
        if (this.words.containsKey(word)) {
            this.words.put(word, this.words.get(word) + 1);
        } else {
            this.words.put(word, 1);
        }
    }

    @Override
    public int getNumOfWords() {
        return this.words.size();
    }

    @Override
    public Map<String, Integer> getWords() {
        return this.words;
    }

    @Override
    public List<String> getWordFrequency(SortOrder order) {
        Map<String, Integer> sortWords = new LinkedHashMap<>();
        List<String> wordList = new LinkedList<>();

        if (order.equals(SortOrder.DESCENDING)) {
            words.entrySet().stream().sorted(Entry.<String, Integer>comparingByValue().reversed())
                    .forEachOrdered(word -> sortWords.put(word.getKey(), word.getValue()));
        } else if (order.equals(SortOrder.ASCENDING)) {
            words.entrySet().stream().sorted(Entry.<String, Integer>comparingByValue())
                    .forEachOrdered(word -> sortWords.put(word.getKey(), word.getValue()));
        }

        for (Entry<String, Integer> word : sortWords.entrySet()) {
            wordList.add(word.getKey() + ": " + word.getValue() + "\n");
        }

        return wordList;
    }

    // @Override
    // public void output(String outputPath, String order, int range, IOHandler handler) {
    //     if (words.size() == 0)
    //         throw new WordFrequencyException("Word not found.");
    //     if (range > words.size() || range < 1)
    //         throw new WordFrequencyException(
    //                 String.format("Out of range! The range should be from 1 to %d.", words.size()));

    //     switch (order) {
    //     case "des":
    //         handler.handleOutput(outputPath, range, getWordFrequency(SortOrder.DESCENDING));
    //         break;
    //     case "asc":
    //         handler.handleOutput(outputPath, range, getWordFrequency(SortOrder.ASCENDING));
    //         break;
    //     default:
    //         throw new WordFrequencyException("The order should be \"asc\" or \"des\".");
    //     }
    // }
}