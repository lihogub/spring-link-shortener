package ru.lihogub.linkshortener.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lihogub.linkshortener.dto.RequestDto;
import ru.lihogub.linkshortener.service.RequestService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @GetMapping("/requests/{alias}")
    List<RequestDto> findAllByAlias(@PathVariable String alias) {
        return requestService.findAllByAlias(alias);
    }
}
