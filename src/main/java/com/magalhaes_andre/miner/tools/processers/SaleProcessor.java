package com.magalhaes_andre.miner.tools.processers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.magalhaes_andre.miner.control.Sales;
import com.magalhaes_andre.miner.control.Salesmans;
import com.magalhaes_andre.miner.model.Item;
import com.magalhaes_andre.miner.model.Sale;
import com.magalhaes_andre.miner.model.builder.ItemBuilder;
import com.magalhaes_andre.miner.model.builder.SaleBuilder;
import com.magalhaes_andre.miner.model.builder.SalesmanBuilder;

public class SaleProcessor extends Processor {
	private Character delimiter;
	private String line;
	private Sales saleControl;
	private Salesmans salesmanControl;

	public SaleProcessor(Character delimiter, String line, Sales saleControl, Salesmans salesmanControl) {
		this.delimiter = delimiter;
		this.line = line;
		this.saleControl = saleControl;
		this.salesmanControl = salesmanControl;
	}

	public Optional<Sale> processSale() {
		List<Integer> delimiterOccurs = delimiterOccurs(delimiter, line);

		if (delimiterOccurs.size() == 3) {

			String sellCode = line.substring(delimiterOccurs.get(0) + 1, delimiterOccurs.get(1));
			List<Item> items = processItemList(line.substring(line.indexOf("[") + 1, line.indexOf("]")));
			String salesman = line.substring(delimiterOccurs.get(2) + 1, line.length());

			if (salesmanControl.findByName(salesman).isPresent()) {
				return Optional.of(SaleBuilder.builder().code(Integer.parseInt(sellCode)).items(items)
						.salesman(salesmanControl.findByName(salesman).get()).build());
			} else {
				return Optional.of(SaleBuilder.builder().code(Integer.parseInt(sellCode)).items(items)
						.salesman(SalesmanBuilder.builder().cpf("000.000.000-00").name(salesman)
								.salary(0).build())
						.build());
			}
		} else {
			return Optional.of(new Sale());
		}

	}

	protected List<Item> processItemList(String line) {
		List<Character> characters = new ArrayList<Character>();
		for (Character character : line.toCharArray()) {
			if (!Character.isDigit(character)) {
				if (!character.toString().equals(".")) {
					if (!characters.contains(character)) {
						characters.add(character);
					}
				}
			}
		}
		List<Item> items = itemProcess(characters.get(0), characters.get(1), line);
		return items;
	}

	protected List<Item> itemProcess(Character delimiter, Character splitter, String line) {
		List<String> lines = new ArrayList<String>(Arrays.asList(line.split(splitter.toString())));
		List<Item> items = new ArrayList<Item>();
		for (String item : lines) {
			List<Integer> delimiterOccurs = delimiterOccurs(delimiter, item);
			if (delimiterOccurs.size() == 2) {
				String itemCode = item.substring(0, delimiterOccurs.get(0));
				String quantity = item.substring(delimiterOccurs.get(0) + 1, delimiterOccurs.get(1));
				String price = item.substring(delimiterOccurs.get(1) + 1);
				items.add(ItemBuilder.builder().code(Integer.parseInt(itemCode)).quantity(Integer.parseInt(quantity))
						.price(Double.parseDouble(price)).build());
			}
		}
		return items;
	}
}