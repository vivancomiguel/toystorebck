package com.toystore.service;

import java.util.List;

import com.toystore.entity.Costumer;
import com.toystore.entity.dto.*;

public interface ICostumerService {
	public List<CostumerDto> findAllCostumers() throws Exception;
	
	public Costumer findCostumerById(Long id) throws Exception;
	
	public CostumerDto findCostumerByEmail(AuthCostumerDto costumer);		
	
	public boolean existsCostumerById(Long id);
	
	public boolean existsCostumerByEmail(String email);
	
	public CostumerDto saveCostumer(Costumer costumer) throws Exception;
	
	public CostumerDto updateCostumer(Costumer costumer) throws Exception;
	
	public String deleteCostumerById(Long id) throws Exception;
	
	public CostumerDto convertCostumerToDto(Costumer costumer);
	
	public List<CostumerDto> convertAllCostumersToDto(List<Costumer> costumers);
}
