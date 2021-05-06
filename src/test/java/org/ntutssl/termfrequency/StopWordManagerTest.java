package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StopWordManagerTest {
    @Test
    public void testGetStopWords() {
        EventManager manager = new EventManager();
        StopWordManager swm = new StopWordManager(manager);

        manager.publish(EventType.LOAD, "");
        
        List<String> words = new ArrayList<>(swm.getStopWords());

        assertTrue(words.contains("the"));
    }

    @Test
    public void testValidate() {
        EventManager manager = new EventManager();
        StopWordManager swm = new StopWordManager(manager);
        WordFrequencyManager wfm = new WordFrequencyManager(manager);

        manager.publish(EventType.VALIDATE, "name");

        assertEquals(1, wfm.getNumOfWords());
    }
}
