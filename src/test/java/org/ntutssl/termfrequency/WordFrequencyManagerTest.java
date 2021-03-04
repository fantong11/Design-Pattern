package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WordFrequencyManagerTest{
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
}