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
		System.out.print("Enter the card number: ");
		String cardNumber = scanner.nextLine();
		System.out.println(cardNumber);

		System.out.print("Enter the card expiration date 'mm/yy': ");
		String expiredDate = scanner.nextLine();
		System.out.println(expiredDate);

		System.out.print("Enter the CVV code: ");
		String cvv = scanner.nextLine();
		System.out.println(cvv);

		if (cardNumber.matches("\\d{16}") && expiredDate.matches("((0[1-9])|(1[0-2]))/\\d{2}")
				&& cvv.matches("\\d{3}")) {
			DecimalFormat format = new DecimalFormat("###.00");
			String resultPrice = "$" + format.format((totalPrice * 0.9));

			EventManager.getInstance().publish(new StringEvent(EventType.PRINT_RECEIPT, resultPrice));

			System.out.print("Pay successfully!\n");
		} else {
			System.out.println("Pay failed.");
		}
	}
}
