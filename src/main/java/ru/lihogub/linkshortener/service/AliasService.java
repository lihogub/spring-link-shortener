package ru.lihogub.linkshortener.service;

import org.springframework.stereotype.Service;
import ru.lihogub.linkshortener.exception.AliasNotFound;

@Service
public interface AliasService {
    String generateAlias(String url);
    String getUrlByAlias(String alias) throws AliasNotFound;
}
