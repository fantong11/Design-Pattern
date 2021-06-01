package org.ntutssl.shop;

import java.util.ArrayList;
import java.util.List;

public class Shop implements EventListener {
	private List<Event<Goods>> goodsList = new ArrayList<>();

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
	 * private methods are not necessary, but you can takce them as reference.
	 */
	/**
	 * replenish stock
	 * 
	 * @param event Event of Goods to replenish
	 */
	private void replenish(Event<Goods> event) {
		goodsList.add(event);
	}

	/**
	 * check if the specific goods is in stock, if so, publish an event ADD_TO_CART
	 * 
	 * @param event Event of Goods to check
	 */
	private void checkStock(Event<Goods> event) {
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
	}
}
