package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HtmlOutputVisitorTest {
    HtmlOutputVisitor hov;
    String result;

    @Before
    public void setUp() {
        hov = new HtmlOutputVisitor();
        result = "";
    }

    @After
    public void tearDown() {
        hov = null;
    }

    @Test
    public void visitParagraphTest() {
        Document paragraph = new Paragraph("design pattern");
        paragraph.accept(hov);
        result = hov.getResult();
        assertEquals("<p>design pattern</p>\n", result);
    }

    @Test
    public void visitTitleTest() {
        Document title = new Title("design pattern", 1);
        title.accept(hov);
        result = hov.getResult();
        assertEquals("<h1>design pattern</h1>\n", result);
    }

    @Test
    public void visitTitleSizeTest() {
        Document title = new Title("design pattern", 3);
        title.accept(hov);
        result = hov.getResult();
        assertEquals("<h3>design pattern</h3>\n", result);
    }

    @Test
    public void visitArticleTest() {
        Document article1 = new Article("output", 1);

        Document h1 = new Title("Design Patterns", 1);
        Document p1 = new Paragraph("This course discusses issues in principles of object-oriented design through design patterns.");
        Document article2 = new Article("Course Design Pattern", 2);
        Document h2 = new Title("Information", 2);
        Document p2 = new Paragraph("Professor: YC Cheng");
        Document article3 = new Article("Introduction", 3);
        Document h3 = new Title("What is design pattern?", 3);
        Document p3 = new Paragraph("Design pattern are solutions to general problems that appear repeatedly in software engineering.");
        Document h22 = new Title("References", 2);
        Document p22 = new Paragraph("Gamma, Erich, et al. Design patterns: elements of reusable object-oriented software. Pearson Education, 1994.");

        article3.add(h3);
        article3.add(p3);
        article2.add(h2);
        article2.add(p2);
        article2.add(article3);
        article2.add(h22);
        article2.add(p22);
        article1.add(h1);
        article1.add(p1);
        article1.add(article2);
        article1.accept(hov);
        String result = hov.getResult();
        System.out.println(result);
        String expectedResult 
            = "<article topic='output'>\n"
            + "  <h1>Design Patterns</h1>\n"
            + "  <p>This course discusses issues in principles of object-oriented design through design patterns.</p>\n"
            + "  <article topic='Course Design Pattern'>\n"
            + "    <h2>Information</h2>\n"
            + "    <p>Professor: YC Cheng</p>\n"
            + "    <article topic='Introduction'>\n"
            + "      <h3>What is design pattern?</h3>\n"
            + "      <p>Design pattern are solutions to general problems that appear repeatedly in software engineering.</p>\n"
            + "    </article>\n"
            + "    <h2>References</h2>\n"
            + "    <p>Gamma, Erich, et al. Design patterns: elements of reusable object-oriented software. Pearson Education, 1994.</p>\n"
            + "  </article>\n"
            + "</article>\n";
        assertEquals(expectedResult, result);
    }
}
