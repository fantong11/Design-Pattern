package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.SortOrder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WordFrequencyManagerStreamTest {
    private IWordFrequencyManager wfm;
    private List<String> wordList;

    @Before
    public void setUp() {
        wfm = new WordFrequencyManagerStream();
        wordList = new ArrayList<>();
    }

    @After
    public void tearDown() {
        wfm = null;
        wordList = null;
    }

    @Test
    public void numofWordsTest() {
        wfm.incrementCount("test1");
        wfm.incrementCount("test2");
        wfm.incrementCount("test3");
        assertEquals(3, wfm.getNumOfWords());
    }

    @Test
    public void zeroWordTest() {
        assertEquals(0, wfm.getNumOfWords());
    }

    @Test
    public void repeatWordsTest() {
        wfm.incrementCount("test3");
        wfm.incrementCount("test1");
        wfm.incrementCount("test3");
        wfm.incrementCount("test2");
        wfm.incrementCount("test3");
        assertEquals(3, wfm.getNumOfWords());
    }

    @Test
    public void wordFreqAscTest() {
        wordList.add("cat: 1");
        wordList.add("bear: 2");
        wordList.add("dog: 3");
        wfm.incrementCount("dog");
        wfm.incrementCount("cat");
        wfm.incrementCount("dog");
        wfm.incrementCount("bear");
        wfm.incrementCount("bear");
        wfm.incrementCount("dog");
        assertEquals(wordList, wfm.getWordFrequency(SortOrder.ASCENDING));
    }

    // @Test
    // public void wordFreqDesTest() {
    //     wordList.add("dog: 3");
    //     wordList.add("bear: 2");
    //     wordList.add("cat: 1");
    //     wfm.incrementCount("dog");
    //     wfm.incrementCount("cat");
    //     wfm.incrementCount("dog");
    //     wfm.incrementCount("bear");
    //     wfm.incrementCount("bear");
    //     wfm.incrementCount("dog");
        
    //     for (Entry<String, Integer> word : wfm.getWords().entrySet()) {
    //         System.out.println(word.getKey() + " " + word.getValue());
    //     }

    //     assertEquals(wordList, wfm.getWordFrequency(SortOrder.DESCENDING));
    // }
}
