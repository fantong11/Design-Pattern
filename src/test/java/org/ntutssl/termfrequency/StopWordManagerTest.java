package org.ntutssl.termfrequency;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StopWordManagerTest{
    @Test
    public void testIsStopWord() {
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt");
        assertTrue(stopWordManager.isStopWordSet("the"));
    }
    
    @Test
    public void testIsFirstStopWord() {
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt");
        assertTrue(stopWordManager.isStopWordSet("a"));
    }

    @Test
    public void testIsLastStopWord() {
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt");
        assertTrue(stopWordManager.isStopWordSet("your"));
    }

    @Test
    public void test_single_character_should_be_a_stop_word() {
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt");
        for (char c = 'A'; c <= 'z'; c++) {
            assertTrue(stopWordManager.isStopWordSet(Character.toString(c)));
        }
        
    }
}