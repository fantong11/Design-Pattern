package org.ntutssl.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

	private Map<EventType, List<EventListener>> bulletin = new HashMap<>();

	public void subscribe(EventType eventType, EventListener listener) {
		if (bulletin.containsKey(eventType)) {
			List<EventListener> listeners = bulletin.get(eventType);
			listeners.add(listener);
			bulletin.put(eventType, listeners);
		} else {
			List<EventListener> listeners = new ArrayList<>();
			listeners.add(listener);
			bulletin.put(eventType, listeners);
		}
	}

	public <T> void publish(Event<T> event) {
		// if (bulletin.get(event.type()) == null) return;
		for (EventListener listener : bulletin.get(event.type())) {
			// System.out.println(event.type().toString());
			listener.onEvent(event);
		}
	}

	// SINGLETON
	// SINGLETON implementation below
	private static EventManager instance;

	public static EventManager getInstance() {
		if (instance == null) {
			instance = new EventManager();
		}
		return instance;
	}

	private EventManager() {
		this.reset();
	}

	public void reset() {
		instance = null;
	}
}
