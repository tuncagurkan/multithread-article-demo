package com.article.multithreadbatch.service;

import com.article.multithreadbatch.helper.DemoTransactionThread;
import com.article.multithreadbatch.helper.FileWriterHelper;
import com.article.multithreadbatch.helper.PrepareDataHelper;
import com.article.multithreadbatch.model.TransactionDto;
import com.article.multithreadbatch.model.entity.Transaction;
import com.article.multithreadbatch.model.enums.Status;
import com.article.multithreadbatch.repository.TransactionRepository;
import com.article.multithreadbatch.util.CsvFileGenerateUtility;
import com.article.multithreadbatch.util.ThreadUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DemoService {

    private final TransactionRepository transactionRepository;
    private final PrepareDataHelper prepareDataHelper;
    private final CsvFileGenerateUtility csvFileGenerateUtility;

    public void executeDemoBatch() {
        String fileName = FileWriterHelper.getFileName();
        FileWriterHelper.deleteFileIfExist(fileName);
        List<Transaction> transactionList = transactionRepository.findAllByStatus(Status.SUCCESS);

        int NTHREADS = ThreadUtility.getNumOfThread(5, transactionList.size());
        int range[][] = ThreadUtility.calculateThreadRanges(NTHREADS, transactionList.size());

        DemoTransactionThread[] workers = new DemoTransactionThread[NTHREADS];
        HashMap[] maps = new HashMap[NTHREADS];

        for (int t = 0; t < NTHREADS; t++) {
            maps[t] = new HashMap();
            workers[t] = new DemoTransactionThread(prepareDataHelper, transactionList, range[t][0], range[t][1], maps[t], t);

        }
        try {
            List<TransactionDto> records  = ThreadUtility.execThreads(workers, maps);
            csvFileGenerateUtility.generateCsv(records, TransactionDto.class, fileName);
        } catch (InterruptedException e) {
            log.error("Error is occurred during prepare data.", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("Error is occurred during write csv data.", e);
            throw new RuntimeException(e);
        }
    }

}
