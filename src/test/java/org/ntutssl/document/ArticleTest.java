package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
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
    public void addTest() {
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
    public void addArticleIntoArticleTest() {
        boolean thrown = false;
        try {
            article.add(new Article("topic", 4));
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
