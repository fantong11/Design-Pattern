package org.ntutssl.termfrequency;

import java.util.List;
import java.util.Map;

import javax.swing.SortOrder;

public interface IWordFrequencyManager {
    public void incrementCount(String word);
    public int getNumOfWords();
    public List<String> getWordFrequency(SortOrder order);
    public Map<String, Integer> getWords();
    public void output(String outputPath, String order, int range, IOHandler handler);
}