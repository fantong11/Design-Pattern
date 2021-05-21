package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import javax.naming.TimeLimitExceededException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TitleTest {
    Title title;

    @Before
    public void setUp() {
        title = new Title("test", 20);
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

    @Test
    public void toStringTest() {
        assertEquals("Title\t\ttext: test\n\t\tsize: 20\n", title.toString());
    }

    @Test
    public void nullIteratorTest() {
        assertFalse(title.iterator().hasNext());
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void getLevelTest() {
        expectedEx.expect(DocumentException.class);
        expectedEx.expectMessage("Invalid action: getLevel");
        title.getLevel();
    }

    @Test
    public void addArticleExceptionTest() {
        expectedEx.expect(DocumentException.class);
        expectedEx.expectMessage("Invalid action: add");
        title.add(new Article("topic", 10));
    }

    @Test
    public void addTitleExceptionTest() {
        expectedEx.expect(DocumentException.class);
        expectedEx.expectMessage("Invalid action: add");
        title.add(new Title("title", 10));
    }

    @Test
    public void addParagraphExceptionTest() {
        expectedEx.expect(DocumentException.class);
        expectedEx.expectMessage("Invalid action: add");
        title.add(new Paragraph("paragraph"));
    }

    @Test(expected = DocumentException.class)
    public void removeExceptionTest() {
        title.remove(new Title("title2", 1));
    }
}
