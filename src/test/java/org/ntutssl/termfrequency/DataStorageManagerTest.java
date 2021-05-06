package org.ntutssl.termfrequency;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DataStorageManagerTest {

    @Test
    public void testGetWords() {
        EventManager manager = new EventManager();
        DataStorageManager dsm = new DataStorageManager(manager);
        
        manager.publish(EventType.LOAD, "input/pride-and-prejudice.txt");
       
        List<String> words = new ArrayList<>(dsm.getWords());
       
        assertTrue(words.contains("mr"));
    }

    @Test
    public void testLoadStopWords() {
        EventManager manager = new EventManager();
        DataStorageManager dsm = new DataStorageManager(manager);
        
        manager.publish(EventType.LOAD, "input/stop_words.txt");
       
        List<String> words = new ArrayList<>(dsm.getWords());
       
        assertTrue(words.contains("you"));
    }

    @Test
    public void testRun() {
        EventManager manager = new EventManager();
        DataStorageManager dsm = new DataStorageManager(manager);
        StopWordManager swm = new StopWordManager(manager);
        WordFrequencyManager wfm = new WordFrequencyManager(manager);
        
        manager.publish(EventType.LOAD, "input/pride-and-prejudice.txt");
        manager.publish(EventType.RUN, "");

        List<String> words = new ArrayList<>(dsm.getWords());
        List<String> stopwords = new ArrayList<>(swm.getStopWords());

        assertTrue(words.contains("mr"));
        assertTrue(stopwords.contains("the"));

    }
}
