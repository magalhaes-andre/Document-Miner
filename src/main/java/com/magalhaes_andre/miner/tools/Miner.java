package com.magalhaes_andre.miner.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.magalhaes_andre.miner.control.Clients;
import com.magalhaes_andre.miner.control.Sales;
import com.magalhaes_andre.miner.control.Salesmans;
import com.magalhaes_andre.miner.dao.ArchiveDAO;
import com.magalhaes_andre.miner.error.UnknownTypeOfData;
import com.magalhaes_andre.miner.model.Client;
import com.magalhaes_andre.miner.model.Item;
import com.magalhaes_andre.miner.model.Sale;
import com.magalhaes_andre.miner.model.Salesman;
import com.magalhaes_andre.miner.tools.processers.ClientProcessor;
import com.magalhaes_andre.miner.tools.processers.SaleProcessor;
import com.magalhaes_andre.miner.tools.processers.SalesmanProcessor;

public abstract class Miner {

	private ArchiveDAO archiveDAO;
	private String archiveExtension;
	private Clients clientControl;
	private Salesmans salesmanControl;
	private Sales saleControl;

	public Miner(ArchiveDAO archiveDAO, String archiveExtension, Clients clientControl, Salesmans salesmanControl) {
		this.archiveDAO = archiveDAO;
		this.archiveExtension = archiveExtension;
		this.clientControl = clientControl;
		this.salesmanControl = salesmanControl;
		this.saleControl = new Sales(clientControl, salesmanControl);
	}

	public void renewControllers() {
		this.clientControl = new Clients();
		this.salesmanControl = new Salesmans();
		this.saleControl = new Sales(clientControl, salesmanControl);
	}

	public void mine() {
		readFiles(readFolder());
	};

	protected List<File> readFolder() {
		List<File> archives = archiveDAO.readFolder(archiveExtension);
		return archives;
	}

	protected void readFiles(List<File> filesToProcess) {
		for (File file : filesToProcess) {
			extractLines(archiveDAO.readFile(file));
			Report.generate(clientControl, saleControl, salesmanControl, archiveDAO, file.getName());
			renewControllers();
		}

	}

	protected void extractLines(List<String> lines) {
		for (String line : lines) {
			processDefiner(line);
		}
	}

	protected void processDefiner(String line) {
		Character delimiter = line.charAt(3);
		Character type = line.charAt(2);

		switch (Character.getNumericValue(type)) {
		case 1:
			Optional<Salesman> processedSalesman = new SalesmanProcessor(delimiter, line, salesmanControl)
					.processSalesman();
			processedSalesman.ifPresent(s -> salesmanControl.addSalesman(s));
			break;
		case 2:
			Optional<Client> processedClient = new ClientProcessor(delimiter, line, clientControl).processClient();
			processedClient.ifPresent(c -> clientControl.addClient(c));
			break;
		case 3:
			Optional<Sale> processedSale = new SaleProcessor(delimiter, line, saleControl, salesmanControl)
					.processSale();
			processedSale.ifPresent(s -> saleControl.addSale(s));
			break;
		default:
			throw new UnknownTypeOfData(type.toString());
		}
	}
}
