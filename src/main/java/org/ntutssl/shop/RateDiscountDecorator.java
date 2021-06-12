package org.ntutssl.shop;

public class RateDiscountDecorator extends Decorator {

	private double rate;
	/**
	 * constructor
	 * 
	 * @param goods goods to be decorated
	 * @param rate  discount rate, which should be [0, 1)
	 */
	public RateDiscountDecorator(Goods goods, double rate) {
		super(goods);
		this.rate = rate;
	}

	public int id() {
		return goods.id();
	}

	public double price() {
		return goods.price();
	}

	public String name() {
		return goods.name();
	}

	public String description() {
		return goods.description();
	}
}
