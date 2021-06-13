package org.ntutssl.shop;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ShoppingCart implements EventListener {
	// private List<Event<Goods>> events;
	private Map<Goods, Integer> shoppingCart = new LinkedHashMap<>();

	public ShoppingCart() {
		EventManager.getInstance().subscribe(EventType.PAY, this);
		EventManager.getInstance().subscribe(EventType.LIST_CART, this);
		EventManager.getInstance().subscribe(EventType.ADD_TO_CART, this);
		EventManager.getInstance().subscribe(EventType.PRINT_RECEIPT, this);
	}

	public void onEvent(Event event) {
		switch (event.type()) {
			case ADD_TO_CART:
				add(event);
				break;

			case PAY:
				pay();
				break;

			case PRINT_RECEIPT:
				printReceipt(event);
				break;

			case LIST_CART:
				listCart();
				break;

			default:
				break;
		}
	}

	/**
	 * add goods to shopping cart
	 * 
	 * @param goodsEvent the data of this event is the goods to be added
	 */
	private void add(Event<Goods> event) {
		shoppingCart.put(event.data(), event.count());
	}

	/**
	 * pay for all items in the shopping cart
	 */
	private void pay() {
		double totalPrice = 0;

		for (Entry<Goods, Integer> goods : shoppingCart.entrySet()) {
			totalPrice += goods.getKey().price() * goods.getValue();
		}

		EventManager.getInstance().publish(new StringEvent(EventType.CALCULATE, String.valueOf(totalPrice)));
	}

	/**
	 * print receipt and publish PURCHASE
	 */
	private void printReceipt(Event<String> event) {
		System.out.print("================================================================================\n");
		System.out.print("Receipt:\n");
		System.out.printf("%-40s%-10s%-10s\n", "name", "price", "count");

		for (Entry<Goods, Integer> goods : shoppingCart.entrySet()) {
			EventManager.getInstance().publish(new GoodsEvent(EventType.PURCHASE, goods.getKey(), goods.getValue()));

			System.out.printf("%-40s%-10s%-10s\n", goods.getKey().name(), "$" + goods.getKey().price(), goods.getValue());
		}

		System.out.print("--------------------------------------------------------------------------------\n");
		System.out.print("Total Price: " + event.data() + "\n");
		System.out.print("================================================================================\n");

		shoppingCart.clear();
	}

	/**
	 * list all items in the shopping cart
	 */
	private void listCart() {
		if (shoppingCart.size() == 0) {
			System.out.print("Your shopping cart is empty\n");
			return;
		}

		System.out.print("================================================================================\n");
		System.out.printf("%-4s%-22s%-40s%-8s%-6s\n", "ID", "name", "description", "price", "count");
		System.out.print("--------------------------------------------------------------------------------\n");
		for (Entry<Goods, Integer> goods : shoppingCart.entrySet()) {
			System.out.printf("%-4s%-22s%-40s%-8s%-6s\n", String.valueOf(goods.getKey().id()), goods.getKey().name(),
					goods.getKey().description(), String.valueOf(goods.getKey().price()),
					String.valueOf(goods.getValue()));
		}
		System.out.print("================================================================================\n");
	}
}
