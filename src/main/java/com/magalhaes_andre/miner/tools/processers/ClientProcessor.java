package com.magalhaes_andre.miner.tools.processers;

import java.util.List;
import java.util.Optional;

import com.magalhaes_andre.miner.control.Clients;
import com.magalhaes_andre.miner.model.Client;
import com.magalhaes_andre.miner.model.builder.ClientBuilder;

public class ClientProcessor extends Processor {
	private Character delimiter;
	private String line;
	private Clients clientControl;

	public ClientProcessor(Character delimiter, String line, Clients clientControl) {
		this.delimiter = delimiter;
		this.line = line;
		this.clientControl = clientControl;
	}

	public Optional<Client> processClient() {
		List<Integer> delimiterOccurs = delimiterOccurs(delimiter, line);
		if (delimiterOccurs.size() == 3) {
			String cnpj = line.substring(delimiterOccurs.get(0) + 1, delimiterOccurs.get(1));
			String name = line.substring(delimiterOccurs.get(1) + 1, delimiterOccurs.get(2));
			String businessArea = line.substring(delimiterOccurs.get(2) + 1, line.length());
			if (clientControl.findByName(name).isPresent()) {
				return clientControl.findByName(name);
			} else {
				return Optional.of(ClientBuilder.builder().cnpj(cnpj).nome(name).businessArea(businessArea).build());
			}
		} else {
			return Optional.of(new Client());
		}
	}
}