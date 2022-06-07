package ru.lihogub.linkshortener.service;

import org.springframework.stereotype.Service;
import ru.lihogub.linkshortener.dto.RequestDto;
import ru.lihogub.linkshortener.exception.AliasNotFound;

import java.util.List;

@Service
public interface AliasService {
    String generateAlias(String url);
    String getUrlByAlias(String aliasString) throws AliasNotFound;
    void performRequest(String aliasString, String ip) throws AliasNotFound;
    List<RequestDto> findAllRequestsByAlias(String aliasString);
}
