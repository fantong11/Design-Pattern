package org.ntutssl.shop;

public class Merchandise implements Goods {

	private int id;
	private String name;
	private String desc;
	private double price;

	/**
	 * constructor
	 * 
	 * @param id    goods id which should not be negative
	 * @param name
	 * @param desc
	 * @param price goods price which should not be negative
	 */
	public Merchandise(int id, String name, String desc, double price) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.price = price;
	}

	public int id() {
		return id;
	}

	public double price() {
		return price;
	}

	public String name() {
		return name;
	}

	public String description() {
		return desc;
	}
}