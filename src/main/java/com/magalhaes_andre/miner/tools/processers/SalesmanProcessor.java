package com.magalhaes_andre.miner.tools.processers;


import java.util.List;
import java.util.Optional;

import com.magalhaes_andre.miner.control.Salesmans;
import com.magalhaes_andre.miner.model.Salesman;
import com.magalhaes_andre.miner.model.builder.SalesmanBuilder;

public class SalesmanProcessor extends Processor{
    private Character delimiter;
    private String line;
    private Salesmans salesmanControl;

    public SalesmanProcessor(Character delimiter, String line, Salesmans salesmanControl){
        this.delimiter = delimiter;
        this.line = line;
        this.salesmanControl = salesmanControl;
    }

    public Optional<Salesman> processSalesman() {
		List<Integer> delimiterOccurs = delimiterOccurs(delimiter, line);
		if (delimiterOccurs.size() == 3) {
			String cpf = line.substring(delimiterOccurs.get(0) + 1, delimiterOccurs.get(1));
			String name = line.substring(delimiterOccurs.get(1) + 1, delimiterOccurs.get(2));
            String salary = line.substring(delimiterOccurs.get(2) + 1, line.length());
            
            if(salesmanControl.findByName(name).isPresent()){
                return salesmanControl.findByName(name);        
            }else {
            	return Optional.of(SalesmanBuilder.builder().cpf(cpf).name(name).salary(Double.parseDouble(salary)).build());
            }
		}else {
			return Optional.of(new Salesman());
		}
    }
    
    
}