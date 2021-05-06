package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WordFrequencyManagerTest {
    @Test
    public void addWordTest() {
        EventManager eventManager = new EventManager();
        WordFrequencyManager wordFrequencyManager = new WordFrequencyManager(eventManager);
        
        wordFrequencyManager.onEvent(EventType.COUNT, "test");

        assertEquals(1, wordFrequencyManager.getNumOfWords());
    }

    @Test
    public void addSameWordTest() {
        EventManager eventManager = new EventManager();
        WordFrequencyManager wordFrequencyManager = new WordFrequencyManager(eventManager);

        eventManager.publish(EventType.COUNT, "design");
        eventManager.publish(EventType.COUNT, "pattern");
        eventManager.publish(EventType.COUNT, "design");
        eventManager.publish(EventType.COUNT, "pattern");

        assertEquals(2, wordFrequencyManager.getNumOfWords());
    }
}
