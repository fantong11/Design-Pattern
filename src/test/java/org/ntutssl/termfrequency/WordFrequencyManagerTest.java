package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;

import java.util.Map.Entry;

import org.junit.Test;

public class WordFrequencyManagerTest {
    @Test
    public void testWordsWork() {
        WordFrequencyManager wordFrequencyManager = new WordFrequencyManager();
        wordFrequencyManager.incrementCount("test");
        wordFrequencyManager.incrementCount("cat");
        wordFrequencyManager.incrementCount("dog");
        assertEquals(Integer.valueOf(1), wordFrequencyManager.getCount("test"));
        assertEquals(Integer.valueOf(1), wordFrequencyManager.getCount("cat"));
    }

    @Test
    public void testNumOfWords() {
        WordFrequencyManager wordFrequencyManager = new WordFrequencyManager();
        wordFrequencyManager.incrementCount("test");
        wordFrequencyManager.incrementCount("cat");
        wordFrequencyManager.incrementCount("dog");
        assertEquals(3, wordFrequencyManager.getNumOfWords());

    }

    @Test
    public void testWordFreqDes() {
        DataStorageManager dataStorageManager = new DataStorageManager("input/pride-and-prejudice.txt");
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt");
        WordFrequencyManager wordFrequencyManager = new WordFrequencyManager();

        // 把字加進wfm
        for (String word : dataStorageManager.getWords()) {
            if (!stopWordManager.isStopWordList(word)) {
                wordFrequencyManager.incrementCount(word);
            }
        }

        assertEquals(Integer.valueOf(786), wordFrequencyManager.getWordFrequencyDescending().get("mr"));
        assertEquals(Integer.valueOf(635), wordFrequencyManager.getWordFrequencyDescending().get("elizabeth"));
    }

    @Test
    public void testWordFreqAsc() {
        DataStorageManager dataStorageManager = new DataStorageManager("input/pride-and-prejudice.txt");
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt");
        WordFrequencyManager wordFrequencyManager = new WordFrequencyManager();

        // 把字加進wfm
        for (String word : dataStorageManager.getWords()) {
            if (!stopWordManager.isStopWordList(word)) {
                wordFrequencyManager.incrementCount(word);
            }
        }

        assertEquals(Integer.valueOf(1), wordFrequencyManager.getWordFrequencyDescending().get("nicely"));
        assertEquals(Integer.valueOf(1), wordFrequencyManager.getWordFrequencyDescending().get("costing"));
    }

}