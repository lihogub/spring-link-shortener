package ru.lihogub.linkshortener.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RequestDetails {
    @Column(name = "date")
    private Date date;
    @Column(name = "ip")
    private String ip;
}
