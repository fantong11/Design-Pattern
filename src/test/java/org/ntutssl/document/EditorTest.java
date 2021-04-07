package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditorTest {
    Editor editor;
    FindContentVisitor fcv;
    HtmlOutputVisitor hov;

    @Before
    public void setUp() {
        editor = new Editor();
        fcv = new FindContentVisitor("design pattern");
        hov = new HtmlOutputVisitor();
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
    public void fcvIteratorTest() {
        Document h1 = new Title("Design Patterns", 1);
        Document p1 = new Paragraph("This course discusses issues in principles of object-oriented design through design patterns.");
        Document article1 = new Article("Course Design Pattern", 1);
        Document h2 = new Title("Information", 2);
        Document p2 = new Paragraph("Professor: YC Cheng");
        Document article2 = new Article("Introduction", 2);
        Document h3 = new Title("What is design pattern?", 3);
        Document p3 = new Paragraph("Design pattern are solutions to general problems that appear repeatedly in software engineering.");
        Document h22 = new Title("References", 2);
        Document p22 = new Paragraph("Gamma, Erich, et al. Design patterns: elements of reusable object-oriented software. Pearson Education, 1994.");

        article2.add(h3);
        article2.add(p3);
        article1.add(h2);
        article1.add(p2);
        article1.add(article2);
        article1.add(h22);
        article1.add(p22);
        editor.add(h1);
        editor.add(p1);
        editor.add(article1);

        Iterator<Document> it = editor.iterator();

        while (it.hasNext()) {
            Document document = it.next();
            document.accept(fcv);
        }

        List<Document> result = fcv.getResult();
        String actualResult = "";
        for (Document document : result) {
            System.out.println(document.toString());
            actualResult += document.toString();
        }
        String expectedResult 
            = "Title\t\ttext: Design Patterns\n"
            + "\t\tsize: 1\n"
            + "Paragraph\ttext: This course discusses issues in principles of object-oriented design through design patterns.\n"
            + "Article\t\ttopic: Course Design Pattern\n"
            + "\t\tlevel: 1\n"
            + "Title\t\ttext: What is design pattern?\n"
            + "\t\tsize: 3\n"
            + "Paragraph\ttext: Design pattern are solutions to general problems that appear repeatedly in software engineering.\n"
            + "Paragraph\ttext: Gamma, Erich, et al. Design patterns: elements of reusable object-oriented software. Pearson Education, 1994.\n";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void hovIteratorTest() {
        Editor editor = new Editor();

        Document h1 = new Title("Design Patterns", 1);
        Document p1 = new Paragraph("This course discusses issues in principles of object-oriented design through design patterns.");
        Document article1 = new Article("Course Design Pattern", 1);
        Document h2 = new Title("Information", 2);
        Document p2 = new Paragraph("Professor: YC Cheng");
        Document article2 = new Article("Introduction", 2);
        Document h3 = new Title("What is design pattern?", 3);
        Document p3 = new Paragraph("Design pattern are solutions to general problems that appear repeatedly in software engineering.");
        Document h22 = new Title("References", 2);
        Document p22 = new Paragraph("Gamma, Erich, et al. Design patterns: elements of reusable object-oriented software. Pearson Education, 1994.");

        article2.add(h3);
        article2.add(p3);
        article1.add(h2);
        article1.add(p2);
        article1.add(article2);
        article1.add(h22);
        article1.add(p22);
        editor.add(h1);
        editor.add(p1);
        editor.add(article1);

        Iterator<Document> it = editor.iterator();

        while (it.hasNext()) {
            Document document = it.next();
            document.accept(hov);
        }

        String result = hov.getResult();
        System.out.println(result);
        String expectedResult 
            = "<h1>Design Patterns</h1>\n"
            + "<p>This course discusses issues in principles of object-oriented design through design patterns.</p>\n"
            + "<article topic='Course Design Pattern'>\n"
            + "  <h2>Information</h2>\n"
            + "  <p>Professor: YC Cheng</p>\n"
            + "  <article topic='Introduction'>\n"
            + "    <h3>What is design pattern?</h3>\n"
            + "    <p>Design pattern are solutions to general problems that appear repeatedly in software engineering.</p>\n"
            + "  </article>\n"
            + "  <h2>References</h2>\n"
            + "  <p>Gamma, Erich, et al. Design patterns: elements of reusable object-oriented software. Pearson Education, 1994.</p>\n"
            + "</article>\n";
        assertEquals(expectedResult, result);
    }
}