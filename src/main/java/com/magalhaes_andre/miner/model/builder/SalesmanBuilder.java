package com.magalhaes_andre.miner.model.builder;

import com.magalhaes_andre.miner.model.Salesman;

public class SalesmanBuilder {
	private Salesman salesman;
	
	public SalesmanBuilder() {
		this.salesman = new Salesman();
	}
	
	public static SalesmanBuilder builder() {
		return new SalesmanBuilder();
	}
	
	public SalesmanBuilder cpf(String cpf) {
		this.salesman.setCpf(cpf);
		return this;
	}
	
	public SalesmanBuilder name(String name) {
		this.salesman.setName(name);
		return this;
	}
	
	public SalesmanBuilder salary(double salary) {
		this.salesman.setSalary(salary);
		return this;
	}
	
	public Salesman build() {
		return this.salesman;
	}
}
