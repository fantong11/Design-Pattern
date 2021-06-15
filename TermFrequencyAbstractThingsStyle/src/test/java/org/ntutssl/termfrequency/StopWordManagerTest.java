package org.ntutssl.termfrequency;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StopWordManagerTest {
    IOHandler handler;
    IStopWordManager swm;

    @Before
    public void setUp() {
        handler = new IOHandler();
        swm = new StopWordManager("input/stop_words.txt", handler);
    }

    @After
    public void tearDown() {
        handler = null;
        swm = null;
    }

    @Test
    public void containFirstWordListTest() {
        assertTrue(swm.isStopWordList("a"));
    }

    @Test
    public void containMiddleWordListTest() {
        assertTrue(swm.isStopWordList("among"));
    }

    @Test
    public void containLastWordListTest() {
        assertTrue(swm.isStopWordList("your"));
    }

    @Test
    public void noContainWordListTest() {
        assertFalse(swm.isStopWordList("project"));
    }

    @Test
    public void containFirstWordSetTest() {
        assertTrue(swm.isStopWordSet("a"));
    }

    @Test
    public void containMiddleWordSetTest() {
        assertTrue(swm.isStopWordList("when"));
    }

    @Test
    public void containLastWordSetTest() {
        assertTrue(swm.isStopWordSet("your"));
    }

    @Test
    public void noContainWordSetTest() {
        assertFalse(swm.isStopWordSet("ebooks"));
    }

    @Test
    public void alphabetStopWordListTest() {
        assertTrue(swm.isStopWordList("a"));
    }

    @Test
    public void alphabetStopWordSetTest() {
        assertTrue(swm.isStopWordList("a"));
    }

    @Test
    public void specialCharStopWordListTest() {
        assertTrue(swm.isStopWordList(")"));
    }

    @Test
    public void specialCharStopWordSetTest() {
        assertTrue(swm.isStopWordList(")"));
    }

    @Test
    public void numberStopWordListTest() {
        assertTrue(swm.isStopWordList("8"));
    }

    @Test
    public void numberStopWordSetTest() {
        assertTrue(swm.isStopWordList("8"));
    }

    @Test
    public void notStopWordListTest() {
        assertFalse(swm.isStopWordList("ebooks"));
        assertFalse(swm.isStopWordList("facility"));
        assertFalse(swm.isStopWordList("originator"));
        assertFalse(swm.isStopWordList("project"));
        assertFalse(swm.isStopWordList("copyright"));
    }
}
