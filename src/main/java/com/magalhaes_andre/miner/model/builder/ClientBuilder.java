package com.magalhaes_andre.miner.model.builder;

import com.magalhaes_andre.miner.model.Client;

public class ClientBuilder {
	private Client client;
	
	public ClientBuilder() {
		this.client = new Client();
	}
	
	public static ClientBuilder builder() {
		return new ClientBuilder();
	}
	
	public ClientBuilder cnpj(String cnpj) {
		this.client.setCnpj(cnpj);
		return this;
	}
	
	public ClientBuilder nome(String name) {
		this.client.setName(name);
		return this;
	}
	
	public ClientBuilder businessArea(String businessArea) {
		this.client.setBusinessArea(businessArea);
		return this;
	}
	
	public Client build() {
		return this.client;
	}
}
