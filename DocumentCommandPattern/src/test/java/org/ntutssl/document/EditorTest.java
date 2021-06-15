package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditorTest {
	private Editor editor;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream systemOut = System.out;

	@Before
	public void setUp() {
		editor = new Editor();
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void tearDown() {
		editor = null;
		System.setOut(systemOut);
	}

	@Test
	public void sizeTest() {
		Document article = new Article("Course Design Pattern", 1);
		Document article2 = new Article("Design Patterns", 2);
		Document title = new Title(
				"Design pattern are solutions to general problems that appear repeatedly in software engineering.", 20);
		Document paragragh = new Paragraph("This course discusses design patterns.");
		Document article3 = new Article("Design", 3);
		editor.add(article);
		editor.add(article2);
		editor.add(title);
		editor.add(paragragh);
		editor.add(article3);

		assertEquals(5, editor.size());
	}

	@Test
	public void iteratorTest() {
		Document article = new Article("Course Design Pattern", 1);
		Document article2 = new Article("Design Patterns", 2);
		Document title = new Title(
				"Design pattern are solutions to general problems that appear repeatedly in software engineering.", 20);
		Document paragragh = new Paragraph("This course discusses design patterns.");
		Document article3 = new Article("Design", 3);
		editor.add(article);
		editor.add(article2);
		editor.add(title);
		editor.add(paragragh);
		editor.add(article3);

		Iterator<Document> it = editor.iterator();

		assertTrue(it.hasNext());
		assertEquals("Course Design Pattern", it.next().getText());
		assertTrue(it.hasNext());
		assertEquals("Design Patterns", it.next().getText());
		assertTrue(it.hasNext());
		assertEquals("Design pattern are solutions to general problems that appear repeatedly in software engineering.",
				it.next().getText());
		assertTrue(it.hasNext());
		assertEquals("This course discusses design patterns.", it.next().getText());
		assertTrue(it.hasNext());
		assertEquals("Design", it.next().getText());
		assertFalse(it.hasNext());
	}

	@Test
	public void importJsonFileTest() {
		editor.importDocumentFromJsonFile("input/test_input.json");
		assertEquals(3, editor.size());
	}

	@Test
	public void exportHtmlFileTest() {
		editor.importDocumentFromJsonFile("input/test_input.json");
		editor.exportDocumentAsHtmlFile("output/test_output.html");

		try (Scanner scanner = new Scanner(new File("output/test_output.html"))) {
			assertEquals("<h1>I'm a simple title</h1>", scanner.nextLine());
			assertEquals("<p>I'm a simple paragraph</p>", scanner.nextLine());
			assertEquals("<article topic='I'm a simple article'>", scanner.nextLine());
			assertEquals("  <h2>inner title</h2>", scanner.nextLine());
			assertEquals("  <p>inner paragraph</p>", scanner.nextLine());
			assertEquals("</article>", scanner.nextLine());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void findContent() {
		editor.importDocumentFromJsonFile("input/test_input.json");
		editor.findContent("title");

		assertEquals("Title\t\ttext: I'm a simple title\n\t\tsize: 1\n" + "Title\t\ttext: inner title\n\t\tsize: 2\n",
				outContent.toString());
	}

	@Test(expected = NoSuchElementException.class)
	public void addExceptionTest() {
		Document article = new Article("Course Design Pattern", 1);
		Document article2 = new Article("Design Patterns", 2);
		Document title = new Title(
				"Design pattern are solutions to general problems that appear repeatedly in software engineering.", 20);
		Document paragragh = new Paragraph("This course discusses design patterns.");
		Document article3 = new Article("Design", 3);
		editor.add(article);
		editor.add(article2);
		editor.add(title);
		editor.add(paragragh);
		editor.add(article3);

		Iterator<Document> it = editor.iterator();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
	}
}
