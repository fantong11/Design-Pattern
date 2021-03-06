package org.ntutssl.termfrequency;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WordFrequencyManager {
    private Map<String, Integer> words;

    public WordFrequencyManager() {
        this.words = new HashMap<>();
    }

    public void incrementCount(String word) {
        if (this.words.containsKey(word)) {
            this.words.put(word, this.words.get(word) + 1);
        } else {
            this.words.put(word, 1);
        }
    }

    public int getNumOfWords() {
        return this.words.size();
    }

    public Integer getCount(String word) {
        return this.words.get(word) != null ? this.words.get(word) : 0;
    }

    public Map<String, Integer> getWordFrequencyDescending() {
        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(this.words.entrySet());

        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> word : list) {
            temp.put(word.getKey(), word.getValue());
        }
        return temp;
    }

    public Map<String, Integer> getWordFrequencyAscending(){
        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(this.words.entrySet());

        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> word : list) {
            temp.put(word.getKey(), word.getValue());
        }
        return temp;
    }
}