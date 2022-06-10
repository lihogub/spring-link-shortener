package ru.lihogub.linkshortener.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.lihogub.linkshortener.exception.AliasNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
class AliasServiceImplTest {
    @Autowired
    private AliasServiceImpl aliasService;

    @Test
    @Transactional
    void generateAliasTestNotNull() {
        Assertions.assertNotNull(aliasService.generateAlias("MY_URL"));
    }

    @Test
    @Transactional
    void getUrlByAliasTestGenerateAliasAndFetchUrl() throws AliasNotFoundException {
        final String MY_URL = "MY_URL";
        String aliasString = aliasService.generateAlias(MY_URL);
        Assertions.assertDoesNotThrow(() -> aliasService.getUrlByAlias(aliasString));
        Assertions.assertEquals(MY_URL, aliasService.getUrlByAlias(aliasString));
    }

    @Test
    @Transactional
    void getUrlByAliasTestThrowsAliasNotFound() {
        Assertions.assertThrows(AliasNotFoundException.class, () -> aliasService.getUrlByAlias("MY_ALIAS"));
    }
}