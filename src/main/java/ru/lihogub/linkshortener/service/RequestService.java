package ru.lihogub.linkshortener.service;

import org.springframework.stereotype.Service;
import ru.lihogub.linkshortener.dto.RequestDto;
import ru.lihogub.linkshortener.entity.RequestDetails;
import ru.lihogub.linkshortener.exception.AliasNotFoundException;

import java.util.List;

@Service
public interface RequestService {

    void performRequest(String alias, RequestDetails details) throws AliasNotFoundException;
    List<RequestDto> findAllByAlias(String alias);
}
