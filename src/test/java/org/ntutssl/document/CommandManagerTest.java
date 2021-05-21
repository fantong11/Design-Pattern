package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class CommandManagerTest {
    private CommandManager commandManager;
    private Editor editor;
    private Article article;

    @Before
    public void setup() {
        commandManager = new CommandManager();
        editor = new Editor();
        article = new Article("article1", 1);
    }

    @Test
    public void executeTitleInEditorTest() {
        commandManager.executeCmd(new AddCommandToEditor(editor, new Title("title", 1)));
        assertEquals("title", editor.iterator().next().getText());
    }

    @Test
    public void executeParagraphInEditorTest() {
        commandManager.executeCmd(new AddCommandToEditor(editor, new Paragraph("paragraph")));
        assertEquals("paragraph", editor.iterator().next().getText());
    }

    @Test
    public void executeArticleInEditorTest() {
        commandManager.executeCmd(new AddCommandToEditor(editor, new Article("article2", 1)));
        assertEquals("article2", editor.iterator().next().getText());
    }

    @Test
    public void executeMultipleDocumentInEditorTest() {
        commandManager.executeCmd(new AddCommandToEditor(editor, new Title("title", 1)));
        commandManager.executeCmd(new AddCommandToEditor(editor, new Paragraph("paragraph")));
        commandManager.executeCmd(new AddCommandToEditor(editor, new Article("article2", 1)));

        Iterator<Document> it = editor.iterator();
        assertEquals("title", it.next().getText());
        assertEquals("paragraph", it.next().getText());
        assertEquals("article2", it.next().getText());
    }

    @Test(expected = NoSuchElementException.class)
    public void undoTitleInEditorTest() {
        commandManager.executeCmd(new AddCommandToEditor(editor, new Title("title", 1)));
        assertEquals("title", editor.iterator().next().getText());
        commandManager.undoCmd();
        editor.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void undoParagraphInEditorTest() {
        commandManager.executeCmd(new AddCommandToEditor(editor, new Paragraph("paragraph")));
        assertEquals("paragraph", editor.iterator().next().getText());
        commandManager.undoCmd();
        editor.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void undoArticleInEditorTest() {
        commandManager.executeCmd(new AddCommandToEditor(editor, new Article("article2", 1)));
        assertEquals("article2", editor.iterator().next().getText());
        commandManager.undoCmd();
        editor.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void undoMultipleDocumentInEditorTest() {
        commandManager.executeCmd(new AddCommandToEditor(editor, new Title("title", 1)));
        commandManager.executeCmd(new AddCommandToEditor(editor, new Paragraph("paragraph")));
        commandManager.executeCmd(new AddCommandToEditor(editor, new Article("article2", 1)));

        commandManager.undoCmd();
        commandManager.undoCmd();
        Iterator<Document> it = editor.iterator();
        assertEquals("title", it.next().getText());
        assertEquals("paragraph", it.next().getText());
        it.next();
    }

    @Test
    public void redoTitleInEditorTest() {
        commandManager.executeCmd(new AddCommandToEditor(editor, new Title("title", 1)));
        assertEquals("title", editor.iterator().next().getText());
        commandManager.undoCmd();
        commandManager.redoCmd();
        assertEquals("title", editor.iterator().next().getText());
    }

    @Test
    public void redoParagraphInEditorTest() {
        commandManager.executeCmd(new AddCommandToEditor(editor, new Paragraph("paragraph")));
        assertEquals("paragraph", editor.iterator().next().getText());
        commandManager.undoCmd();
        commandManager.redoCmd();
        assertEquals("paragraph", editor.iterator().next().getText());
    }

    @Test
    public void redoArticleInEditorTest() {
        commandManager.executeCmd(new AddCommandToEditor(editor, new Article("article2", 1)));
        assertEquals("article2", editor.iterator().next().getText());
        commandManager.undoCmd();
        commandManager.redoCmd();
        assertEquals("article2", editor.iterator().next().getText());
    }

    @Test
    public void redoMultipleDocumentInEditorTest() {
        commandManager.executeCmd(new AddCommandToEditor(editor, new Title("title", 1)));
        commandManager.executeCmd(new AddCommandToEditor(editor, new Paragraph("paragraph")));
        commandManager.executeCmd(new AddCommandToEditor(editor, new Article("article2", 1)));

        commandManager.undoCmd();
        commandManager.undoCmd();
        commandManager.undoCmd();
        commandManager.redoCmd();
        commandManager.redoCmd();
        commandManager.redoCmd();
        Iterator<Document> it = editor.iterator();
        assertEquals("title", it.next().getText());
        assertEquals("paragraph", it.next().getText());
        assertEquals("article2", it.next().getText());
    }

    @Test
    public void executeTitleInArticleTest() {
        commandManager.executeCmd(new AddCommandToArticle(article, new Title("title", 1)));
        assertEquals("title", article.iterator().next().getText());
    }

    @Test
    public void executeParagraphInArticleTest() {
        commandManager.executeCmd(new AddCommandToArticle(article, new Paragraph("paragraph")));
        assertEquals("paragraph", article.iterator().next().getText());
    }

    @Test
    public void executeArticleInArticleTest() {
        commandManager.executeCmd(new AddCommandToArticle(article, new Article("article2", 2)));
        assertEquals("article2", article.iterator().next().getText());
    }

    @Test
    public void executeMultipleDocumentInArticleTest() {
        commandManager.executeCmd(new AddCommandToArticle(article, new Title("title", 1)));
        commandManager.executeCmd(new AddCommandToArticle(article, new Paragraph("paragraph")));
        commandManager.executeCmd(new AddCommandToArticle(article, new Article("article2", 2)));

        Iterator<Document> it = article.iterator();
        assertEquals("title", it.next().getText());
        assertEquals("paragraph", it.next().getText());
        assertEquals("article2", it.next().getText());
    }

    @Test(expected = NoSuchElementException.class)
    public void undoTitleInArticleTest() {
        commandManager.executeCmd(new AddCommandToArticle(article, new Title("title", 1)));
        assertEquals("title", article.iterator().next().getText());
        commandManager.undoCmd();
        article.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void undoParagraphInArticleTest() {
        commandManager.executeCmd(new AddCommandToArticle(article, new Paragraph("paragraph")));
        assertEquals("paragraph", article.iterator().next().getText());
        commandManager.undoCmd();
        article.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void undoArticleInArticleTest() {
        commandManager.executeCmd(new AddCommandToArticle(article, new Article("article2", 2)));
        assertEquals("article2", article.iterator().next().getText());
        commandManager.undoCmd();
        article.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void undoMultipleDocumentInArticleTest() {
        commandManager.executeCmd(new AddCommandToArticle(article, new Title("title", 1)));
        commandManager.executeCmd(new AddCommandToArticle(article, new Paragraph("paragraph")));
        commandManager.executeCmd(new AddCommandToArticle(article, new Article("article2", 2)));

        commandManager.undoCmd();
        commandManager.undoCmd();
        Iterator<Document> it = article.iterator();
        assertEquals("title", it.next().getText());
        assertEquals("paragraph", it.next().getText());
        it.next();
    }

    @Test
    public void redoTitleInAritcleTest() {
        commandManager.executeCmd(new AddCommandToArticle(article, new Title("title", 1)));
        assertEquals("title", article.iterator().next().getText());
        commandManager.undoCmd();
        commandManager.redoCmd();
        assertEquals("title", article.iterator().next().getText());
    }

    @Test
    public void redoParagraphInAritcleTest() {
        commandManager.executeCmd(new AddCommandToArticle(article, new Paragraph("paragraph")));
        assertEquals("paragraph", article.iterator().next().getText());
        commandManager.undoCmd();
        commandManager.redoCmd();
        assertEquals("paragraph", article.iterator().next().getText());
    }

    @Test
    public void redoArticleInAritcleTest() {
        commandManager.executeCmd(new AddCommandToArticle(article, new Article("article2", 2)));
        assertEquals("article2", article.iterator().next().getText());
        commandManager.undoCmd();
        commandManager.redoCmd();
        assertEquals("article2", article.iterator().next().getText());
    }

    @Test
    public void redoMultipleDocumentInArticleTest() {
        commandManager.executeCmd(new AddCommandToArticle(article, new Title("title", 1)));
        commandManager.executeCmd(new AddCommandToArticle(article, new Paragraph("paragraph")));
        commandManager.executeCmd(new AddCommandToArticle(article, new Article("article2", 2)));

        commandManager.undoCmd();
        commandManager.undoCmd();
        commandManager.undoCmd();
        commandManager.redoCmd();
        commandManager.redoCmd();
        commandManager.redoCmd();
        Iterator<Document> it = article.iterator();
        assertEquals("title", it.next().getText());
        assertEquals("paragraph", it.next().getText());
        assertEquals("article2", it.next().getText());
    }
}