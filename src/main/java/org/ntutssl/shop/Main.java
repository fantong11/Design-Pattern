package org.ntutssl.shop;

public class Main {
	public static void main(String args[]) {
		new GoodsParser();
		new ShoppingCart();
		new Shop();
		EventManager.getInstance().publish(new StringEvent(EventType.IMPORT_REPLENISH_LIST, "input/replenish_list.json"));
		new InstructionHandler().run();
	}
}
