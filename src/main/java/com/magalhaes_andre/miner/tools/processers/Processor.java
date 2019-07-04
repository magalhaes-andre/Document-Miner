package com.magalhaes_andre.miner.tools.processers;

import java.util.ArrayList;
import java.util.List;

import com.magalhaes_andre.miner.control.Clients;
import com.magalhaes_andre.miner.control.Sales;
import com.magalhaes_andre.miner.control.Salesmans;
import com.magalhaes_andre.miner.model.Item;
import com.magalhaes_andre.miner.model.builder.ClientBuilder;
import com.magalhaes_andre.miner.model.builder.SaleBuilder;
import com.magalhaes_andre.miner.model.builder.SalesmanBuilder;

public abstract class Processor {

	protected List<Integer> delimiterOccurs(Character delimiter, String line) {

		List<Integer> delimiterOccurs = new ArrayList<Integer>();

		for (int counter = 0; counter < line.length(); counter++) {
			if (line.charAt(counter) == delimiter) {
				delimiterOccurs.add(counter);
			}
		}
		return delimiterOccurs;
	}
}