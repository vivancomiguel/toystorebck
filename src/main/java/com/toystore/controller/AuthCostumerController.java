package com.toystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.toystore.entity.dto.AuthCostumerDto;
import com.toystore.entity.dto.CostumerDto;
import com.toystore.service.*;

//@RestController
//@RequestMapping("/login")
//@CrossOrigin(origins="*")
public class AuthCostumerController {
	@Autowired
	CostumerService costumerService;
	
	@PostMapping //localhost:8080/login
	@ResponseBody
	public ResponseEntity<?> loginCustomer(@RequestBody AuthCostumerDto authCostumer) {
		try {
			return new ResponseEntity<CostumerDto>(costumerService.findCostumerByEmail(authCostumer), HttpStatus.ACCEPTED);
		}
		catch (IllegalStateException e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
					
	}

}

