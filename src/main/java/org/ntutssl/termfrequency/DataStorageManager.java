package org.ntutssl.termfrequency;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataStorageManager implements EventListener{
	private EventManager eventManager;
	private List<String> words;

	public DataStorageManager(EventManager eventManager) {
		this.eventManager = eventManager;
		this.words = new ArrayList<>();
		this.eventManager.subscribe(EventType.LOAD, this);
		this.eventManager.subscribe(EventType.RUN, this);
	}

	public List<String> getWords() {
		return words;
	}

	public void onEvent(EventType eventType, String event) {
		switch (eventType) {
			case LOAD:
				handleInput(event, "[\\W_]+");
				break;

			case RUN:
				run(event);
				break;

			default:
				break;
		}
	}

	private void handleInput(String filePath, String pattern) {
		try (Scanner scanner = new Scanner(new File(filePath))) {
			scanner.useDelimiter(pattern);
			while (scanner.hasNext()) {
				words.add(scanner.next().toLowerCase());
			}
		} catch (IOException e) {
			throw new WordFrequencyException("File not found.");
		}
	}

	private void run(String event) {
		for (String word : words) {
			eventManager.publish(EventType.VALIDATE, word);
		}
		eventManager.publish(EventType.EOF, event);
		
	}
}