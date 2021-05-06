package org.ntutssl.termfrequency;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DataStorageManagerTest {

    @Test
    public void testGetWords() {
        EventManager eventManager = new EventManager();
        DataStorageManager dataStorageManager = new DataStorageManager(eventManager);

        eventManager.publish(EventType.LOAD, "input/pride-and-prejudice.txt");
        assertTrue(dataStorageManager.getWords().contains("very"));
    }

    @Test
    public void testLoadStopWords() {
        EventManager eventManager = new EventManager();
        DataStorageManager dataStorageManager = new DataStorageManager(eventManager);

        eventManager.publish(EventType.LOAD, "input/stop_words.txt");
        assertTrue(dataStorageManager.getWords().contains("you"));
    }

    @Test
    public void runTest() {
        EventManager eventManager = new EventManager();
        DataStorageManager dataStorageManager = new DataStorageManager(eventManager);
        StopWordManager stopWordManager = new StopWordManager(eventManager);
        new WordFrequencyManager(eventManager);
        
        eventManager.publish(EventType.LOAD, "input/pride-and-prejudice.txt");
        eventManager.publish(EventType.RUN, "");
        assertTrue(dataStorageManager.getWords().contains("darcy"));
        assertTrue(stopWordManager.getStopWords().contains("a"));

    }
}
