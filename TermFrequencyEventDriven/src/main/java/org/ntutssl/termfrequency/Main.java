package org.ntutssl.termfrequency;

public class Main {
	public static void main(String[] args) {
		EventManager eventManager = new EventManager();
		new StopWordManager(eventManager);
		new DataStorageManager(eventManager);
		new WordFrequencyManager(eventManager);
		new WordFrequencyController(eventManager);
		new Output(eventManager, Integer.parseInt(args[1]), args[2]);

		eventManager.publish(EventType.START, args[0]);
	}
}