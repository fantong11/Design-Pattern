package org.ntutssl.termfrequency;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataStorageManagerTest {
    private IOHandler handler;
    private IDataStorageManager swm;
    private List<String> words;

    @Before
    public void setUp() {
        handler = new IOHandler();
        swm = new DataStorageManager("input/pride-and-prejudice.txt", handler);
        words = new ArrayList<>();
    }

    @After
    public void tearDown() {
        handler = null;
        swm = null;
        words = null;
    }

    @Test
    public void containFirstWordTest() {
        words = swm.getWords();
        assertEquals("the", words.get(0));
    }

    @Test
    public void containLastWordTest() {
        words = swm.getWords();
        assertEquals("ebooks", words.get(words.size() - 1));
    }

}
