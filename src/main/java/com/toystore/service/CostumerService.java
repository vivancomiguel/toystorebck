package com.toystore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toystore.entity.*;
import com.toystore.entity.dto.AuthCostumerDto;
import com.toystore.entity.dto.CostumerDto;
import com.toystore.repository.*;

@Service
public class CostumerService implements ICostumerService {
	@Autowired
	ICostumerRepository costumerRepository;
	
	/*// GET
	public Iterable<Costumer> getAllCostumer(){
		return costumerRepository.findAll();
	}
	
	public Costumer findCostumerById(Long id) {
		return costumerRepository.findById(id).orElseThrow(
			()->new IllegalStateException("El usuarion con el id "+id+" no existe.")
		);
	}
	
	public boolean existCostumerById(Long id) {
		return costumerRepository.existsById(id);
	}
	
	// POST
	public void saveCostumer(Costumer costumer) {
		costumerRepository.save(costumer);
	}*/

	@Override
	public List<CostumerDto> findAllCostumers() throws Exception {
		// List<Costumer> costumers = (List<Costumer>) costumerRepository.findByIsActive(true);
		List<Costumer> costumers = (List<Costumer>) costumerRepository.findByIsActiveOrderByLastNameAsc(true);
		return convertAllCostumersToDto(costumers);
	}

	@Override
	public Costumer findCostumerById(Long id) throws Exception {
		return costumerRepository.findById(id)
				.orElseThrow(() -> 
				new IllegalStateException
				("The user with id " + id + " does not exist"));
	}

	@Override
	public CostumerDto findCostumerByEmail(AuthCostumerDto authCostumer) {
		/*Costumer costumer = costumerRepository.findByEmail(authCostumer.getEmail())
				.orElseThrow(() ->
				new IllegalStateException
				("The email" + authCostumer.getEmail() + " does not exist"));
		if(!costumer.getPassword().equals(authCostumer.getPassword())) {
			throw new IllegalStateException("WRONG PASSWORD");
		}
				
		return convertCostumerToDto(costumer);*/return new CostumerDto();
	}
	
	@Override
	public boolean existsCostumerById(Long id) {
		return costumerRepository.existsById(id);
	}

	@Override
	public boolean existsCostumerByEmail(String email) {
		return costumerRepository.existsByEmail(email);
	}

	@Override
	public CostumerDto saveCostumer(Costumer costumer) throws Exception {	
		if (!(costumer.getFirstName().length() < Costumer.FIRST_NAME_MAX_LENGTH))			
			throw new IllegalStateException("Name length is greater than "+ Costumer.FIRST_NAME_MAX_LENGTH);
		else if(!(costumer.getLastName().length() < Costumer.LASTNAME_MAX_LENGTH))			
			throw new IllegalStateException("Lastname length is greater than "+ Costumer.LASTNAME_MAX_LENGTH);
				
		costumer.setIsActive(true);
		return convertCostumerToDto(costumerRepository.save(costumer));		
	}
	
	@Override
	public CostumerDto updateCostumer(Costumer costumer) throws Exception {			
		Costumer costumerInDatabase = findCostumerById(costumer.getCostumerId());		
		costumerInDatabase.setFirstName(costumer.getFirstName());
		costumerInDatabase.setLastName(costumer.getLastName());
		costumerInDatabase.setAvatar(costumer.getAvatar());
		costumerInDatabase.setAddress(costumer.getAddress());
		costumerInDatabase.setIsActive(true);
		return saveCostumer(costumerInDatabase);		
	}
	
	@Override
	public String deleteCostumerById(Long id) throws Exception {
		Costumer costumerInDatabase = findCostumerById(id);
		costumerInDatabase.setIsActive(false);		
		costumerRepository.save(costumerInDatabase);
		return "The costumer has been deleted";						
	}
	
	@Override
	public CostumerDto convertCostumerToDto(Costumer costumer) {
		CostumerDto costumerDto = new CostumerDto();		
		costumerDto.setCostumerId(costumer.getCostumerId());
		costumerDto.setFirstName(costumer.getFirstName());
		costumerDto.setLastName(costumer.getLastName());
		costumerDto.setEmail(costumer.getEmail());
		costumerDto.setAddress(costumer.getAddress());
		costumerDto.setAvatar(costumer.getAvatar());						
		return costumerDto;
	}

	@Override
	public List<CostumerDto> convertAllCostumersToDto(List<Costumer> costumers) {
		List<CostumerDto> costumersDto = new ArrayList<CostumerDto>(); 
		for (Costumer costumer: costumers ){
			costumersDto.add( convertCostumerToDto(costumer));
		}
		return costumersDto;
	}

}
