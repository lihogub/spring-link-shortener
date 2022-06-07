package ru.lihogub.linkshortener.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {
    private String ip;
    private long timestamp;
}
