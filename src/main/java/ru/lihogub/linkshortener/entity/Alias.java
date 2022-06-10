package ru.lihogub.linkshortener.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "aliases", indexes = {@Index(unique = true, columnList = "id")})
public class Alias {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "url", nullable = false)
    private String url;

    @CreatedDate
    private Date createdAt;
}
