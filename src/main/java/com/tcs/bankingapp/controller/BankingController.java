package com.tcs.bankingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.bankingapp.controller.dto.BankAccountDto;
import com.tcs.bankingapp.service.BankingService;

@RestController
public class BankingController {
	
	private BankingService bankingService;
	
	public BankingController(BankingService bankingService) {
		this.bankingService = bankingService;
	}

	@GetMapping("/getping")
	public ResponseEntity<String> getPing(){
		return ResponseEntity.ok("Success");
	}
	
	@PostMapping("/create-account")
	public ResponseEntity<BankAccountDto> createBankAccount(@RequestBody BankAccountDto accountDto){
		return new ResponseEntity<BankAccountDto>(bankingService.createBankAccount(accountDto),HttpStatus.CREATED);
	}
}
