package ru.lihogub.linkshortener.service.impl;

import org.springframework.stereotype.Service;
import ru.lihogub.linkshortener.service.RandomStringService;

import java.util.Random;

@Service
class RandomStringServiceImpl implements RandomStringService {
    private final String abc = "abcdefghijklmnopqrstuvwxyz";
    private final String ABC = abc.toUpperCase();
    private final String digits = "0123456789";
    private final String[] src = {abc, ABC, digits};
    private final Random random = new Random();

    @Override
    public String generate(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String buildFrom = src[random.nextInt(3)];
            sb.append(buildFrom.charAt(random.nextInt(buildFrom.length())));
        }
        return sb.toString();
    }
}
