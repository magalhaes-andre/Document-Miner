package com.magalhaes_andre.miner.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.magalhaes_andre.miner.model.Client;
import com.magalhaes_andre.miner.model.Salesman;

public class Clients {
	List<Client> clients = new ArrayList<Client>();

	public void addClient(Client client) {
		clients.add(client);
	}

	public Optional<Client> findByName(String clientName) {
		return clients.stream().filter(c -> c.getName().toLowerCase().startsWith(clientName.toLowerCase())).findFirst();
	}

	public List<Client> listClients() {
		return clients;
	}

	public int clientQuantity() {
		return clients.size();
	}
}
