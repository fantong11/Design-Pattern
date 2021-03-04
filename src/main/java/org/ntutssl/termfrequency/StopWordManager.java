package org.ntutssl.termfrequency;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class StopWordManager {
    private List<String> stopWordList;
    private Set<String> stopWordSet;

    public StopWordManager(String filePath) {
        this.stopWordList = new ArrayList<>();
        this.stopWordSet = new HashSet<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                this.stopWordSet.add(scanner.next());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File is not exist!");
        }
    }

    public boolean isStopWordList(String word){
        return this.stopWordList.contains(word);
    }

    public boolean isStopWordSet(String word){
        return this.stopWordSet.contains(word);
    }
}