package com.article.multithreadbatch.model;

import com.article.multithreadbatch.model.enums.Status;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "id")
    private UUID id;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "Islem Tarihi")
    private Date createdAt;

    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "Tutar")
    private BigDecimal amount;

    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "Order Id")
    private String orderId;

    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "Durum")
    private Status status;
}
