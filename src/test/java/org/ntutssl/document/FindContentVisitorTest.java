package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FindContentVisitorTest {
    FindContentVisitor fcv;

    @Before
    public void setUp() {
        fcv = new FindContentVisitor("design");
    }

    @After
    public void tearDown() {
        fcv = null;
    }

    @Test
    public void visitArticleTest() {
        Document article = new Article("Course Design Pattern", 1);
        Document article2 = new Article("Design Patterns", 2);
        Document paragragh = new Paragraph("This course discusses design patterns.");
        Document article3 = new Article("Design", 3);
        article2.add(new Article("design slkjfaslkfjlk;sdajf;lsz", 3));
        article.add(article2);
        article.add(paragragh);
        article.add(article3);
        
        article.accept(fcv);
        List<Document> result = fcv.getResult();
        System.out.println(result);
        assertEquals("Course Design Pattern", result.get(0).getText());
        // assertEquals("Design", result.get(1).getText());
    }

    @Test
    public void visitParagraphTest() {
        Document paragragh = new Paragraph("This course discusses design patterns.");
        paragragh.accept(fcv);
        List<Document> result = fcv.getResult();
        assertEquals("This course discusses design patterns.", result.get(0).getText());
    }

    @Test
    public void visitTitleTest() {
        Document title = new Title("Design patterns", 20);
        title.accept(fcv);
        List<Document> result = fcv.getResult();
        assertEquals("Design patterns", result.get(0).getText());
    }
}
