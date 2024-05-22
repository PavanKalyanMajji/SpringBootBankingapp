package com.tcs.bankingapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.config.RuntimeBeanNameReference;
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
		Optional<BankAccount> isAccount=Optional.ofNullable(bankingRepo.findByAccountNumber(accountDto.getAccountNumber()));
		if(isAccount.isPresent()) {
			throw new RuntimeException("Account Already Present");
		}
		return Optional.ofNullable(bankingRepo.save(commonImpl.mapToBankAccount(accountDto)))
												.map((x) -> commonImpl.mapToBankAccountDto(x))
												.orElseThrow(() -> new RuntimeException("Failed To Create By Account"));
	}

	@Override
	public BankAccountDto getAccountHolder(long accountNumber) {
		return Optional.ofNullable(bankingRepo.findByAccountNumber(accountNumber))
				.map(x -> commonImpl.mapToBankAccountDto(x))
				.orElseThrow(() -> new RuntimeException("Account Details Not Found"));
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
		Optional.ofNullable(bankingRepo.findByAccountNumber(accountNumber))
							.orElseThrow(() -> new RuntimeException("AccountHolder Details Not found"));
		return Optional.ofNullable(bankingRepo.deleteByAccountNumber(accountNumber)).get();
	}

	@Override
	public BankAccountDto creditAmount(long accountNumber, double amount) {
		BankAccount accountDetails=Optional.ofNullable(bankingRepo.findByAccountNumber(accountNumber)).orElseThrow(() -> new RuntimeException("AccountHolder Details Not found"));
		double total =accountDetails.getBalance()+amount;
		accountDetails.setBalance(total);
		return Optional.ofNullable(bankingRepo.save(accountDetails)).map((bankAccountDetails) -> commonImpl.mapToBankAccountDto(bankAccountDetails)).orElseThrow(() -> new RuntimeException("Unable able to save acount details"));
	}

	@Override
	public BankAccountDto debitAmount(long accountNumber, double debitAmount) {
		BankAccount accountDetails=Optional.ofNullable(bankingRepo.findByAccountNumber(accountNumber)).orElseThrow(() -> new RuntimeException("AccountHolder Details Not found"));
		double totalAmount=accountDetails.getBalance();
		if(totalAmount<debitAmount) {
			throw new RuntimeException("InsufficientBalance please Check Balance");
		}
		accountDetails.setBalance(totalAmount-debitAmount);
		return Optional.ofNullable(bankingRepo.save(accountDetails)).map((bankAccountDetails) -> commonImpl.mapToBankAccountDto(bankAccountDetails)).orElseThrow(() -> new RuntimeException("Unable able to save acount details"));
	}
}
