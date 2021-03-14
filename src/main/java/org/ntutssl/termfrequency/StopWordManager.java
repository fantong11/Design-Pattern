package org.ntutssl.termfrequency;

import java.util.List;
import java.util.Set;

public class StopWordManager implements IStopWordManager {
    public List<String> stopWordList;
    private Set<String> stopWordSet;

    public StopWordManager(String filePath, IOHandler ioHandler) {
        stopWordList = ioHandler.handleInputAsList(filePath, ",");
        stopWordSet = ioHandler.handleInputAsSet(filePath, ",");

        // 把其他單獨字元都加進stopWordList和stopWordSet
        for (char c = 33; c <= 126; c++) {
            this.stopWordList.add(Character.toString(c));
            this.stopWordSet.add(Character.toString(c));
        }
    }

    @Override
    public boolean isStopWordList(String word) {
        return this.stopWordList.contains(word);
    }
             
    @Override
    public boolean isStopWordSet(String word) {
        return this.stopWordSet.contains(word);
    }
}