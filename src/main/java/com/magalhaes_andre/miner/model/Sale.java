package com.magalhaes_andre.miner.model;

import java.util.List;
import java.util.stream.Collectors;

public class Sale implements Comparable<Sale> {

	private int code;
	private List<Item> items;
	private Salesman salesman;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}
	
	public int getMax() {
		return (int) Math.round(items.stream().map(i -> i.getPrice()*i.getQuantity()).reduce(Double::sum).get());
	}

	@Override
	public int compareTo(Sale sale) {
		return sale.getMax() - this.getMax();
	}
}
