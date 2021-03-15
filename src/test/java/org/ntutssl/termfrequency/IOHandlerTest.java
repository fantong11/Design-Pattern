package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class IOHandlerTest {
    IOHandler handler;
    List<String> list;
    Set<String> set;

    @Before
    public void setUp() {
        handler = new IOHandler();
        list = new ArrayList<>();
        set = new HashSet<>();
    }

    @Test
    public void inputFirstStopWordListTest() {
        list = handler.handleInputAsList("input/stop_words.txt", ",");
        assertEquals("a", list.get(0));
    }

    @Test
    public void inputLastStopWordListTest() {
        list = handler.handleInputAsList("input/stop_words.txt", ",");
        assertEquals("your", list.get(list.size() - 1));
    }

    @Test
    public void inputFirstStopWordSetTest() {
        set = handler.handleInputAsSet("input/stop_words.txt", ",");
        assertTrue(set.contains("a"));
    }

    @Test
    public void inputNotStopWordSetTest() {
        set = handler.handleInputAsSet("input/stop_words.txt", ",");
        assertFalse(set.contains("!"));
    }

    @Test(expected = WordFrequencyException.class)
    public void inValidFilePathListTest() {
        handler.handleInputAsList("filePath", ",");
    }

    @Test(expected = WordFrequencyException.class)
    public void inValidFilePathSetTest() {
        handler.handleInputAsSet("filePath", ",");
    }
}
