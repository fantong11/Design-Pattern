package org.ntutssl.shop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Collection implements Goods {

	private List<Goods> goodses = new ArrayList<>();
	private int id;
	private String name;
	private String desc;

	/**
	 * consturctor
	 * 
	 * @param id   goods id which should not be negative
	 * @param name
	 * @param desc
	 */
	public Collection(int id, String name, String desc) {
		if (id < 1) throw new ShopException("ID should be positive.");
		this.id = id;
		this.name = name;
		this.desc = desc;
	}

	public int id() {
		return id;
	}

	public double price() {
		double price = 0;
		for (Goods goods : goodses) {
			price += goods.price();
		}
		return price;
	}

	public String name() {
		return name;
	}

	public String description() {
		return desc;
	}

	@Override
	public void add(Goods goods) {
		goodses.add(goods);
	}

	@Override
	public Iterator<Goods> iterator() {
		return goodses.iterator();
	}
}