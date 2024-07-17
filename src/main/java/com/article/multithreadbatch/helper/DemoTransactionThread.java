package com.article.multithreadbatch.helper;

import com.article.multithreadbatch.model.TransactionDto;
import com.article.multithreadbatch.model.entity.Transaction;

import java.util.HashMap;
import java.util.List;

public class DemoTransactionThread extends Thread {
    private int begin;
    private int end;
    private HashMap maps;
    PrepareDataHelper prepareDataHelper;
    List<Transaction> transactionList;
    private int t;

    public DemoTransactionThread(PrepareDataHelper prepareDataHelper, List<Transaction> transactionList, int begin, int end, HashMap maps, int t) {
        this.begin = begin;
        this.end = end;
        this.maps = maps;
        this.prepareDataHelper = prepareDataHelper;
        this.transactionList = transactionList;
        this.t = t;
    }

    @Override
    public void run() {
        List<TransactionDto> transactionDataList = prepareDataHelper.prepareTransactionData(transactionList, begin, end);
        maps.put(0, transactionDataList);
    }
}
