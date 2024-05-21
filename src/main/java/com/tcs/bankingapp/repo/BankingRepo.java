package com.tcs.bankingapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.bankingapp.model.BankAccount;

@Repository
public interface BankingRepo extends JpaRepository<BankAccount, Long>{

}
