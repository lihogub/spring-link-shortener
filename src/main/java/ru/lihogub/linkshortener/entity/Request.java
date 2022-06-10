package ru.lihogub.linkshortener.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "requests", indexes = {@Index(columnList = "alias_id")})
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Alias alias;
    @Embedded
    private RequestDetails details;
}
