package org.ntutssl.shop;

public class Main {
	public static void main(String args[]) {
		EventManager.getInstance().publish(new StringEvent(EventType.IMPORT_REPLENISH_LIST, "input/replenish_list.json"));
		EventManager.getInstance().publish(new StringEvent(EventType.IMPORT_SHOPPING_LIST, "input/shopping_list.json"));
	}
}
