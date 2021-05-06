package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WordFrequencyManagerTest {
    @Test
    public void testIncrementCount() {
        EventManager manager = new EventManager();
        WordFrequencyManager wfm = new WordFrequencyManager(manager);
        
        wfm.onEvent(EventType.COUNT, "a");

        assertEquals(1, wfm.getNumOfWords());
    }

    @Test
    public void testGetNumOfWords() {
        EventManager manager = new EventManager();
        WordFrequencyManager wfm = new WordFrequencyManager(manager);

        manager.publish(EventType.COUNT, "a");
        manager.publish(EventType.COUNT, "b");
        manager.publish(EventType.COUNT, "a");

        assertEquals(2, wfm.getNumOfWords());
    }

    // @Test
    // public void testResult() {
    //     EventManager manager = new EventManager();
    //     WordFrequencyManager wfm = new WordFrequencyManager(manager);

    //     manager.publish(EventType.COUNT, "design");
    //     manager.publish(EventType.COUNT, "pattern");
    //     manager.publish(EventType.COUNT, "design");
    //     manager.publish(EventType.EOF, "");

    //     String expected = "pattern: 1\ndesign: 2\n";

    //     assertEquals(expected, wfm.getOutput());
    // }
}
