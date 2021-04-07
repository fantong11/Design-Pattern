package org.ntutssl.document;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class InstructionHandler {
	private Editor editor;
	private int lastLevel = 0;
	public static Scanner scanner = new Scanner(System.in);

	public InstructionHandler(Editor editor) {
		this.editor = editor;
	}

	public void run() {
		while (true) {
			printEditorInstructions();
			handleEditorInstructions(scanner.nextLine());
		}
	}

	private void printEditorInstructions() {
		System.out.println("Please enter the following instruction to start editing:");
		System.out.println("  1. 'add title': to add a title to the editor");
		System.out.println("  2. 'add paragraph': to add a paragraph to the editor");
		System.out.println("  3. 'add article': to add an article to the editor");
		System.out.println("  4. 'find content': to find the specific string in the editor");
		System.out.println("  5. 'output html': to transfer the text to html format");
		System.out.println("  6. 'exit': to exit the program");
	}

	private void handleEditorInstructions(String instruction) {
		switch (instruction) {
		case "add title":
			Document title = addTitleInstruction();
			if (title != null) {
				editor.add(title);
				System.out.println("Title added to the editor.");
			}
			break;

		case "add paragraph":
			Document paragragh = addParagraphInstruction();
			editor.add(paragragh);
			System.out.println("Paragraph added to the editor.");
			break;

		case "add article":
			Document article = addArticleInstruction(lastLevel);
			if (article != null) {
				editor.add(article);
				handleArticleInstructions(instruction, (Article) article);
			}
			break;

		case "find content":
			findContentInstruction();
			break;

		case "output html":
			outputHtmlInstruction();
			break;

		case "exit":
			scanner.close();
			System.exit(0);
			break;

		default:
			System.out.println("Invalid Instruction");
			break;
		}
	}

	private Document addTitleInstruction() {
		System.out.println("Please enter the information of title:");
		System.out.print("Text of title: ");
		String text = scanner.nextLine();
		System.out.print("Size of title: ");
		int size = Integer.parseInt(scanner.nextLine());
		if (size < 1 || size > 6) {
			System.out.println("Invalid Input: The size should be in range 1 to 6");
			return null;
		}
		return new Title(text, size);
	}

	private Document addParagraphInstruction() {
		System.out.println("Please enter the information of paragraph:");
		System.out.print("Text of paragraph: ");
		String text = scanner.nextLine();

		return new Paragraph(text);

	}

	private Document addArticleInstruction(int lastLevel) {
		System.out.println("Please enter the information of article:");
		System.out.print("Topic of article: ");
		String topic = scanner.nextLine();
		System.out.print("Level of article: ");
		int level = Integer.parseInt(scanner.nextLine());
		if (level <= 0) {
			System.out.println(
					"Invalid Input: The level should be positive or higher than the level of the current article.");
			return null;
		}
		this.lastLevel = level;

		return new Article(topic, level);

	}

	private void printArticleInstructions() {
		System.out.println("Please enter the following instruction to edit the article:");
		System.out.println("  1. 'add title': to add a title to the article");
		System.out.println("  2. 'add paragraph': to add a paragraph to the article");
		System.out.println("  3. 'add article': to add an article to the article");
		System.out.println("  4. 'exit': to exit the process");

	}

	private void handleArticleInstructions(String instruction, Article article) {
		printArticleInstructions();
		instruction = scanner.nextLine();
		Article previousArticle = null;

		switch (instruction) {
		case "add title":
			Document title = addTitleInstruction();
			article.add(title);
			handleArticleInstructions(instruction, article);
			break;

		case "add paragraph":
			Document paragraph = addParagraphInstruction();
			article.add(paragraph);
			handleArticleInstructions(instruction, article);
			break;

		case "add article":
			previousArticle = article;
			Document newArticle = addArticleInstruction(lastLevel);
			article.add(newArticle);
			handleArticleInstructions(instruction, (Article) newArticle);
			handleArticleInstructions(instruction, previousArticle);
			break;

		case "exit":
			return;

		default:
			System.out.println("Invalid instruction.");
			handleArticleInstructions(instruction, article);
			break;
		}
	}

	private void findContentInstruction() {
		System.out.print("Enter the word you wnat to find: ");
		String target = scanner.nextLine();

		FindContentVisitor fcv = new FindContentVisitor(target);
		Iterator<Document> it = editor.iterator();

		while (it.hasNext()) {
			Document document = it.next();
			document.accept(fcv);
		}

		List<Document> result = fcv.getResult();

		for (Document document : result) {
			System.out.print(document.toString());
		}

	}

	private void outputHtmlInstruction() {
		HtmlOutputVisitor hov = new HtmlOutputVisitor();
		Iterator<Document> it = editor.iterator();

		while (it.hasNext()) {
			Document document = it.next();
			document.accept(hov);
		}

		String result = hov.getResult();

		System.out.println(result);
	}
}