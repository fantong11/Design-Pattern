package org.ntutssl.document;

import java.util.Scanner;

public class InstructionHandler {
	private Editor editor;

	public InstructionHandler(Editor editor) {
		this.editor	= editor;
	}

	public void run() {
		while (true) {
			printEditorInstructions();
			try (Scanner scanner = new Scanner(System.in);) {
				handleEditorInstructions(scanner.nextLine());
			} catch (Exception e) {
				System.out.println(e);
			}
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
				editor.add(title);
				break;

			case "add paragraph":
				Document paragragh = addParagraphInstruction();
				editor.add(paragragh);
				break;

			case "add article":
				Document article = addArticleInstruction(0);
				editor.add(article);
				break;

			case "find content":
				findContentInstruction();
				break;

			case "output html":
				outputHtmlInstruction();
				break;

			case "exit":
				System.exit(0);
				break;

			default:
				System.out.println("Invalid Instruction");
				break;
		}
	}

	private Document addTitleInstruction() {
		return null;
	}

	private Document addParagraphInstruction() {
		return null;
	}

	private Document addArticleInstruction(int lastLevel) {
		return null;
	}

	private void printArticleInstructions() {
		
	}

	private void handleArticleInstructions(String instruction, Article article) {
		
	}

	private void findContentInstruction() {
		
	}

	private void outputHtmlInstruction() {
    
	}
}