package ru.lihogub.linkshortener.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lihogub.linkshortener.dto.AliasDto;
import ru.lihogub.linkshortener.dto.UrlDto;
import ru.lihogub.linkshortener.service.AliasService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AliasController {
    private final AliasService aliasService;

    @PostMapping("/alias")
    AliasDto getAlias(@RequestBody UrlDto urlSto) {
        String aliasString = aliasService.generateAlias(urlSto.getUrl());
        AliasDto aliasDto = new AliasDto();
        aliasDto.setAlias(aliasString);
        return aliasDto;
    }
}
