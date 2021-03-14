package org.ntutssl.termfrequency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class IOHandler {
    private List<String> wordList;
    private Set<String> wordSet;

    public IOHandler() {}

    public List<String> handleInputAsList(String filePath, String pattern) {
        this.wordList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.useDelimiter(pattern);
            while (scanner.hasNext()) {
                this.wordList.add(scanner.next().toLowerCase());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
        return this.wordList;
    }

    public Set<String> handleInputAsSet(String filePath, String pattern) {
        this.wordSet = new HashSet<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.useDelimiter(pattern);
            while (scanner.hasNext()) {
                this.wordSet.add(scanner.next().toLowerCase());
            }
        } catch (FileNotFoundException ex) {
            throw new WordFrequencyException("File not found.", ex);
        }
        return this.wordSet;
    }

    public void handleOutput(String outputPath, int range, List<String> data) {
        File file = new File(outputPath);
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < data.size(); i++) {
                if (i >= range) break;
                br.write(data.get(i));
            }
        } catch (IOException ex) {
            throw new WordFrequencyException("Path have some problem!", ex);
        }
    }
}