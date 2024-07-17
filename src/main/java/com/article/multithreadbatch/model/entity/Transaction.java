package com.article.multithreadbatch.model.entity;

import com.article.multithreadbatch.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"transaction\"")
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Column(name = "\"amount\"")
    private BigDecimal amount;
    @Column(name = "\"orderId\"")
    private String orderId;
    @Enumerated(EnumType.STRING)
    private Status status;

}
