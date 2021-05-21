package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class AddCommandToArticleTest {
    private Article article;
    private AddCommandToArticle acta;

    @Before
    public void setup() {
        article = new Article("article1", 1);

    }

    @Test
    public void executeTitleInArticleTest() {

        acta = new AddCommandToArticle(article, new Title("title", 1));
        acta.execute();
        assertEquals("title", article.iterator().next().getText());
    }

    @Test
    public void executeParagraphInArticleTest() {

        acta = new AddCommandToArticle(article, new Paragraph("paragraph"));
        acta.execute();
        assertEquals("paragraph", article.iterator().next().getText());
    }

    @Test
    public void executeArticleInArticleTest() {

        acta = new AddCommandToArticle(article, new Article("article2", 2));
        acta.execute();
        assertEquals("article2", article.iterator().next().getText());
    }

    @Test(expected = NoSuchElementException.class)
    public void undoTitleTest() {
        acta = new AddCommandToArticle(article, new Title("title", 1));
        acta.execute();
        assertEquals("title", article.iterator().next().getText());
        acta.undo();
        article.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void undoParagraphTest() {
        acta = new AddCommandToArticle(article, new Paragraph("paragraph"));
        acta.execute();
        assertEquals("paragraph", article.iterator().next().getText());
        acta.undo();
        article.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void undoArticleTest() {
        acta = new AddCommandToArticle(article, new Article("article2", 2));
        acta.execute();
        assertEquals("article2", article.iterator().next().getText());
        acta.undo();
        article.iterator().next();
    }

    @Test
    public void redoTitleTest() {
        acta = new AddCommandToArticle(article, new Title("title", 1));
        acta.execute();
        assertEquals("title", article.iterator().next().getText());
        acta.undo();
        acta.redo();
        assertEquals("title", article.iterator().next().getText());
    }

    @Test
    public void redoParagraphTest() {
        acta = new AddCommandToArticle(article, new Paragraph("paragraph"));
        acta.execute();
        assertEquals("paragraph", article.iterator().next().getText());
        acta.undo();
        acta.redo();
        assertEquals("paragraph", article.iterator().next().getText());
    }

    @Test
    public void redoArticleTest() {
        acta = new AddCommandToArticle(article, new Article("article2", 2));
        acta.execute();
        assertEquals("article2", article.iterator().next().getText());
        acta.undo();
        acta.redo();
        assertEquals("article2", article.iterator().next().getText());
    }
}
