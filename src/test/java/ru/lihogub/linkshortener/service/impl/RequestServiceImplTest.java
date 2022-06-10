package ru.lihogub.linkshortener.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.lihogub.linkshortener.dto.RequestDto;
import ru.lihogub.linkshortener.entity.RequestDetails;
import ru.lihogub.linkshortener.exception.AliasNotFoundException;
import ru.lihogub.linkshortener.service.AliasService;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class RequestServiceImplTest {
    @Autowired
    private RequestServiceImpl requestService;
    @Autowired
    private AliasService aliasService;

    final String ALIAS = "ALIAS";
    final String URL = "URL";
    final String IP = "IP";
    final Date DATE = new Date();

    final RequestDetails DETAILS = RequestDetails.builder()
            .ip(IP)
            .date(DATE)
            .build();

    @Test
    @Transactional
    void performRequestTestAliasDoesNotExist() {
        Assertions.assertThrows(AliasNotFoundException.class, () -> requestService.performRequest(ALIAS, DETAILS));
    }

    @Test
    @Transactional
    void findAllByAliasTestAliasDoesNotExist() {
        List<RequestDto> requestDtoList = requestService.findAllByAlias(ALIAS);
        Assertions.assertNotNull(requestDtoList);
        Assertions.assertEquals(0, requestDtoList.size());
    }

    @Test
    @Transactional
    void performRequestTestCreateAndPerform() {
        final String generatedAlias = aliasService.generateAlias(URL);
        System.out.println(DETAILS);
        Assertions.assertDoesNotThrow(() -> requestService.performRequest(generatedAlias, DETAILS));
        List<RequestDto> requestDtoList;
        requestDtoList = Assertions.assertDoesNotThrow(() -> requestService.findAllByAlias(generatedAlias));
        Assertions.assertNotNull(requestDtoList);
        System.out.println(requestDtoList);
        Assertions.assertEquals(1, requestDtoList.size());

        Assertions.assertEquals(IP, requestDtoList.get(0).getIp());
        Assertions.assertEquals(DATE.getTime(), requestDtoList.get(0).getTimestamp());
    }
}