package com.article.multithreadbatch.helper;

import com.article.multithreadbatch.model.TransactionDto;
import com.article.multithreadbatch.model.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PrepareDataHelper {

    public List<TransactionDto> prepareTransactionData(List<Transaction> transactionList, int begin, int end) {
        List<TransactionDto> data = new ArrayList<>();
        int failedTransactionCount = 0;
        int count = 0;
        for (int i = begin; i < end; i++) {
            if (count % 100 == 0) log.info("completed transaction count:{}", count);
            prepareTransactionRecord(data, failedTransactionCount, transactionList.get(i));
            count++;
        }

        return data;
    }

    private void prepareTransactionRecord(List<TransactionDto> data, int failedTransactionCount, Transaction transaction) {
        var trx = TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .orderId(transaction.getOrderId())
                .createdAt(transaction.getCreatedAt())
                .build();
        data.add(trx);
    }


}
