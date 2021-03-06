package org.ntutssl.termfrequency;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.Instant;

import org.junit.Test;

public class StopWordManagerTest {
    @Test
    public void testIsStopWord() {
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt", "List");
        assertTrue(stopWordManager.isStopWordSet("the"));
    }

    @Test
    public void testIsFirstStopWord() {
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt", "List");
        assertTrue(stopWordManager.isStopWordSet("a"));
    }

    @Test
    public void testIsLastStopWord() {
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt", "List");
        assertTrue(stopWordManager.isStopWordSet("your"));
    }

    @Test
    public void testSingleCharacterStopWord() {
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt", "List");
        for (char c = 33; c <= 126; c++) {
            assertTrue(stopWordManager.isStopWordSet(Character.toString(c)));
        }

    }

    @Test
    public void testListAndSetExecutionTime() {
        StopWordManager stopWordManager = new StopWordManager("input/stop_words.txt", "List");
        Instant start = Instant.now();
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }
}