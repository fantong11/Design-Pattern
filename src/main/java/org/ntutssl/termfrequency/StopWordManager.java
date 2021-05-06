package org.ntutssl.termfrequency;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StopWordManager implements EventListener {
	private EventManager eventManager;
	private List<String> stopWordList;

	public StopWordManager(EventManager eventManager) {
		this.eventManager = new EventManager();
		this.stopWordList = new ArrayList<>();
		this.eventManager.subscribe(EventType.LOAD, this);
		this.eventManager.subscribe(EventType.VALIDATE, this);
	}

	public List<String> getStopWords() {
		return stopWordList;
	}

	public void onEvent(EventType eventType, String event) {
		System.out.println("fsdfsafasd");
		System.out.println("fsdfsafasd" + event);
		switch (eventType) {
			case LOAD:
				handleInput("input/stop_words.txt", "[\\W_]+");
				break;

			case VALIDATE:
				wordIsContain(event);
				break;

			default:
				break;
		}

	}

	private void handleInput(String filePath, String pattern) {
		try (Scanner scanner = new Scanner(new File(filePath))) {
			scanner.useDelimiter(pattern);
			while (scanner.hasNext()) {
				stopWordList.add(scanner.next().toLowerCase());
			}
		} catch (IOException e) {
			throw new WordFrequencyException("File not found.");
		}

		for (char c = 33; c <= 126; c++) {
			stopWordList.add(Character.toString(c));
		}
	}

	private void wordIsContain(String word) {
		if (!stopWordList.contains(word)) {
			eventManager.publish(EventType.COUNT, word);
		}
	}
}