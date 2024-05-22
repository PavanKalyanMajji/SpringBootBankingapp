package com.tcs.bankingapp.service;

import java.util.List;

import com.tcs.bankingapp.controller.dto.BankAccountDto;

public interface BankingService {
	 public BankAccountDto createBankAccount(BankAccountDto accountDto);
	 public BankAccountDto getAccountHolder(long accountNumber);
	 public List<BankAccountDto> getAllAccountHolders();
	 public Integer deleteByAccountNumber(long accountNumber);
	 public BankAccountDto creditAmount(long accountNumber,double amount);
	 public BankAccountDto debitAmount(long accountNumber,double amount);
}
