package com.tcs.bankingapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tcs.bankingapp.controller.dto.BankAccountDto;
import com.tcs.bankingapp.model.BankAccount;
import com.tcs.bankingapp.repo.BankingRepo;
import com.tcs.bankingapp.util.CommonImpl;

@Service
@Transactional
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

	@Override
	public BankAccountDto getAccountHolder(long accountNumber) {
		return Optional.ofNullable(bankingRepo.findByAccountNumber(accountNumber)).map(x -> commonImpl.mapToBankAccountDto(x)).orElseThrow(() -> new RuntimeException());
	}

	@Override
	public List<BankAccountDto> getAllAccountHolders() {
		
		return Optional.ofNullable(bankingRepo.findAll()).map((listOfBankAccounts) -> {
			List<BankAccountDto> listOfAccountHolders =new ArrayList<BankAccountDto>();
			for(BankAccount obj:listOfBankAccounts) {
				listOfAccountHolders.add(commonImpl.mapToBankAccountDto(obj));
			}
			return listOfAccountHolders;	
		}).orElseThrow(() -> new RuntimeException());
	}

	@Override
	public Integer deleteByAccountNumber(long accountNumber) {
		return Optional.ofNullable(bankingRepo.deleteByAccountNumber(accountNumber)).get();
	}
}
