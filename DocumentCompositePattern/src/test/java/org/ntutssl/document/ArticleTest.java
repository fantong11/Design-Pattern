package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArticleTest {
    Document article;

    @Before
    public void setUp() {
        article = new Article("test", 5);
    }

    @After
    public void tearDown() {
        article = null;
    }

    @Test
    public void getTextTest() {
        assertEquals("test", article.getText());
    }

    @Test
    public void getLevelTest() {
        assertEquals(5, article.getLevel());
    }

    @Test
    public void addSinglePrimitiveDocumentTest() {
        Document title = new Title("Lorem");
        article.add(title);
        assertEquals("Lorem", article.getContent(0).getText());
    }

    @Test
    public void addMultiPrimitiveDocumentsTest() {
        Document title = new Title("Lorem");
        Document paragraph = new Paragraph(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque finibus tortor commodo, convallis libero quis, varius nibh. Nam quis diam velit. Donec placerat neque sit amet laoreet fermentum. Nullam sed nulla fringilla, rhoncus leo non, dictum tortor. Aliquam faucibus eget lorem a feugiat. Aliquam at pharetra erat, vel egestas nunc. Ut varius libero sed nibh aliquet consequat. Aliquam mattis porttitor dignissim.");
        article.add(title);
        article.add(paragraph);
        assertEquals("Lorem", article.getContent(0).getText());
        assertEquals(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque finibus tortor commodo, convallis libero quis, varius nibh. Nam quis diam velit. Donec placerat neque sit amet laoreet fermentum. Nullam sed nulla fringilla, rhoncus leo non, dictum tortor. Aliquam faucibus eget lorem a feugiat. Aliquam at pharetra erat, vel egestas nunc. Ut varius libero sed nibh aliquet consequat. Aliquam mattis porttitor dignissim.",
                article.getContent(1).getText());
    }

    @Test
    public void addSingleArticleDocumentTest() {
        Document article = new Article("Lorem", 7);
        this.article.add(article);
        assertEquals("Lorem", this.article.getContent(0).getText());
    }

    @Test
    public void addMultiArticleDocumentsTest() {
        this.article.add(new Article("Lorem", 7));
        this.article.add(new Article("1234", 10));
        assertEquals("Lorem", article.getContent(0).getText());
        assertEquals("1234", article.getContent(1).getText());
    }

    @Test
    public void addArticleAndPrimitiveDocumentsTest() {
        this.article.add(new Article("Lorem", 7));
        this.article.add(new Title("1234"));
        this.article.add(new Paragraph("789"));
        assertEquals("Lorem", article.getContent(0).getText());
        assertEquals("1234", article.getContent(1).getText());
        assertEquals("789", article.getContent(2).getText());
    }

    @Test
    public void addPrimitiveDocumentsInArticleInArticleTest() {
        this.article.add(new Article("Lorem", 7));
        this.article.getContent(0).add(new Title("test"));
        assertEquals("test", article.getContent(0).getContent(0).getText());
    }

    @Test
    public void addLowLevelArticleDocumentTest() {
        boolean thrown = false;
        try {
            article.add(new Article("topic", 4));
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void addHighLevelArticleDocumentTest() {
        boolean thrown = false;
        try {
            article.add(new Article("topic", 10));
        } catch (Exception e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
}
