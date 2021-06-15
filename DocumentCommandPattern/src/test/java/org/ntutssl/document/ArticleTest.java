package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ArticleTest {
    Document article;
    Iterator<Document> iterator;

    @Before
    public void setUp() {
        article = new Article("test", 5);
        iterator = null;
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
        Document title = new Title("Lorem", 20);
        article.add(title);
        iterator = article.iterator();
        assertEquals("Lorem", iterator.next().getText());
    }

    @Test
    public void addMultiPrimitiveDocumentsTest() {
        Document title = new Title("Lorem", 20);
        Document paragraph = new Paragraph(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque finibus tortor commodo, convallis libero quis, varius nibh. Nam quis diam velit. Donec placerat neque sit amet laoreet fermentum. Nullam sed nulla fringilla, rhoncus leo non, dictum tortor. Aliquam faucibus eget lorem a feugiat. Aliquam at pharetra erat, vel egestas nunc. Ut varius libero sed nibh aliquet consequat. Aliquam mattis porttitor dignissim.");
        article.add(title);
        article.add(paragraph);
        iterator = article.iterator();
        assertEquals("Lorem", iterator.next().getText());
        assertEquals(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque finibus tortor commodo, convallis libero quis, varius nibh. Nam quis diam velit. Donec placerat neque sit amet laoreet fermentum. Nullam sed nulla fringilla, rhoncus leo non, dictum tortor. Aliquam faucibus eget lorem a feugiat. Aliquam at pharetra erat, vel egestas nunc. Ut varius libero sed nibh aliquet consequat. Aliquam mattis porttitor dignissim.",
                iterator.next().getText());
    }

    @Test
    public void addSingleArticleDocumentTest() {
        Document article = new Article("Lorem", 7);
        this.article.add(article);
        iterator = this.article.iterator();
        assertEquals("Lorem", iterator.next().getText());
    }

    @Test
    public void addMultiArticleDocumentsTest() {
        this.article.add(new Article("Lorem", 7));
        this.article.add(new Article("1234", 10));
        iterator = article.iterator();
        assertEquals("Lorem", iterator.next().getText());
        assertEquals("1234", iterator.next().getText());
    }

    @Test
    public void addArticleAndPrimitiveDocumentsTest() {
        this.article.add(new Article("Lorem", 7));
        this.article.add(new Title("1234", 20));
        this.article.add(new Paragraph("789"));
        iterator = article.iterator();
        assertEquals("Lorem", iterator.next().getText());
        assertEquals("1234", iterator.next().getText());
        assertEquals("789", iterator.next().getText());
    }

    @Test
    public void toStringTest() {
        assertEquals("Article\t\ttopic: test\n\t\tlevel: 5\n", article.toString());
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void addLowLevelArticleDocumentTest() {
        expectedEx.expect(DocumentException.class);
        expectedEx.expectMessage("Level must be higher than current article.");
        article.add(new Article("topic", 4));
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

    @Test
    public void getSizeTest() {
        expectedEx.expect(DocumentException.class);
        expectedEx.expectMessage("Invalid action: getSize");
        article.getSize();
    }

    @Test(expected = NoSuchElementException.class)
    public void removeTest() {
        Document title = new Title("title", 1);
        Document paragraph = new Paragraph("paragraph");
        article.add(title);
        article.add(paragraph);
        article.remove(paragraph);

        iterator = article.iterator();

        assertEquals("title", iterator.next().getText());
        iterator.next();
    }
}
