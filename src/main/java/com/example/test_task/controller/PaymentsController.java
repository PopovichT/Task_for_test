package com.example.test_task.controller;

import com.example.test_task.repository.TransactionAccountRepository;
import com.example.test_task.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transaction;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentsController {

    private final PaymentService paymentService;
    private final TransactionAccountRepository transactionAccountRepository;

    @Autowired
    public PaymentsController(PaymentService paymentService, TransactionAccountRepository repository) {
        this.paymentService = paymentService;
        this.transactionAccountRepository = repository;
    }

    @PostMapping("/pay")
    public void pay(@RequestParam("account_id") Long accountId){
        log.info("Received request to do payment for {}", accountId);
        paymentService.pay(accountId);
    }
    @GetMapping("/get_log")
    public Set<Transaction> getTransactionByAccount(@RequestParam("transactionAccount_id") String transactionAccountId){
        return null;
    }
}

