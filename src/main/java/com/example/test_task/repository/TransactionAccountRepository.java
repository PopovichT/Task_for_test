package com.example.test_task.repository;

import com.example.test_task.entity.TransactionAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionAccountRepository extends CrudRepository<TransactionAccount, Long>{
}
