package org.ntutssl.termfrequency;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.SortOrder;

public class WordFrequencyManager implements IWordFrequencyManager {
    private Map<String, Integer> words;

    public WordFrequencyManager() {
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
    public Map<String, Integer> getWords() {
        return this.words;
    }

    @Override
    public int getNumOfWords() {
        return this.words.size();
    }

    @Override
    public List<String> getWordFrequency(SortOrder order) {
        List<Entry<String, Integer>> list = new LinkedList<>(this.words.entrySet());
        List<String> wordList = new LinkedList<>();

        if (order.equals(SortOrder.DESCENDING)) {
            Collections.sort(list, new Comparator<Entry<String, Integer>>() {
                @Override
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            });
        } else if (order.equals(SortOrder.ASCENDING)) {
            Collections.sort(list, new Comparator<Entry<String, Integer>>() {
                @Override
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                    return (o1.getValue().compareTo(o2.getValue()));
                }
            });
        }

        for (Entry<String, Integer> word : list) {
            wordList.add(word.getKey() + ": " + word.getValue() + "\n");
        }

        return wordList;
    }

    @Override
    public void output(String outputPath, String order, int range, IOHandler handler) {
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