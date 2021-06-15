package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import javax.swing.SortOrder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WordFrequencyManagerTest {
    private IWordFrequencyManager wfm;
    IOHandler handler;
    private List<String> wordList;

    @Before
    public void setUp() {
        wfm = new WordFrequencyManager();
        wordList = new LinkedList<>();
        handler = new IOHandler();
    }

    @After
    public void tearDown() {
        wfm = null;
        wordList = null;
        handler = null;
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
        wordList.add("test1: 1\n");
        wordList.add("test2: 2\n");
        wordList.add("test3: 3\n");
        wfm.incrementCount("test3");
        wfm.incrementCount("test1");
        wfm.incrementCount("test3");
        wfm.incrementCount("test2");
        wfm.incrementCount("test2");
        wfm.incrementCount("test3");
        assertEquals(wordList, wfm.getWordFrequency(SortOrder.ASCENDING));
    }

    @Test
    public void wordFreqDesTest() {
        wordList.add("test3: 3\n");
        wordList.add("test2: 2\n");
        wordList.add("test1: 1\n");
        wfm.incrementCount("test3");
        wfm.incrementCount("test1");
        wfm.incrementCount("test3");
        wfm.incrementCount("test2");
        wfm.incrementCount("test2");
        wfm.incrementCount("test3");
        assertEquals(wordList, wfm.getWordFrequency(SortOrder.DESCENDING));
    }

    @Test(expected = WordFrequencyException.class)
    public void wordNotFoundTest() {
        wfm.output("output/output.txt", "asc", 10, handler);
    }

    @Test(expected = WordFrequencyException.class)
    public void rangeHightThanLimitTest() {
        wfm.incrementCount("test1");
        wfm.incrementCount("test2");
        wfm.incrementCount("test3");
        wfm.output("output/output.txt", "asc", 10, handler);
    }

    @Test(expected = WordFrequencyException.class)
    public void rangeLowThanLimitTest() {
        wfm.incrementCount("test1");
        wfm.incrementCount("test2");
        wfm.incrementCount("test3");
        wfm.output("output/output.txt", "asc", -20, handler);
    }

    @Test(expected = WordFrequencyException.class)
    public void inValidOrderTest() {
        wfm.incrementCount("test1");
        wfm.incrementCount("test2");
        wfm.incrementCount("test3");
        wfm.output("output/output.txt", "abc", 2, handler);
    }
}
