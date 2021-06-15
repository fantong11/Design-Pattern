package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

public class StopWordManagerTest {
    @Test
    public void testGetStopWords() {
        EventManager eventManager = new EventManager();
        StopWordManager stopWordManager = new StopWordManager(eventManager);
        eventManager.publish(EventType.LOAD, "");
        assertTrue(stopWordManager.getStopWords().contains("a"));
    }

    @Test
    public void testValidate() {
        EventManager eventManager = new EventManager();
        new StopWordManager(eventManager);
        WordFrequencyManager wordFrequencyManager = new WordFrequencyManager(eventManager);

        eventManager.publish(EventType.VALIDATE, "the");
        eventManager.publish(EventType.VALIDATE, "design");

        assertEquals(2, wordFrequencyManager.getNumOfWords());
    }
}
