package org.ntutssl.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop implements EventListener {
	private Map<Integer, Integer> stocksIdCount = new HashMap<>();
	private List<Goods> stocks = new ArrayList<>();

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
		stocksIdCount.put(event.data().id(), event.count());
		stocks.add(event.data());
	}

	/**
	 * check if the specific goods is in stock, if so, publish an event ADD_TO_CART
	 * 
	 * @param event Event of Goods to check
	 */
	private void checkStock(Event<Goods> event) {
		if (!stocksIdCount.containsKey(event.data().id())) {
			System.out.print("The store doesn't have this goods.\n");
			return;
		}
		if (stocksIdCount.get(event.data().id()) < event.count()) {
			System.out.println("out of stock. goods ID: " + event.data().id());
			return;
		}
		EventManager.getInstance().publish(new GoodsEvent(EventType.ADD_TO_CART, event.data(), event.count()));
	}

	/**
	 * deduct stock after user complete purchase
	 * 
	 * @param event Event of Goods to be deducted
	 */
	private void purchase(Event<Goods> event) {
		for (Goods goods : stocks) {
			if (stocksIdCount.containsKey(goods.id()) && goods.name().equals(event.data().name())) {
				stocksIdCount.put(goods.id(), stocksIdCount.get(goods.id()) - event.count());
			}

		}
	}

	/**
	 * show stocks of this shop
	 */
	private void listShop() {
		if (stocksIdCount.isEmpty()) {
			System.out.print("This shop does not sell anything.\n");
			return;
		}
		System.out.print("================================================================================\n");
		System.out.printf("%-4s%-22s%-40s%-8s%-6s\n", "ID", "name", "description", "price", "count");
		System.out.print("--------------------------------------------------------------------------------\n");
		for (Goods goods : stocks) {
			if (stocksIdCount.containsKey(goods.id())) {
				System.out.printf("%-4s%-22s%-40s%-8s%-6s\n", String.valueOf(goods.id()), goods.name(),
						goods.description(), String.valueOf(goods.price()),
						String.valueOf(stocksIdCount.get(goods.id())));

			}

		}
		System.out.print("================================================================================\n");
	}
}
