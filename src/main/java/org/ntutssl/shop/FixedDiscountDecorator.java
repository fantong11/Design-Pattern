package org.ntutssl.shop;

public class FixedDiscountDecorator extends Decorator {
	private double discount;
	/**
	 * counstructor
	 * 
	 * @param goods    goods to be decorated
	 * @param discount fixed discount, which should be lower than the price of goods
	 */
	public FixedDiscountDecorator(Goods goods, double discount) {
		super(goods);
		this.discount = discount;
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
