package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TitleTest {
    Title title;

    @Before
    public void setUp() {
        title = new Title("test");
    }

    @After
    public void tearDown() {
        title = null;
    }

    @Test
    public void getTextTest() {
        assertEquals("test", title.getText());
    }

    @Test
    public void addTest() {
        boolean thrown = false;
        try {
            title.add(new Article("topic", 10));
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
