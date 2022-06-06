package ru.lihogub.linkshortener.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.lihogub.linkshortener.entity.Alias;
import ru.lihogub.linkshortener.exception.AliasNotFound;
import ru.lihogub.linkshortener.repository.AliasRepository;


@SpringBootTest
class AliasServiceImplTest {
    @Autowired
    private AliasServiceImpl aliasService;

    @Autowired
    private AliasRepository aliasRepository;

    @Test
    @Transactional
    void generateAliasTestNotNull() {
        Assertions.assertNotNull(aliasService.generateAlias("MY_URL"));
    }

    @Test
    @Transactional
    void getUrlByAliasTestGenerateAliasAndFetchUrl() throws AliasNotFound {
        final String MY_URL = "MY_URL";
        String aliasString = aliasService.generateAlias(MY_URL);
        Assertions.assertDoesNotThrow(() -> aliasService.getUrlByAlias(aliasString));
        Assertions.assertEquals(MY_URL, aliasService.getUrlByAlias(aliasString));
    }

    @Test
    @Transactional
    void getUrlByAliasTestThrowsAliasNotFound() {
        Assertions.assertThrows(AliasNotFound.class, () -> aliasService.getUrlByAlias("MY_ALIAS"));
    }

    @Test
    @Transactional
    void getUrlByAliasTestIncrementCounter() throws AliasNotFound {
        String aliasString = aliasService.generateAlias("MY_URL");

        Alias alias = aliasRepository
                .findByAlias(aliasString)
                .orElseThrow(AliasNotFound::new);

        long aliasCounter1 = alias.getCounter();

        aliasService.getUrlByAlias(aliasString);

        Alias alias2 = aliasRepository
                .findByAlias(aliasString)
                .orElseThrow(AliasNotFound::new);

        long aliasCounter2 = alias2.getCounter();

        Assertions.assertEquals(aliasCounter1 + 1L, aliasCounter2);
    }
}