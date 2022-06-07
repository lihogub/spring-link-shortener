package ru.lihogub.linkshortener.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.lihogub.linkshortener.dto.RequestDto;
import ru.lihogub.linkshortener.exception.AliasNotFound;

import java.util.List;

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
    void performRequestTest() throws AliasNotFound {
        final String MY_URL = "MY_URL";
        String aliasString = aliasService.generateAlias(MY_URL);

        List<RequestDto> requestList1 = aliasService.findAllRequestsByAlias(aliasString);
        Assertions.assertNotNull(requestList1);

        long requestCount1 = requestList1.size();
        Assertions.assertEquals(0L, requestCount1);

        final String MY_IP = "127.0.0.1";
        aliasService.performRequest(aliasString, MY_IP);

        List<RequestDto> requestList2 = aliasService.findAllRequestsByAlias(aliasString);
        long requestCount2 = requestList2.size();
        Assertions.assertEquals(1L, requestCount2);

        Assertions.assertEquals(MY_IP, requestList2.get(0).getIp());
        Assertions.assertEquals(requestCount1 + 1L, requestCount2);
    }
}