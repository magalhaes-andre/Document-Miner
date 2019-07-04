package com.magalhaes_andre.miner.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.magalhaes_andre.tema_final.model.Item;
import com.magalhaes_andre.tema_final.model.Sale;
import com.magalhaes_andre.tema_final.model.builder.ItemBuilder;
import com.magalhaes_andre.tema_final.model.builder.SaleBuilder;

public class SalesOrderingTest {

	private List<Sale> sales;
	private Sale lowSale;
	private Sale highSale;

	@Before
	public void init() {
		sales = new ArrayList<Sale>();
		List<Item> lowItems = new ArrayList<Item>();
		List<Item> highItems = new ArrayList<Item>();
		lowItems.add(ItemBuilder.builder().code(01).quantity(10).price(10).build());
		lowSale = SaleBuilder.builder().code(00).items(lowItems).build();
		sales.add(lowSale);
		highItems.add(ItemBuilder.builder().code(03).quantity(20).price(10).build());
		highSale = SaleBuilder.builder().code(01).items(highItems).build();

		sales.add(highSale);
	}

	@Test
	public void testBestSaleOrdering() {
		Collections.sort(sales);
		Assert.assertTrue(sales.get(0).getCode() == highSale.getCode());
	}

	@Test
	public void testWorstSaleOrdering() {
		Collections.sort(sales);
		Assert.assertTrue(sales.get(sales.size() - 1).getCode() == (lowSale.getCode()));
	}
}
