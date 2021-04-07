package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NullIteratorTest {
    @Test
    public void hasNextTest() {
        HtmlOutputVisitor hov = new HtmlOutputVisitor();
        Document article1 = new Article("output", 1);

        Document h1 = new Title("Design Patterns", 1);
        Document p1 = new Paragraph(
                "This course discusses issues in principles of object-oriented design through design patterns.");
        Document article2 = new Article("Course Design Pattern", 2);

        article1.add(h1);
        article1.add(p1);
        article1.add(article2);
        article1.accept(hov);

        Iterator<Document> it = article1.iterator();

        assertTrue(it.hasNext());
        assertEquals("Design Patterns", it.next().getText());
        assertTrue(it.hasNext());
        assertEquals("This course discusses issues in principles of object-oriented design through design patterns.",
                it.next().getText());
        assertTrue(it.hasNext());
        assertEquals("Course Design Pattern", it.next().getText());
        assertFalse(it.hasNext());
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void noSuchElementExceptionTest() {
        expectedEx.expect(NoSuchElementException.class);

        HtmlOutputVisitor hov = new HtmlOutputVisitor();
        Document article1 = new Article("output", 1);

        Document h1 = new Title("Design Patterns", 1);
        Document p1 = new Paragraph(
                "This course discusses issues in principles of object-oriented design through design patterns.");
        Document article2 = new Article("Course Design Pattern", 2);

        article1.add(h1);
        article1.add(p1);
        article1.add(article2);
        article1.accept(hov);

        Iterator<Document> it = article1.iterator();

        while (true) {
            it.next();
        }
    }
}