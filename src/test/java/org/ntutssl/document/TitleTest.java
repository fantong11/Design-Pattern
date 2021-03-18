package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TitleTest {
    private Title title;

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
}
