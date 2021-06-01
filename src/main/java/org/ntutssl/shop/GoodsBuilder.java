package org.ntutssl.shop;

import java.util.Stack;

public class GoodsBuilder {
	private Goods goods;
	private Stack<Goods> stack;

	public GoodsBuilder() {
		this.stack = new Stack<>();
	}

	public void buildMerchandise(int id, String name, String desc, double price) {
		if (stack.empty()) {
			goods = new Merchandise(id, name, desc, price);
		} else {
			stack.peek().add(new Merchandise(id, name, desc, price));
		}
	}

	public void startBuildCollection(int id, String name, String desc) {
		stack.push(new Collection(id, name, desc));
	}

	public void endBuildCollection() {
		Goods currentGoods = stack.pop();

		if (stack.empty()) {
			goods = currentGoods;
		} else {
			stack.peek().add(currentGoods);
		}
	}

	public Goods getResult() {
		return goods;
	}
}
