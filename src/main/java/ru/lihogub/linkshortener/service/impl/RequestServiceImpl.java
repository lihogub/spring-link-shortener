package ru.lihogub.linkshortener.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.lihogub.linkshortener.dto.RequestDto;
import ru.lihogub.linkshortener.entity.Request;
import ru.lihogub.linkshortener.entity.RequestDetails;
import ru.lihogub.linkshortener.exception.AliasNotFoundException;
import ru.lihogub.linkshortener.mapper.RequestMapper;
import ru.lihogub.linkshortener.repository.RequestRepository;
import ru.lihogub.linkshortener.service.RequestService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    @Override
    public void performRequest(String alias, RequestDetails details) throws AliasNotFoundException {
        try {
            requestRepository.insertByAlias(alias, details);
        } catch (DataIntegrityViolationException e) {
            throw new AliasNotFoundException();
        }
    }

    @Override
    public List<RequestDto> findAllByAlias(String alias) {
        return requestMapper.requestListToRequestDtoList(requestRepository.findAllByAlias(alias));
    }
}
