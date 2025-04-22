package com.example.patterns_banking.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String number;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private BigDecimal balance;
    private Boolean isActive = true;

    public BankAccount(Builder builder) {
        this.id = builder.id;
        this.number = builder.number;
        this.type = builder.type;
        this.balance = builder.balance;
    }

    public static BankAccount.Builder builder() {
        return new BankAccount.Builder();
    }

    public static class Builder {
        private Long id;
        private String number;
        private AccountType type;
        private BigDecimal balance;
        private Boolean isActive;

        private Builder() {}

        public BankAccount.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public BankAccount.Builder number(String number) {
            this.number = number;
            return this;
        }

        public BankAccount.Builder type(AccountType type) {
            this.type = type;
            return this;
        }

        public BankAccount.Builder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public BankAccount.Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public BankAccount build() {
            return new BankAccount(this);
        }
    }
}
