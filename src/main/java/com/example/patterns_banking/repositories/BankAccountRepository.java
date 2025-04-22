package com.example.patterns_banking.repositories;

import com.example.patterns_banking.models.BankAccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankAccountRepository {
    private static final String DB_URL = "jdbc:h2:mem:patterns-banking";
    private static final String DB_USER = "sa";
    private static final String DB_PW = "";

    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS bank_account (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "number VARCHAR(255) NOT NULL UNIQUE," +
                    "type VARCHAR(50)," +
                    "balance DECIMAL(19,2)," +
                    "is_active BOOLEAN DEFAULT TRUE" +
                    ")";

    private static final String INSERT_SQL = "INSERT INTO bank_account (number, type, balance, is_active) " +
            "VALUES (?, ?, ?, ?)";

    private static BankAccountRepository instance;

    private BankAccountRepository() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
    }

    public static BankAccountRepository getInstance() {
        if (instance == null) {
            instance = new BankAccountRepository();
        }
        return instance;
    }

    public BankAccount save(BankAccount bankAccount) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, bankAccount.getNumber());
                pstmt.setString(2, bankAccount.getType().name());
                pstmt.setBigDecimal(3, bankAccount.getBalance());
                pstmt.setBoolean(4, bankAccount.getIsActive());

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating account failed, no rows affected.");
                }

                try(ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        bankAccount.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Creating account failed, no ID obtained.");
                    }
                }
            }

            return bankAccount;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving account", e);
        }
    }

}
