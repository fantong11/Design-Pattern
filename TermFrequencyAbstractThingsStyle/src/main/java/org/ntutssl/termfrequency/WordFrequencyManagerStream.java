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
}