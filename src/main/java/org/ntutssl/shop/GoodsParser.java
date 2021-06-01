package org.ntutssl.shop;

import com.google.gson.JsonObject;

public class GoodsParser implements EventListener {

	public GoodsParser() {
		EventManager.getInstance().subscribe(EventType.IMPORT_SHOPPING_LIST, this);
		EventManager.getInstance().subscribe(EventType.IMPORT_REPLENISH_LIST, this);
	}

	public void onEvent(Event event) {
		switch (event.type()) {
			case IMPORT_SHOPPING_LIST:
				importShoppingCartList(event);
				break;

			case IMPORT_REPLENISH_LIST:
				importReplenishmentList(event);
				break;

			default:
				break;
		}
	}

	/**
	 * private methods are not necessary, but you can takce them as reference.
	 */
	private void importShoppingCartList(Event<String> event) {
	}

	private void importReplenishmentList(Event<String> event) {
	}

	private Goods parse(JsonObject jsonObj) {
		return null;
	}
}
