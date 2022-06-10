package ru.lihogub.linkshortener.service;

import org.springframework.stereotype.Service;
import ru.lihogub.linkshortener.exception.AliasNotFoundException;


@Service
public interface AliasService {
    String generateAlias(String url);
    String getUrlByAlias(String aliasString) throws AliasNotFoundException;
}
