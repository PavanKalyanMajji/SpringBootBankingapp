package com.tcs.bankingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.bankingapp.controller.dto.BankAccountDto;
import com.tcs.bankingapp.service.BankingService;

import java.util.*;

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
	
	@GetMapping("/getAccountHolder/{accountNo}")
	public ResponseEntity<BankAccountDto> getAccountHolder(@PathVariable("accountNo") long accountNumber){
		return new ResponseEntity<BankAccountDto>(bankingService.getAccountHolder(accountNumber),HttpStatus.FOUND);
	}
	
	@GetMapping
	public ResponseEntity<List<BankAccountDto>> getAccountHolders(){
		return new ResponseEntity<List<BankAccountDto>>(bankingService.getAllAccountHolders(),HttpStatus.OK);
	}
	
	@DeleteMapping("/{accountNo}")
	public ResponseEntity<Integer> beleteByAccountNumber(@PathVariable("accountNo") long accountNumber){
		return new ResponseEntity<Integer>(bankingService.deleteByAccountNumber(accountNumber),HttpStatus.OK);
	}
	
	@PutMapping("/credit/{accountNo}/{amount}")
	public ResponseEntity<BankAccountDto> creditAmount(@PathVariable("accountNo") long accountNo,@PathVariable("amount") double amount){
		return new ResponseEntity<BankAccountDto>(bankingService.creditAmount(accountNo, amount),HttpStatus.OK);
	}
	
	@PutMapping("/debit/{accountNo}/{amount}")
	public ResponseEntity<BankAccountDto> debitAmount(@PathVariable("accountNo") long accountNo,@PathVariable("amount") double amount){
		return new ResponseEntity<BankAccountDto>(bankingService.debitAmount(accountNo, amount),HttpStatus.OK);
	}
}
