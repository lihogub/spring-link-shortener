package ru.lihogub.linkshortener.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lihogub.linkshortener.dto.RequestDto;
import ru.lihogub.linkshortener.entity.Alias;
import ru.lihogub.linkshortener.entity.Request;
import ru.lihogub.linkshortener.exception.AliasNotFound;
import ru.lihogub.linkshortener.mapper.RequestMapper;
import ru.lihogub.linkshortener.repository.AliasRepository;
import ru.lihogub.linkshortener.repository.RequestRepository;
import ru.lihogub.linkshortener.service.AliasService;
import ru.lihogub.linkshortener.service.RandomStringService;

import java.util.List;


@Service
@RequiredArgsConstructor
class AliasServiceImpl implements AliasService {
    private final RequestMapper requestMapper;
    private final AliasRepository aliasRepository;
    private final RequestRepository requestRepository;
    private final RandomStringService randomStringService;

    @Override
    @Transactional
    public String generateAlias(String url) {
        String uniqueAliasString;
        do {
            uniqueAliasString = randomStringService.generate(5);
        } while (aliasRepository.existsById(uniqueAliasString));

        Alias alias = new Alias();
        alias.setId(uniqueAliasString);
        alias.setUrl(url);
        Alias savedAlias = aliasRepository.save(alias);

        return savedAlias.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public String getUrlByAlias(String aliasString) throws AliasNotFound {
        return aliasRepository
                .findById(aliasString)
                .orElseThrow(AliasNotFound::new)
                .getUrl();
    }

    @Override
    @Transactional
    public void performRequest(String aliasString, String ip) throws AliasNotFound {
        Alias alias = aliasRepository
                .findById(aliasString)
                .orElseThrow(AliasNotFound::new);

        Request request = new Request();
        request.setAlias(alias);
        request.setIp(ip);
        requestRepository.save(request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RequestDto> findAllRequestsByAlias(String aliasString) {
        return requestMapper.requestListToRequestDtoList(requestRepository.findAllByAlias(aliasString));
    }

}
