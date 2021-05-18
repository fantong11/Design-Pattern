package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    public void toStringTest() {
        assertEquals("Paragraph\ttext: test\n", paragraph.toString());
    }

    @Test
    public void nullIterator() {
        assertFalse(paragraph.iterator().hasNext());
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void getLevelTest() {
        expectedEx.expect(DocumentException.class);
        expectedEx.expectMessage("Invalid action: getLevel");
        paragraph.getLevel();
    }

    @Test
    public void addArticleExceptionTest() {
        expectedEx.expect(DocumentException.class);
        expectedEx.expectMessage("Invalid action: add");
        paragraph.add(new Article("topic", 10));
    }

    @Test
    public void addTitleExceptionTest() {
        expectedEx.expect(DocumentException.class);
        expectedEx.expectMessage("Invalid action: add");
        paragraph.add(new Title("title", 10));
    }

    @Test
    public void addParagraphExceptionTest() {
        expectedEx.expect(DocumentException.class);
        expectedEx.expectMessage("Invalid action: add");
        paragraph.add(new Paragraph("paragraph"));
    }

    @Test
    public void getSizeTest() {
        expectedEx.expect(DocumentException.class);
        expectedEx.expectMessage("Invalid action: getSize");
        paragraph.getSize();
    }
}
