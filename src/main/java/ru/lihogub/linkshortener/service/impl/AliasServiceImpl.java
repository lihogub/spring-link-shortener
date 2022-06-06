package ru.lihogub.linkshortener.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lihogub.linkshortener.entity.Alias;
import ru.lihogub.linkshortener.exception.AliasNotFound;
import ru.lihogub.linkshortener.repository.AliasRepository;
import ru.lihogub.linkshortener.service.AliasService;
import ru.lihogub.linkshortener.service.RandomStringService;


@Service
class AliasServiceImpl implements AliasService {
    @Autowired
    private AliasRepository aliasRepository;

    @Autowired
    private RandomStringService randomStringService;

    @Override
    @Transactional
    public String generateAlias(String url) {
        String uniqueAliasString;
        do {
            uniqueAliasString = randomStringService.generate(10);
        } while (aliasRepository.existsByAlias(uniqueAliasString));

        Alias alias = new Alias();
        alias.setUrl(url);
        alias.setAlias(uniqueAliasString);
        alias.setCounter(0L);

        Alias savedAlias = aliasRepository.save(alias);

        return savedAlias.getAlias();
    }

    @Override
    @Transactional
    public String getUrlByAlias(String alias) throws AliasNotFound {
        Alias fetchedAlias = aliasRepository
                .findByAlias(alias)
                .map(alias1 -> {
                    alias1.setCounter(alias1.getCounter() + 1L);
                    return alias1;
                })
                .orElseThrow(AliasNotFound::new);
        Alias savedAlias = aliasRepository.save(fetchedAlias);
        return savedAlias.getUrl();
    }
}
