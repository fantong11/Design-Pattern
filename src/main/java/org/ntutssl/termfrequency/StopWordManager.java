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

    public StopWordManager(String filePath, String dataStructureType) {
        if (dataStructureType.equals("List")) storeStopWordList(filePath);
        else if (dataStructureType.equals("Set")) storeStopWordSet(filePath);
    }

    public void storeStopWordList(String filePath) {
        this.stopWordList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                this.stopWordList.add(scanner.next());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File is not exist!");
        }

        // 把其他單獨字元都加進stopWordList
        for (char c = 33; c <= 126; c++) {
            this.stopWordList.add(Character.toString(c));
        }
    }

    public void storeStopWordSet(String filePath) {
        this.stopWordSet = new HashSet<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                this.stopWordSet.add(scanner.next());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File is not exist!");
        }

        // 把其他單獨字元都加進stopWordSet
        for (char c = 33; c <= 126; c++) {
            this.stopWordSet.add(Character.toString(c));
        }
    }

    public boolean isStopWordList(String word) {
        return this.stopWordList.contains(word);
    }

    public boolean isStopWordSet(String word) {
        return this.stopWordSet.contains(word);
    }
}