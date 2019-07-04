package com.magalhaes_andre.miner.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.magalhaes_andre.miner.control.Clients;
import com.magalhaes_andre.miner.control.Sales;
import com.magalhaes_andre.miner.control.Salesmans;
import com.magalhaes_andre.miner.dao.ArchiveDAO;
import com.magalhaes_andre.miner.model.Item;
import com.magalhaes_andre.miner.model.Sale;

public class Report {

	public static void generate(Clients clientControl, Sales saleControl, Salesmans salesmanControl,
			ArchiveDAO archiveDAO, String fileName) {

		List<String> reportContent = new ArrayList<String>();
		Sale worstSale = saleControl.worstSale();
		Sale bestSale = saleControl.bestSale();
		double totalPrice = 0;

		reportContent.add("Quantidade de Clientes: " + clientControl.clientQuantity());
		reportContent.add("Quantidade de Vendedores: " + salesmanControl.salesmanQuantity());
		reportContent.add(
				"Melhor venda!!\nCodigo:" + bestSale.getCode() + "\nVendedor: " + bestSale.getSalesman().getName());

		for (Item item : bestSale.getItems()) {
			reportContent.add("Código Produto: " + item.getCode() + " " + "Preço do Produto: " + item.getPrice());
			totalPrice += item.getPrice();
		}
		reportContent.add("Preço total da venda: " + totalPrice);
		totalPrice = 0;
		reportContent.add("\n\nPior vendedor: " + worstSale.getSalesman().getName() + "\n");
		reportContent.add("Na venda: \n");
		for (Item item : worstSale.getItems()) {
			reportContent.add("Código Produto: " + item.getCode() + " " + "Preço do Produto: " + item.getPrice());
			totalPrice += item.getPrice();
		}
		reportContent.add("Preço total da venda: " + totalPrice);
		archiveDAO.writeReport(fileName + ".done", reportContent);
	}
}
