package org.ntutssl.shop;

import java.util.List;

public class ShoppingCart implements EventListener {
	// private EventManager eventManager;
	private List<Event<Goods>> events;

	public ShoppingCart() {
		EventManager.getInstance().subscribe(EventType.PAY, this);
		EventManager.getInstance().subscribe(EventType.LIST_CART, this);
	}

	public void onEvent(Event event) {
		switch (event.type()) {
			case ADD_TO_CART:
				add(event);
				break;
				
			case PAY:
				pay();
				break;
		
			case LIST_CART:
				listCart();
				break;

			default:
				break;
		}
	}

	/**
	 * private methods are not necessary, but you can takce them as reference.
	 */
	/**
	 * add goods to shopping cart
	 * 
	 * @param goodsEvent the data of this event is the goods to be added
	 */
	private void add(Event<Goods> event) {
		events.add(event);
	}

	/**
	 * pay for all items in the shopping cart
	 */
	private void pay() {
	}

	/**
	 * list all items in the shopping cart
	 */
	private void listCart() {
	}
}
