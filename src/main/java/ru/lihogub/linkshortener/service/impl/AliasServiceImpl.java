package ru.lihogub.linkshortener.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lihogub.linkshortener.entity.Alias;
import ru.lihogub.linkshortener.exception.AliasNotFoundException;
import ru.lihogub.linkshortener.repository.AliasRepository;
import ru.lihogub.linkshortener.service.AliasService;
import ru.lihogub.linkshortener.service.RandomStringService;


@Service
@RequiredArgsConstructor
class AliasServiceImpl implements AliasService {
    private final AliasRepository aliasRepository;
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
    public String getUrlByAlias(String aliasString) throws AliasNotFoundException {
        return aliasRepository
                .findById(aliasString)
                .orElseThrow(AliasNotFoundException::new)
                .getUrl();
    }
}
