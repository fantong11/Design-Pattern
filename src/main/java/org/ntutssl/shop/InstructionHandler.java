package org.ntutssl.shop;

import java.util.Scanner;

public class InstructionHandler {

	public static Scanner scanner = new Scanner(System.in);

	public InstructionHandler() {

	}

	public void run() {
		while (true) {
			printInstructions();
			handleInstruction(scanner.nextLine());
		}
	}

	private void printInstructions() {
		System.out.print("Please enter the following instruction(number) to start shopping:\n");
		System.out.print("  1. to list all the goods that the shop has\n");
		System.out.print("  2. to import your shopping list into the shopping cart\n");
		System.out.print("  3. to check the content of your shopping cart\n");
		System.out.print("  4. to go to pay\n");
		System.out.print("  5. to exit the program\n");
		System.out.print(">");
	}

	private void handleInstruction(String instruction) {
		switch (instruction) {
			case "1":
				instructionListShop();
				break;

			case "2":
				instructionImportShoppingList();
				break;

			case "3":
				instructionListShoppingCart();
				break;

			case "4":
				instructionPay();
				break;

			case "5":
				System.exit(0);
				break;

			default:
				System.out.println("invalid instruction\n");
				break;
		}
	}

	private void instructionListShop() {
		EventManager.getInstance().publish(new StringEvent(EventType.LIST_SHOP, ""));
	}

	private void instructionImportShoppingList() {
		EventManager.getInstance().publish(new StringEvent(EventType.IMPORT_SHOPPING_LIST, "input/shopping_list.json"));
		System.out.print("import successfully\n");
	}

	private void instructionListShoppingCart() {
		EventManager.getInstance().publish(new StringEvent(EventType.LIST_CART, ""));
	}

	private void instructionPay() {
		System.out.print("Please, select a payment method by number:\n");
		System.out.print("  1. Bank Transfer\n");
		System.out.print("  2. Credit Card\n");
		System.out.print(">");

		String instruction = scanner.nextLine();

		switch (instruction) {
			case "1":
				new PayByBankTransferStrategy();
				EventManager.getInstance().publish(new StringEvent(EventType.PAY, ""));
				break;

			case "2":
				new PayByCreditCardStrategy();
				EventManager.getInstance().publish(new StringEvent(EventType.PAY, ""));
				break;

			default:
				System.out.print("invalid option\n");
				break;
		}
	}
}
