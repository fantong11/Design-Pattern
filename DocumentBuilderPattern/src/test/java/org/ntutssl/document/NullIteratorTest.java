package org.ntutssl.document;

import static org.junit.Assert.assertFalse;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NullIteratorTest {
    Paragraph paragraph;
    Title title;
    Article article;

    @Before
    public void setUp() {
        paragraph = new Paragraph("test");
        title = new Title("test", 1);
        article = new Article("test", 1);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void paragraphIteratorNextExceptionTest() {
        expectedEx.expect(NoSuchElementException.class);
        Iterator<Document> it = paragraph.iterator();
        it.next();
    }

    @Test
    public void paragraphIteratorHasNextExceptionTest() {
        Iterator<Document> it = paragraph.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void titleIteratorNextExceptionTest() {
        expectedEx.expect(NoSuchElementException.class);
        Iterator<Document> it = title.iterator();
        it.next();
    }

    @Test
    public void titleIteratorHasNextExceptionTest() {
        Iterator<Document> it = title.iterator();
        assertFalse(it.hasNext());
    }
}
