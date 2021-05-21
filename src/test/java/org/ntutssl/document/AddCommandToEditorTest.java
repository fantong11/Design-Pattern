package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class AddCommandToEditorTest {
    private Editor editor;
    private AddCommandToEditor acte;

    @Before
    public void setup() {
        editor = new Editor();
        
    }

    @Test
    public void executeTitleInArticleTest() {

        acte = new AddCommandToEditor(editor, new Title("title", 1));
        acte.execute();
        assertEquals("title", editor.iterator().next().getText());
    }

    @Test
    public void executeParagraphInArticleTest() {

        acte = new AddCommandToEditor(editor, new Paragraph("paragraph"));
        acte.execute();
        assertEquals("paragraph", editor.iterator().next().getText());
    }

    @Test
    public void executeArticleInArticleTest() {

        acte = new AddCommandToEditor(editor, new Article("article2", 2));
        acte.execute();
        assertEquals("article2", editor.iterator().next().getText());
    }

    @Test(expected = NoSuchElementException.class)
    public void undoTitleTest() {
        acte = new AddCommandToEditor(editor, new Title("title", 1));
        acte.execute();
        assertEquals("title", editor.iterator().next().getText());
        acte.undo();
        editor.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void undoParagraphTest() {
        acte = new AddCommandToEditor(editor, new Paragraph("paragraph"));
        acte.execute();
        assertEquals("paragraph", editor.iterator().next().getText());
        acte.undo();
        editor.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void undoArticleTest() {
        acte = new AddCommandToEditor(editor, new Article("article2", 2));
        acte.execute();
        assertEquals("article2", editor.iterator().next().getText());
        acte.undo();
        editor.iterator().next();
    }

    @Test
    public void redoTitleTest() {
        acte = new AddCommandToEditor(editor, new Title("title", 1));
        acte.execute();
        assertEquals("title", editor.iterator().next().getText());
        acte.undo();
        acte.redo();
        assertEquals("title", editor.iterator().next().getText());
    }

    @Test
    public void redoParagraphTest() {
        acte = new AddCommandToEditor(editor, new Paragraph("paragraph"));
        acte.execute();
        assertEquals("paragraph", editor.iterator().next().getText());
        acte.undo();
        acte.redo();
        assertEquals("paragraph", editor.iterator().next().getText());
    }

    @Test
    public void redoArticleTest() {
        acte = new AddCommandToEditor(editor, new Article("article2", 2));
        acte.execute();
        assertEquals("article2", editor.iterator().next().getText());
        acte.undo();
        acte.redo();
        assertEquals("article2", editor.iterator().next().getText());
    }
}