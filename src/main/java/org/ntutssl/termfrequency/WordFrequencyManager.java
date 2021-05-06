package org.ntutssl.termfrequency;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WordFrequencyManager implements EventListener {
	private EventManager eventManager;
	private Map<String, Integer> words;
	private String output;

	public WordFrequencyManager(EventManager eventManager) {
		this.eventManager = eventManager;
		this.words = new HashMap<>();
		this.output = "";
		this.eventManager.subscribe(EventType.COUNT, this);
		this.eventManager.subscribe(EventType.EOF, this);
	}

	public int getNumOfWords() {
		return words.size();
	}

	public String getOutput() {
		return this.output;
	}

	public void onEvent(EventType eventType, String event) {
		switch (eventType) {
			case COUNT:
				incrementCount(event);
				break;

			case EOF:
				outputWordFequency();
				break;

			default:
				break;
		}
	}

	private void incrementCount(String word) {
		if (words.containsKey(word)) {
			words.put(word, words.get(word) + 1);
		} else {
			words.put(word, 1);
		}
	}

	private void outputWordFequency() {
		for (Entry<String, Integer> word : words.entrySet()) {
			output += word.getKey() + ": " + word.getValue() + "\n";
		}
		eventManager.publish(EventType.OUTPUT, output);
	}

}