package com.magalhaes_andre.miner;

import com.magalhaes_andre.miner.tools.Miner;
import com.magalhaes_andre.miner.tools.MinerDAT;

public class Main {
	public static void main(String[] args) {
		Miner datMiner = new MinerDAT();
		while(true) {
			datMiner.mine();
		}
	}
}
