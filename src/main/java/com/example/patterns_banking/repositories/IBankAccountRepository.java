package com.example.patterns_banking.repositories;

import com.example.patterns_banking.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBankAccountRepository extends JpaRepository<BankAccount, Long> {
}
