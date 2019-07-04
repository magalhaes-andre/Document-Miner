package com.magalhaes_andre.miner.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.magalhaes_andre.miner.model.Client;
import com.magalhaes_andre.miner.model.Sale;
import com.magalhaes_andre.miner.model.Salesman;

public class Salesmans {
	List<Salesman> salesmans = new ArrayList<Salesman>();

	public void addSalesman(Salesman vendedor) {
		salesmans.add(vendedor);
	}

	public Optional<Salesman> findByName(String salesmanName) {
		return salesmans.stream().filter(s -> s.getName().toLowerCase().startsWith(salesmanName.toLowerCase()))
				.findFirst();
	}

	public List<Salesman> list() {
		return salesmans;
	}

	public int salesmanQuantity() {
		return salesmans.size();
	}

}
