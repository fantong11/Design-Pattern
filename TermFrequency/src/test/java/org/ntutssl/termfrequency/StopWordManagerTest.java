package org.ntutssl.termfrequency;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.Instant;

import org.junit.Test;

public class StopWordManagerTest {
    @Test
    public void testIsStopWord() {
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt");
        assertTrue(stopWordManager.isStopWordList("the"));
    }

    @Test
    public void testIsFirstStopWord() {
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt");
        assertTrue(stopWordManager.isStopWordList("a"));
    }

    @Test
    public void testIsLastStopWord() {
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt");
        assertTrue(stopWordManager.isStopWordList("your"));
    }

    @Test
    public void testSingleCharacterStopWord() {
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt");
        for (char c = 33; c <= 126; c++) {
            assertTrue(stopWordManager.isStopWordList(Character.toString(c)));
        }

    }

    // 比較list的時間和set的時間
    @Test
    public void testListAndSetExecutionTime() {
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt");

        Instant listStart = Instant.now();
        DataStorageManager dataStorageListManager = new DataStorageManager("input/pride-and-prejudice.txt");
        stopWordManager.storeStopWordList("input/stop_words.txt");
        WordFrequencyManager wordFrequencyListManager = new WordFrequencyManager();

        for (String word : dataStorageListManager.getWords()) {
            if (!stopWordManager.isStopWordList(word)) {
                wordFrequencyListManager.incrementCount(word);
            }
        }
        Instant listEnd = Instant.now();

        Instant setStart = Instant.now();
        DataStorageManager dataStorageSetManager = new DataStorageManager("input/pride-and-prejudice.txt");
        stopWordManager.storeStopWordSet("input/stop_words.txt");
        WordFrequencyManager wordFrequencySetManager = new WordFrequencyManager();

        for (String word : dataStorageSetManager.getWords()) {
            if (!stopWordManager.isStopWordSet(word)) {
                wordFrequencySetManager.incrementCount(word);
            }
        }
        Instant setEnd = Instant.now();
        
        assertTrue(Duration.between(listStart, listEnd).compareTo(Duration.between(setStart, setEnd)) > 0);
    }
}