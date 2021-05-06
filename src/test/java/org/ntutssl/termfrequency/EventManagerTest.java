package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EventManagerTest {
    @Test
    public void eventTest() {
        EventManager manager = new EventManager();
        new WordFrequencyController(manager);
        DataStorageManager dataStorageManager = new DataStorageManager(manager);
        StopWordManager stopWordManager = new StopWordManager(manager);
        WordFrequencyManager wordFrequencyManager = new WordFrequencyManager(manager);
        new Output(manager, 5, "des");

        manager.publish(EventType.START, "input/pride-and-prejudice.txt");
        assertTrue(dataStorageManager.getWords().contains("elizabeth"));
        assertTrue(stopWordManager.getStopWords().contains("a"));
        assertEquals(6479, wordFrequencyManager.getNumOfWords());
    }
}