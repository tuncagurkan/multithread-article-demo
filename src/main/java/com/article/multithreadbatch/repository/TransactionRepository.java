package com.article.multithreadbatch.repository;

import com.article.multithreadbatch.model.entity.Transaction;
import com.article.multithreadbatch.model.enums.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends CrudRepository<Transaction, UUID> {

    List<Transaction> findAllByStatus(Status status);
}
