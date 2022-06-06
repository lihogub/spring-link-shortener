package ru.lihogub.linkshortener.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lihogub.linkshortener.service.RandomStringService;

@SpringBootTest
public class RandomStringServiceImplTest {

    @Autowired
    private RandomStringService randomStringService;

    @Test
    void generateTestNotNull() {
        Assertions.assertNotNull(randomStringService.generate(1));
    }

    @Test
    void generateTestWithGivenLength() {
        Assertions.assertEquals(1, randomStringService.generate(1).length());
        Assertions.assertEquals(10, randomStringService.generate(10).length());
        Assertions.assertEquals(100, randomStringService.generate(100).length());
    }

}
