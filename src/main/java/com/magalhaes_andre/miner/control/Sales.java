package com.magalhaes_andre.miner.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.magalhaes_andre.miner.model.Item;
import com.magalhaes_andre.miner.model.Sale;
import com.magalhaes_andre.miner.model.Salesman;

public class Sales {
	List<Sale> sales = new ArrayList<Sale>();
	Clients clientControl;
	Salesmans salesmanControl;

	public Sales(Clients clientControl, Salesmans salesmanControl) {
		this.clientControl = clientControl;
		this.salesmanControl = salesmanControl;
	}

	public void addSale(Sale sale) {
		sales.add(sale);
	}

	public List<Sale> list(){
		return sales;
	}

	public Sale worstSale() {
		Collections.sort(sales);
		return sales.get(sales.size()-1);
	}

	public Sale bestSale() {
		Collections.sort(sales);
		return sales.get(0);
	}
}
