package com.example.test_task.service;

import com.example.test_task.entity.Transaction;
import com.example.test_task.entity.TransactionAccount;
import com.example.test_task.repository.TransactionAccountRepository;
import com.example.test_task.repository.TransactionRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@Builder
public class PaymentService {

    private static final BigDecimal paymentAmount = new BigDecimal ("1.1");
    private final TransactionAccountRepository transactionAccountRepository;
    private TransactionRepository transactionRepository;


    @Autowired
    public PaymentService(TransactionAccountRepository transactionAccountRepository, TransactionRepository transactionRepository){
        this.transactionAccountRepository = transactionAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void pay (Long accountId) {
        log.info("Trying to do paymet from account {}", accountId);
        doPay(accountId);
        log.info("Done with payment from account {}", accountId);
    }

    private void doPay(Long accountId) {
        var taOpt = transactionAccountRepository.findById(accountId);
        if (taOpt.isEmpty()) {
            log.warn("Transaction account with id {} not found", accountId);
            return;
        }

        var transactionAccount = taOpt.get();
        var balance = transactionAccount.getBalance();
        log.info("Transaction account has balance {}", balance);

        if (balance.compareTo(paymentAmount) < 0) {
            log.error("Balance is smaller than payment amount {}", paymentAmount);
            return;
        }

        var newBalance = balance.subtract(paymentAmount);
        log.info("New balance for account is {}", newBalance);
        transactionAccount.setBalance(newBalance);

        var transaction = createTransaction(transactionAccount,paymentAmount);
        transactionRepository.save(transaction);

        transactionAccountRepository.save(transactionAccount);
    }

    private Transaction createTransaction(TransactionAccount acc, BigDecimal amount) {
        return Transaction.builder()
                .amount(amount)
                .transactionAccount(acc)
                .build();
    }
}

