package org.ntutssl.termfrequency;

public class WordFrequencyController implements EventListener {
	private EventManager eventManager;

	public WordFrequencyController(EventManager eventManager) {
		this.eventManager = eventManager;
		eventManager.subscribe(EventType.START, this);
	}

	public void onEvent(EventType eventType, String event) {
		switch (eventType) {
			case START:
				eventManager.publish(EventType.LOAD, event);
				eventManager.publish(EventType.RUN, "");
				break;
		
			default:
				break;
		}
	}
}