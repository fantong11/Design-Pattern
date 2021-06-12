package org.ntutssl.shop;

import java.util.ArrayList;
import java.util.List;

public class Shop implements EventListener {
	private List<Event<Goods>> stocks = new ArrayList<>();

	public Shop() {
		EventManager.getInstance().subscribe(EventType.REPLENISH, this);
		EventManager.getInstance().subscribe(EventType.CHECK_STOCK, this);
		EventManager.getInstance().subscribe(EventType.PURCHASE, this);
		EventManager.getInstance().subscribe(EventType.LIST_SHOP, this);
	}

	public void onEvent(Event event) {
		switch (event.type()) {
			case REPLENISH:
				replenish(event);
				break;

			case CHECK_STOCK:
				checkStock(event);
				break;

			case PURCHASE:
				purchase(event);
				break;

			case LIST_SHOP:
				listShop();
				break;

			default:
				break;
		}
	}

	/**
	 * replenish stock
	 * 
	 * @param event Event of Goods to replenish
	 */
	private void replenish(Event<Goods> event) {
		stocks.add(event);
	}

	/**
	 * check if the specific goods is in stock, if so, publish an event ADD_TO_CART
	 * 
	 * @param event Event of Goods to check
	 */
	private void checkStock(Event<Goods> event) {
		for (Event<Goods> replenishedGoods : stocks) {
			if (replenishedGoods.data().name().equals(event.data().name())) {
				EventManager.getInstance().publish(new GoodsEvent(EventType.ADD_TO_CART, event.data(), event.count()));
			}
		}
	}

	/**
	 * deduct stock after user complete purchase
	 * 
	 * @param event Event of Goods to be deducted
	 */
	private void purchase(Event<Goods> event) {
	}

	/**
	 * show stocks of this shop
	 */
	private void listShop() {
		if (stocks.size() == 0)  {
			System.out.print("Your shopping cart is empty.\n");
			return;
		}
		System.out.print("================================================================================\n");
		System.out.printf("%-4s%-22s%-40s%-8s%-6s\n", "ID", "name", "description", "price", "count");
		System.out.print("--------------------------------------------------------------------------------\n");
		for (Event<Goods> goods : stocks) {
			System.out.printf("%-4s%-22s%-40s%-8s%-6s\n", String.valueOf(goods.data().id()), goods.data().name(),
					goods.data().description(), String.valueOf(goods.data().price()),
					String.valueOf(goods.count()));
		}
		System.out.print("================================================================================\n");
	}
}
