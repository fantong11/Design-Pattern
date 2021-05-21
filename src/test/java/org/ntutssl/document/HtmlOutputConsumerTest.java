package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class HtmlOutputConsumerTest {
    Document title1;
    Document title2;
    Document title3;
    Document paragraph1;
    Document paragraph2;
    Document paragraph3;
    Document article1;
    Document article2;
    Document article3;

    @Before
    public void setUp() {
        title1 = new Title("title1", 2);
        title2 = new Title("title2", 3);
        title3 = new Title("title3", 4);
        paragraph1 = new Paragraph("paragraph1");
        paragraph2 = new Paragraph("paragraph2");
        paragraph3 = new Paragraph("paragraph3");
        article1 = new Article("article1", 1);
        article2 = new Article("article2", 2);
        article3 = new Article("article3", 3);
    }

    @Test
    public void titleTest() {
        List<Document> documents = new ArrayList<>();
        List<String> result = new ArrayList<>();
        HtmlOutputConsumer hoc = new HtmlOutputConsumer(result);

        documents.add(title1);

        documents.iterator().forEachRemaining(hoc);

        assertEquals("<h2>title1</h2>\n", result.get(0));
    }

    @Test
    public void titleLargerSizeTest() {
        List<Document> documents = new ArrayList<>();
        List<String> result = new ArrayList<>();
        HtmlOutputConsumer hoc = new HtmlOutputConsumer(result);

        documents.add(title2);

        documents.iterator().forEachRemaining(hoc);

        assertEquals("<h3>title2</h3>\n", result.get(0));
    }

    @Test
    public void paragraphTest() {
        List<Document> documents = new ArrayList<>();
        List<String> result = new ArrayList<>();
        HtmlOutputConsumer hoc = new HtmlOutputConsumer(result);

        documents.add(paragraph1);

        documents.iterator().forEachRemaining(hoc);

        assertEquals("<p>paragraph1</p>\n", result.get(0));
    }

    @Test
    public void articleTest() {
        article1.add(title1);
        article1.add(paragraph1);

        List<Document> documents = new ArrayList<>();
        List<String> result = new ArrayList<>();
        HtmlOutputConsumer hoc = new HtmlOutputConsumer(result);

        documents.add(article1);

        documents.iterator().forEachRemaining(hoc);

        assertEquals("<article topic='article1'>\n", result.get(0));
        assertEquals("  <h2>title1</h2>\n", result.get(1));
        assertEquals("  <p>paragraph1</p>\n", result.get(2));
        assertEquals("</article>\n", result.get(3));
    }

    @Test
    public void articleInAnotherArticleTest() {
        article1.add(title1);
        article1.add(paragraph1);
        article2.add(title2);
        article2.add(paragraph2);
        article1.add(article2);

        List<Document> documents = new ArrayList<>();
        List<String> result = new ArrayList<>();
        HtmlOutputConsumer hoc = new HtmlOutputConsumer(result);

        documents.add(article1);

        documents.iterator().forEachRemaining(hoc);

        assertEquals("<article topic='article1'>\n", result.get(0));
        assertEquals("  <h2>title1</h2>\n", result.get(1));
        assertEquals("  <p>paragraph1</p>\n", result.get(2));
        assertEquals("  <article topic='article2'>\n", result.get(3));
        assertEquals("    <h3>title2</h3>\n", result.get(4));
        assertEquals("    <p>paragraph2</p>\n", result.get(5));
        assertEquals("  </article>\n", result.get(6));
        assertEquals("</article>\n", result.get(7));
    }
}
