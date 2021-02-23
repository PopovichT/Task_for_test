package com.example.test_task.service;

import com.example.test_task.entity.TransactionAccount;
import com.example.test_task.repository.TransactionAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PaymentService {

    private static final BigDecimal paymentAmount = new BigDecimal ("1.1");
    private final TransactionAccountRepository transactionAccountRepository;


    @Autowired
    public PaymentService(TransactionAccountRepository transactionAccountRepository){
        this.transactionAccountRepository = transactionAccountRepository;
    }

    public void pay (Long accountId) {
        log.info("Trying to do paymet from account {}", accountId);
        doPay(accountId);
        log.info("Done with payment from account {}", accountId);
    }

    private void doPay(Long accountId){
        var taOpt = transactionAccountRepository.findById(accountId);

    }
}
