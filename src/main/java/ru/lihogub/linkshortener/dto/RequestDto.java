package ru.lihogub.linkshortener.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class RequestDto {
    private String ip;
    private long timestamp;
}
