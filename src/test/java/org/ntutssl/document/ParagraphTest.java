package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParagraphTest {
    Paragraph paragraph;

    @Before
    public void setUp() {
        paragraph = new Paragraph("test");
    }

    @After
    public void tearDown() {
        paragraph = null;
    }

    @Test
    public void getTextTest() {
        assertEquals("test", paragraph.getText());
    }
}
