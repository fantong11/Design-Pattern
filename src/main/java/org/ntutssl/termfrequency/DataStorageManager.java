package org.ntutssl.termfrequency;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class DataStorageManager {
    private List<String> words;

    public DataStorageManager(String filePath) {
        this.words = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.useDelimiter("[\\W_]+");
            while (scanner.hasNext()) {
                this.words.add(scanner.next().toLowerCase());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File is not exist!");
        }
    }

    public List<String> getWords(){
        return this.words;
    }
}