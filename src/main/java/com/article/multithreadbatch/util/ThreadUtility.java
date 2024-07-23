package com.article.multithreadbatch.util;


import com.article.multithreadbatch.helper.DemoTransactionThread;
import com.article.multithreadbatch.model.TransactionDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThreadUtility {
    public static int getNumOfThread(int maxNumOfThreads,int listSize){
        int NTHREADS = listSize < maxNumOfThreads ? 1 : listSize / maxNumOfThreads;
        if (NTHREADS > maxNumOfThreads) {
            NTHREADS = maxNumOfThreads;
        }
        return NTHREADS;
    }

    public static int[][] calculateThreadRanges(int NTHREADS,int listSize){
        int limit = listSize / NTHREADS;
        int range[][] = new int[NTHREADS][2];
        int t;
        for (t = 0; t < NTHREADS; t++) {
            range[t][0] = t * limit;
            range[t][1] = (t + 1) * limit;
        }
        range[NTHREADS - 1][1] = listSize;
        return range;
    }
    public static List<TransactionDto> execThreads(DemoTransactionThread[] workers, Map[] maps) throws InterruptedException{
        int t = 0;
        for (t = 0; t < maps.length; t++) {
            workers[t].start();
        }
        for (t = 0; t < maps.length; t++) {
            workers[t].join();
        }
        List<TransactionDto> columns = new ArrayList<>();
        for (t = 0; t < maps.length; t++) {
            for (TransactionDto trx: (ArrayList<TransactionDto>) maps[t].get(0)) {
                columns.add(trx);
            }
        }
        return columns;
    }

}
