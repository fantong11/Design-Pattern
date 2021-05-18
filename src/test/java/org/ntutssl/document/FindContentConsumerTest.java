package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FindContentConsumerTest {
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
        List<Document> result = new ArrayList<>();
        FindContentConsumer fcc = new FindContentConsumer(result, "title1");

        documents.add(title1);
        documents.add(title2);
        documents.add(title3);

        documents.iterator().forEachRemaining(fcc);

        assertEquals("title1", result.get(0).getText());
    }

    @Test
    public void titleSearchWithEmptyStringTest() {
        List<Document> documents = new ArrayList<>();
        List<Document> result = new ArrayList<>();
        FindContentConsumer fcc = new FindContentConsumer(result, " ");

        documents.add(title1);
        documents.add(title2);
        documents.add(title3);

        documents.iterator().forEachRemaining(fcc);

        assertTrue(result.isEmpty());
    }

    @Test
    public void emptyTitleTest() {
        Document titleEmpty = new Title("  ", 2);

        List<Document> documents = new ArrayList<>();
        List<Document> result = new ArrayList<>();
        FindContentConsumer fcc = new FindContentConsumer(result, "  ");

        documents.add(title1);
        documents.add(titleEmpty);
        documents.add(title3);

        documents.iterator().forEachRemaining(fcc);

        assertEquals("  ", result.get(0).getText());
    }

    @Test
    public void paragraphTest() {
        List<Document> documents = new ArrayList<>();
        List<Document> result = new ArrayList<>();
        FindContentConsumer fcc = new FindContentConsumer(result, "paragraph1");

        documents.add(paragraph1);
        documents.add(paragraph2);
        documents.add(paragraph3);

        documents.iterator().forEachRemaining(fcc);

        assertEquals("paragraph1", result.get(0).getText());
    }

    @Test
    public void paragraphSearchWithEmptyStringTest() {
        List<Document> documents = new ArrayList<>();
        List<Document> result = new ArrayList<>();
        FindContentConsumer fcc = new FindContentConsumer(result, "  ");

        documents.add(paragraph1);
        documents.add(paragraph2);
        documents.add(paragraph3);

        documents.iterator().forEachRemaining(fcc);

        assertTrue(result.isEmpty());
    }

    @Test
    public void emptyParagraphTest() {
        Document paragraphEmpty = new Paragraph("  ");

        List<Document> documents = new ArrayList<>();
        List<Document> result = new ArrayList<>();
        FindContentConsumer fcc = new FindContentConsumer(result, "  ");

        documents.add(paragraph1);
        documents.add(paragraph2);
        documents.add(paragraphEmpty);

        documents.iterator().forEachRemaining(fcc);

        assertEquals("  ", result.get(0).getText());
    }

    @Test
    public void articleTest() {
        article1.add(title1);
        article1.add(paragraph1);

        List<Document> documents = new ArrayList<>();
        List<Document> result = new ArrayList<>();
        FindContentConsumer fcc = new FindContentConsumer(result, "article1");

        documents.add(article1);

        documents.iterator().forEachRemaining(fcc);

        assertEquals("article1", result.get(0).getText());
    }

    @Test
    public void articleInArticleTest() {
        article1.add(title1);
        article1.add(paragraph1);
        article2.add(title2);
        article2.add(paragraph2);
        article1.add(article2);

        List<Document> documents = new ArrayList<>();
        List<Document> result = new ArrayList<>();
        FindContentConsumer fcc = new FindContentConsumer(result, "paragraph");

        documents.add(article1);

        documents.iterator().forEachRemaining(fcc);

        assertEquals("paragraph1", result.get(0).getText());
        assertEquals("paragraph2", result.get(1).getText());
    }

    @Test
    public void emptyArticleTest() {
        Document titleEmpty = new Title("  ", 5);
        Document paragraphEmpty = new Paragraph("  ");

        article1.add(titleEmpty);
        article1.add(paragraphEmpty);

        List<Document> documents = new ArrayList<>();
        List<Document> result = new ArrayList<>();
        FindContentConsumer fcc = new FindContentConsumer(result, "  ");

        documents.add(article1);

        documents.iterator().forEachRemaining(fcc);

        assertEquals("  ", result.get(0).getText());
        assertEquals("  ", result.get(1).getText());
    }

    @Test
    public void articleSearchWithEmptyStringTest() {
        article1.add(title1);
        article1.add(paragraph1);

        List<Document> documents = new ArrayList<>();
        List<Document> result = new ArrayList<>();
        FindContentConsumer fcc = new FindContentConsumer(result, "  ");

        documents.add(article1);

        documents.iterator().forEachRemaining(fcc);

        assertTrue(result.isEmpty());
    }
}