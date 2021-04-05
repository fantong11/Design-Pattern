package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditorTest {
    Editor editor;
    FindContentVisitor fcv;

    @Before
    public void setUp() {
        editor = new Editor();
        fcv = new FindContentVisitor("Design");
    }

    @After
    public void tearDown() {
        editor = null;
        fcv = null;
    }

    @Test
    public void sizeTest() {
        Document article = new Article("Course Design Pattern", 1);
        Document article2 = new Article("Design Patterns", 2);
        Document title = new Title("Design pattern are solutions to general problems that appear repeatedly in software engineering.", 20);
        Document paragragh = new Paragraph("This course discusses design patterns.");
        Document article3 = new Article("Design", 3);
        editor.add(article);
        editor.add(article2);
        editor.add(title);
        editor.add(paragragh);
        editor.add(article3);
        
        assertEquals(5, editor.size());
    }

    @Test
    public void iteratorTest() {
        Document article = new Article("Course Design Pattern", 1);
        Document article2 = new Article("Design Patterns", 2);
        Document title = new Title("Design pattern are solutions to general problems that appear repeatedly in software engineering.", 20);
        Document paragragh = new Paragraph("This course discusses design patterns.");
        Document article3 = new Article("Design", 3);
        editor.add(article);
        editor.add(article2);
        editor.add(title);
        editor.add(paragragh);
        editor.add(article3);

        Iterator<Document> it = editor.iterator();

        assertTrue(it.hasNext());
        assertEquals("Course Design Pattern", it.next().getText());
        assertTrue(it.hasNext());
        assertEquals("Design Patterns", it.next().getText());
        assertTrue(it.hasNext());
        assertEquals("Design pattern are solutions to general problems that appear repeatedly in software engineering.", it.next().getText());
        assertTrue(it.hasNext());
        assertEquals("This course discusses design patterns.", it.next().getText());
        assertTrue(it.hasNext());
        assertEquals("Design", it.next().getText());
        assertFalse(it.hasNext());
    }

    @Test
    public void visitorTest() {
        Document article = new Article("Course Design Pattern", 1);
        Document article2 = new Article("Design Patterns", 2);
        Document title = new Title("Design pattern are solutions to general problems that appear repeatedly in software engineering.", 20);
        Document paragragh = new Paragraph("This course discusses design patterns.");
        Document article3 = new Article("Design", 3);
        editor.add(article);
        editor.add(article2);
        editor.add(title);
        editor.add(paragragh);
        editor.add(article3);

        Iterator<Document> it = editor.iterator();

        while (it.hasNext()) {
            Document document = it.next();
            document.accept(fcv);            
        }

    }
}