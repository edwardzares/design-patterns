package com.example.patterns_banking.services;

import com.example.patterns_banking.dtos.BankAccountDTO;
import com.example.patterns_banking.models.BankAccount;
import com.example.patterns_banking.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    private BankAccountRepository bankAccountRepository;

    public BankAccountService() {
        this.bankAccountRepository = BankAccountRepository.getInstance();
    }

    public BankAccount create(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = BankAccount
                .builder()
                .number(bankAccountDTO.getNumber())
                .type(bankAccountDTO.getType())
                .balance(bankAccountDTO.getBalance())
                .isActive(bankAccountDTO.getIsActive())
                .build();

        return bankAccountRepository.save(bankAccount);
    }
}
