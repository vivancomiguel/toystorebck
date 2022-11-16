package com.toystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.toystore.entity.*;
import com.toystore.entity.dto.CostumerDto;
import com.toystore.service.*;

@RestController
@RequestMapping("/api/costumer")
@CrossOrigin(origins="*")
public class CostumerController {
	@Autowired
	CostumerService costumerService;
	
	@GetMapping("/") //localhost:8080/api/costumer/
	@ResponseBody 
	//ResponseEntity configura la respuesta http
	public  ResponseEntity<?> getAllCostumers () {
		try {
			return new ResponseEntity<Iterable<CostumerDto>>
			(costumerService.findAllCostumers(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("{id}") //localhost:8080/api/costumer/id
	@ResponseBody
	public ResponseEntity<?> getCostumerById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<CostumerDto>( 
					costumerService.convertCostumerToDto(
							costumerService.findCostumerById(id)
							),
					HttpStatus.OK);
		}
		catch (IllegalStateException e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping //localhost:8080/api/costumer
	@ResponseBody
	public ResponseEntity<?> addNewCostumer(@RequestBody Costumer costumer) {
		try {
			//Se guarda el cliente y lo retorna con el id asignado.
			return new ResponseEntity<CostumerDto>(costumerService.saveCostumer(costumer), HttpStatus.CREATED);
		}
		catch (IllegalStateException e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
					
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<?> updateCostumer(@RequestBody Costumer costumer) {	
		try {
			return new ResponseEntity<CostumerDto>(costumerService.updateCostumer(costumer), HttpStatus.OK);
		}
		catch (IllegalStateException e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}			
	}
	
	@DeleteMapping("/{id}") //localhost:8080/api/costumer/id
	@ResponseBody
	public ResponseEntity<?> deleteCostumer(@PathVariable("id") Long id) {
		try {			
			return new ResponseEntity<String>(costumerService.deleteCostumerById(id), HttpStatus.OK);
		}
		catch (IllegalStateException e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}		
	
	}
	
	
	/*{		
	Customer customer = customerRepository.findById(id)
			.orElseThrow(() -> 
			new IllegalStateException
			("The user with id " + id + " does not exist"));
	if (!customer.getIsActive())
		throw new IllegalStateException("The user with id " + id + " does not active");
	return customer;
	}*/
}
