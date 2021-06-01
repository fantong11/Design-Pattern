package org.ntutssl.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
	private Map<EventType, List<EventListener>> listenerMap = new HashMap<>();

	public void subscribe(EventType eventType, EventListener listener) {
		listenerMap.get(eventType).add(listener);
	}

	public <T> void publish(Event<T> event) {
		for (EventListener listener : listenerMap.get(event.type())) {
			listener.onEvent(event);
		}

	}

	// SINGLETON implementation below
	private static EventManager instance;

	public static EventManager getInstance() {
		if (instance == null) {
			instance = new EventManager();
		}
		return instance;
	}

	private EventManager() {
		listenerMap.put(EventType.IMPORT_SHOPPING_LIST, new ArrayList<>());
		listenerMap.put(EventType.IMPORT_REPLENISH_LIST, new ArrayList<>());
		listenerMap.put(EventType.REPLENISH, new ArrayList<>());
		listenerMap.put(EventType.CHECK_STOCK, new ArrayList<>());
		listenerMap.put(EventType.PAY, new ArrayList<>());
		listenerMap.put(EventType.ADD_TO_CART, new ArrayList<>());
		listenerMap.put(EventType.PURCHASE, new ArrayList<>());
		listenerMap.put(EventType.LIST_CART, new ArrayList<>());
		listenerMap.put(EventType.LIST_SHOP, new ArrayList<>());
	}
}
