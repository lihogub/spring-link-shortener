package ru.lihogub.linkshortener.service;

import org.springframework.stereotype.Service;

@Service
public interface RandomStringService {
    String generate(int length);
}
