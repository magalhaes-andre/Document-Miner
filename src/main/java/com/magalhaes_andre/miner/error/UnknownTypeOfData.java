package com.magalhaes_andre.miner.error;

public class UnknownTypeOfData extends RuntimeException{
	
	public UnknownTypeOfData(String typeOfData) {
		super("This type of data is not supported: "+typeOfData);
	}
}
