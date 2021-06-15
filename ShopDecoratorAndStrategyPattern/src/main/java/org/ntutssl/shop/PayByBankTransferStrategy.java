package org.ntutssl.shop;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PayByBankTransferStrategy implements PayStrategy, EventListener {

	public PayByBankTransferStrategy() {
		EventManager.getInstance().subscribe(EventType.CALCULATE, this);
	}

	public void onEvent(Event event) {
		if (event.type() == EventType.CALCULATE) {
			calculate(Double.parseDouble(event.data().toString()));
		}
	}

	/**
	 * Bank code are 3 digits. Account number are 12 digits.
	 */
	@Override
	public void calculate(double totalPrice) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the bank code:");
		String bankCode = scanner.nextLine();

		System.out.print("Eneter the account number:");
		String accountNumber = scanner.nextLine();

		if (!bankCode.matches("[0-9]{3}") || !accountNumber.matches("[0-9]{12}")) {
			System.out.print("Pay failed.\n");
			return;
		}
		DecimalFormat format = new DecimalFormat("###.00");
		String resultPrice = "$" + format.format(totalPrice + 0.49);
		EventManager.getInstance().publish(new StringEvent(EventType.PRINT_RECEIPT, resultPrice));
		System.out.println("Pay successfully!"); 
	}
}
