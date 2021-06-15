package org.ntutssl.termfrequency;

import java.util.ArrayList;
import java.util.List;

public class DataStorageManager implements IDataStorageManager {
    private List<String> words;

    public DataStorageManager(String filePath, IOHandler ioHandler) {
        this.words = new ArrayList<>();
        this.words = ioHandler.handleInputAsList(filePath, "[\\W_]+");
    }

    @Override
    public List<String> getWords() {
        return this.words;
    }
}