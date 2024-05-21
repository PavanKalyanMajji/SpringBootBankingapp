package com.tcs.bankingapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankingController {
	@GetMapping("/getping")
	public ResponseEntity<String> getPing(){
		return ResponseEntity.ok("Success");
	}
}
