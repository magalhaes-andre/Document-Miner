package com.magalhaes_andre.miner.tools;

import com.magalhaes_andre.miner.control.Clients;
import com.magalhaes_andre.miner.control.Sales;
import com.magalhaes_andre.miner.control.Salesmans;
import com.magalhaes_andre.miner.dao.ArchiveDAO;

public class MinerDAT extends Miner {
	Clients clientControl = new Clients();
	Salesmans salesmanControl = new Salesmans();
	Sales saleControl = new Sales(clientControl, salesmanControl);
	
	public MinerDAT(){
		super(new ArchiveDAO(), ".dat", new Clients(), new Salesmans());
	}
}
