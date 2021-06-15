package org.ntutssl.termfrequency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Output implements EventListener {
	private EventManager eventManager;
	private int range;
	private String order;
	private List<String> result;

	public Output(EventManager eventManager, int range, String order) {
		if (!order.equals("acs") && !order.equals("des")) {
			throw new WordFrequencyException("The order should be asc or des.");
		}

		if (range < 0) {
			throw new WordFrequencyException("Out of range! The range should not be minus.");
		}

		this.eventManager = eventManager;
		this.range = range;
		this.order = order;
		this.result = new ArrayList<>();
		this.eventManager.subscribe(EventType.OUTPUT, this);
	}

	public void onEvent(EventType eventType, String event) {
		if (eventType.equals(EventType.OUTPUT)) {
			Map<String, Integer> wordMap = new HashMap<>();
			String[] words = event.split("\n");
			for (String word : words) {
				String[] splitWord = word.split(": ");
				splitWord[0] = splitWord[0].replace("\\W", "");
        		splitWord[1] = splitWord[1].replace("\\W", "");
				wordMap.put(splitWord[0], Integer.parseInt(splitWord[1]));
			}
			sortByValue(wordMap);
		}
	}

	private void sortByValue(Map<String, Integer> wordMap) {
		List<Entry<String, Integer>> wordList = new LinkedList<>(wordMap.entrySet());

		switch (order) {
			case "asc":
				Collections.sort(wordList, new Comparator<Entry<String, Integer>>() {
					@Override
					public int compare(Entry<String, Integer> word1, Entry<String, Integer> word2) {
						return (word1.getValue()).compareTo(word2.getValue());
					}
				});
				break;

			case "des":
				Collections.sort(wordList, new Comparator<Entry<String, Integer>>() {
					@Override
					public int compare(Entry<String, Integer> word1, Entry<String, Integer> word2) {
						return (word2.getValue()).compareTo(word1.getValue());
					}
				});
				break;
		}

		for (Entry<String, Integer> word : wordList) {
            result.add(word.getKey() + ": " + word.getValue() + "\n");
        }

		int count = 0;
		for (String word : result) {
			if (count >= range) break;
			System.out.println(word);
			count++;
		}
	}
}
