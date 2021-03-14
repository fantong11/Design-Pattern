package org.ntutssl.termfrequency;

import java.util.List;
import java.util.Map;

import javax.swing.SortOrder;

public interface IWordFrequencyManager {
    public void incrementCount(String word);

    public int getNumOfWords();

    public List<String> getWordFrequency(SortOrder order);

    public Map<String, Integer> getWords();

    default public void output(String outputPath, String order, int range, IOHandler handler) {
        // 檔案裡沒有字
        if (getNumOfWords() == 0)
            throw new WordFrequencyException("Word not found.");
        // 範圍超過上限或低於下限
        if (range > getNumOfWords() || range < 1)
            throw new WordFrequencyException(
                    String.format("Out of range! The range should be from 1 to %d.", getNumOfWords()));

        // 判斷遞增遞減
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