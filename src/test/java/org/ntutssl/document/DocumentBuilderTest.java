package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DocumentBuilderTest {
    private DocumentBuilder builder;

    @Before
    public void setUp() {
        builder = new DocumentBuilder();
    }

    @Test
    public void buildTitleTest() {
        builder.buildTitle("title", 10);
        Document result = builder.getResult();

        assertEquals("title", result.getText());
        assertEquals(10, result.getSize());
    }

    @Test
    public void buildParagraphTest() {
        builder.buildParagraph("paragraph");
        Document result = builder.getResult();

        assertEquals("paragraph", result.getText());
    }

    @Test
    public void buildArticleTest() {
        builder.startBuildArticle("article", 1);
        builder.endBuildArticle();
        Document result = builder.getResult();

        assertEquals("article", result.getText());
    }

    @Test
    public void buildTitleAndParagraphInArticleTest() {
        builder.startBuildArticle("article", 1);
        builder.buildTitle("title", 10);
        builder.buildParagraph("paragraph");
        builder.endBuildArticle();

        Document result = builder.getResult();
        Iterator<Document> it = result.iterator();
        assertEquals("article", result.getText());
        assertEquals("title", it.next().getText());
        assertEquals("paragraph", it.next().getText());
    }

    @Test
    public void buildArticleInArticleTest() {
        builder.startBuildArticle("article", 1);
        builder.buildTitle("title", 10);
        builder.buildParagraph("paragraph");
        builder.startBuildArticle("article2", 2);
        builder.buildParagraph("paragraph2");
        builder.endBuildArticle();
        builder.endBuildArticle();

        Document result = builder.getResult();
        Iterator<Document> it = result.iterator();
        assertEquals("article", result.getText());
        assertEquals("title", it.next().getText());
        assertEquals("paragraph", it.next().getText());


        Document article2 = it.next();
        Iterator<Document> it2 = article2.iterator();
        assertEquals("article2", article2.getText());
        assertEquals("paragraph2", it2.next().getText());
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    @Test
    public void buildLargerLevelArticleInSmallerLevelArticleTest() {
        expectedEx.expect(DocumentException.class);
        builder.startBuildArticle("article", 1);
        builder.buildTitle("title", 10);
        builder.buildParagraph("paragraph");
        builder.startBuildArticle("article2", 1);
        builder.buildParagraph("paragraph2");
        builder.endBuildArticle();
        builder.endBuildArticle();

    }
}
