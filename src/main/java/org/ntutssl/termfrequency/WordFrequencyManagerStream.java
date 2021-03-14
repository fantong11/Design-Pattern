package org.ntutssl.termfrequency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
        List<Entry<String, Integer>> list = new LinkedList<>(this.words.entrySet());
        List<String> wordList = new LinkedList<>();

        if (order.equals(SortOrder.DESCENDING)) {
            list.stream().sorted(new Comparator<Entry<String, Integer>>() {
                @Override
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            }).collect(Collectors.toList());
            // words.entrySet().stream().sorted(Entry.<String, Integer>comparingByValue().reversed());
        } else if (order.equals(SortOrder.ASCENDING)) {
            list.stream().sorted(new Comparator<Entry<String, Integer>>() {
                @Override
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                    return (o1.getValue().compareTo(o2.getValue()));
                }
            }).collect(Collectors.toList());
        }

        for (Entry<String, Integer> word : words.entrySet()) {
            wordList.add(word.getKey() + ": " + word.getValue() + "\n");
        }

        return wordList;
    }
    // @Override
    // public List<String> getWordFrequency(SortOrder order) {
    //     List<String> sortedList = new ArrayList<>();
    //     List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(words.entrySet());
    //     if (order.equals(SortOrder.DESCENDING)) {
    //         list.stream().sorted((o1, o2)->o2.getValue().compareTo(o1.getValue())).collect(Collectors.toList());
    //     } else if (order.equals(SortOrder.ASCENDING)) {
    //         list.stream().sorted((o1, o2)->o1.getValue().compareTo(o2.getValue())).collect(Collectors.toList());
    //     }

    //     for (Map.Entry<String, Integer> entry : list) {
    //         sortedList.add(entry.getKey() + ": " + entry.getValue() + "\n");
    //     };
        
    //     return sortedList;
    // }

    @Override
    public void output(String outputPath, String order, int range, IOHandler handler) {
        if (range > words.size() || range < 1)
            throw new WordFrequencyException(String.format("Out of range! The range should be from 1 to %d.", words.size()));
            
        switch (order) {
        case "des":
            handler.handleOutput(outputPath, range, getWordFrequency(SortOrder.DESCENDING));
            break;
        case "asc":
            handler.handleOutput(outputPath, range, getWordFrequency(SortOrder.ASCENDING));
            break;
        default:
            throw new WordFrequencyException("The order should be \"asc\" or \"des\".");
        }
    }
}