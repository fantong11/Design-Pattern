package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class EventManagerTest {
    @Test
    public void testPublish() {
        EventManager manager = new EventManager();
        WordFrequencyController wfc = new WordFrequencyController(manager);
        DataStorageManager dsm = new DataStorageManager(manager);
        StopWordManager swm = new StopWordManager(manager);
        WordFrequencyManager wfm = new WordFrequencyManager(manager);
        Output output = new Output(manager, 5, "des");

        manager.publish(EventType.START, "input/pride-and-prejudice.txt");

        List<String> words = new ArrayList<>(dsm.getWords());
        List<String> stopwords = new ArrayList<>(swm.getStopWords());

        assertTrue(words.contains("mr"));
        assertTrue(stopwords.contains("the"));
        assertEquals(6479, wfm.getNumOfWords());
    }
}