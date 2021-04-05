package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void addTest() {
        boolean thrown = false;
        try {
            paragraph.add(new Article("topic", 10));
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void toStringTest() {
        System.out.println(paragraph.toString());
        assertEquals("Paragraph\ttext: test", paragraph.toString());
    }
}
