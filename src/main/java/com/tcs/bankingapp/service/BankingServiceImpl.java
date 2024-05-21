package com.tcs.bankingapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tcs.bankingapp.controller.dto.BankAccountDto;
import com.tcs.bankingapp.model.BankAccount;
import com.tcs.bankingapp.repo.BankingRepo;
import com.tcs.bankingapp.util.CommonImpl;

@Service
public class BankingServiceImpl implements BankingService{
	
	private BankingRepo bankingRepo;
	
	private CommonImpl commonImpl;
	
	public BankingServiceImpl(BankingRepo bankingRepo, CommonImpl commonImpl) {
		this.bankingRepo = bankingRepo;
		this.commonImpl = commonImpl;
	}

	@Override
	public BankAccountDto createBankAccount(BankAccountDto accountDto) {
		Optional<BankAccount> optionalBankAccount=Optional.ofNullable(bankingRepo.save(commonImpl.mapToBankAccount(accountDto)));
		return optionalBankAccount.map((x) -> commonImpl.mapToBankAccountDto(x)).orElseThrow(() -> new RuntimeException());
	}

}
