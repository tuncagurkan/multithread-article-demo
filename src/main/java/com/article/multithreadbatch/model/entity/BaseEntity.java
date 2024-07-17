package com.article.multithreadbatch.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity
{
    @Column(name = "\"createdAt\"")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "\"updatedAt\"")
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "\"deletedAt\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

}
