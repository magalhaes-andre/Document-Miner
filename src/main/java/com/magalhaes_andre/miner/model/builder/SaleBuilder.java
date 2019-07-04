package com.magalhaes_andre.miner.model.builder;

import java.util.List;

import com.magalhaes_andre.miner.model.Item;
import com.magalhaes_andre.miner.model.Sale;
import com.magalhaes_andre.miner.model.Salesman;

public class SaleBuilder {
private Sale sale;
	
	public SaleBuilder() {
		this.sale = new Sale();
	}
	
	public static SaleBuilder builder() {
		return new SaleBuilder();
	}
	
	public SaleBuilder code(int code) {
		this.sale.setCode(code);
		return this;
	}
	
	public SaleBuilder items(List<Item> items) {
		this.sale.setItems(items);
		return this;
	}
	
	public SaleBuilder salesman(Salesman salesman) {
		this.sale.setSalesman(salesman);
		return this;
	}
	
	public Sale build() {
		return this.sale;
	}
}
