package com.tcs.bankingapp.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tcs.bankingapp.controller.dto.BankAccountDto;
import com.tcs.bankingapp.model.BankAccount;

@Component
public class CommonImpl {
	
	private ModelMapper mapper=new ModelMapper();
	
	public BankAccount mapToBankAccount(BankAccountDto bankAccountDto) {
		BankAccount bankAccount=new BankAccount();
		mapper.map(bankAccountDto, bankAccount);
		return bankAccount;
	}
	
	public BankAccountDto mapToBankAccountDto(BankAccount bankAccount) {
		BankAccountDto bankAccountDto=new BankAccountDto();
		mapper.map(bankAccount, bankAccountDto);
		return bankAccountDto;
	}
}
