package org.ntutssl.termfrequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
	private Map<EventType, List<EventListener>> listenerMap;

	public EventManager() {
		listenerMap = new HashMap<>();
		listenerMap.put(EventType.START, new ArrayList<>());
		listenerMap.put(EventType.LOAD, new ArrayList<>());
		listenerMap.put(EventType.RUN, new ArrayList<>());
		listenerMap.put(EventType.VALIDATE, new ArrayList<>());
		listenerMap.put(EventType.COUNT, new ArrayList<>());
		listenerMap.put(EventType.EOF, new ArrayList<>());
		listenerMap.put(EventType.OUTPUT, new ArrayList<>());
	}

	public void subscribe(EventType eventType, EventListener listener) {
		listenerMap.get(eventType).add(listener);
	}

	public void publish(EventType eventType, String event) {
		for (EventListener listener : listenerMap.get(eventType)) {
			listener.onEvent(eventType, event);
		}
	}
}