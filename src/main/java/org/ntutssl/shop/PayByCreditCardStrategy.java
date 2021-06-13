package org.ntutssl.shop;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PayByCreditCardStrategy implements PayStrategy, EventListener {

	public PayByCreditCardStrategy() {
		EventManager.getInstance().subscribe(EventType.CALCULATE, this);
	}

	public void onEvent(Event event) {
		if (event.type() == EventType.CALCULATE) {
			calculate(Double.parseDouble(event.data().toString()));
		}
	}

	/**
	 * Card number are 16 digits. The format of card expiration date is 'mm/yy',
	 * such as '06/21'. CVV code are 3 digits.
	 */
	@Override
	public void calculate(double totalPrice) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the card number:");
		String cardNumber = scanner.nextLine();

		System.out.print("Eneter the card expiration date 'mm/yy':");
		String date = scanner.nextLine();

		System.out.print("Enter the CVV code:");
		String CVV = scanner.nextLine();

		if (!cardNumber.matches("[0-9]{16}") || !date.matches("0[1-9]|1[0-2]/0[1-9]|[1-9][0-9]")
				|| !CVV.matches("[0-9]{3}")) {
			System.out.print("Pay failed.\n");
			return;
		}

		DecimalFormat format = new DecimalFormat("###.00");
		String resultPrice = "$" + format.format(totalPrice * 0.9);
		EventManager.getInstance().publish(new StringEvent(EventType.PRINT_RECEIPT, resultPrice));
		System.out.println("Pay successfully!"); 
	}
}
