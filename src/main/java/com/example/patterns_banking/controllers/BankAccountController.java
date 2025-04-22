package com.example.patterns_banking.controllers;

import com.example.patterns_banking.dtos.BankAccountDTO;
import com.example.patterns_banking.models.BankAccount;
import com.example.patterns_banking.services.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank_account")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccountDTO bankAccountDTO) {
        return ResponseEntity.ok(bankAccountService.create(bankAccountDTO));
    }

}
