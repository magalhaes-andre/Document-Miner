package com.magalhaes_andre.miner.model.builder;

import com.magalhaes_andre.miner.model.Item;

public class ItemBuilder {
private Item item;
	
	public ItemBuilder() {
		this.item = new Item();
	}
	
	public static ItemBuilder builder() {
		return new ItemBuilder();
	}
	
	public ItemBuilder code(int code) {
		this.item.setCode(code);
		return this;
	}
	
	public ItemBuilder quantity(int quantity) {
		this.item.setQuantity(quantity);
		return this;
	}
	
	public ItemBuilder price(double price ) {
		this.item.setPrice(price);
		return this;
	}
	
	public Item build() {
		return this.item;
	}
}
