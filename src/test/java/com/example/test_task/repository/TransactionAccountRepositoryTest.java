package com.example.test_task.repository;

import com.example.test_task.entity.Transaction;
import com.example.test_task.entity.TransactionAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class TransactionAccountRepositoryTest {

    @Autowired
    private TransactionAccountRepository repository;

    @Test
    void testCanSaveAndReadTransactionAccount() {
        var account = getAccount();

        var savedAccount = repository.save(account);

        assertEquals(account.getBalance(),savedAccount.getBalance());
    }

    @Test
    void testCanSaveAndReadAccountTransactionList() {
        var account = getAccount();
        var transaction = getTransaction();
        account.getTransactions().add(transaction);
        account.getTransactions().add(transaction);

        var savedAccountId = repository.save(account).getId();

        var savedAccountOpt = repository.findById(savedAccountId);
        assertTrue(savedAccountOpt.isPresent());

        var savedAccount = savedAccountOpt.get();
        assertEquals(2, savedAccount.getTransactions().size());
    }

    private TransactionAccount getAccount() {
        return TransactionAccount.builder()
                .transactions(new ArrayList<>())
                .balance(BigDecimal.ONE)
                .build();
    }

    private Transaction getTransaction() {
        return Transaction.builder()
                .amount(BigDecimal.ONE)
                .build();
    }

}