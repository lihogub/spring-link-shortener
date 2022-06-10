package ru.lihogub.linkshortener.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.lihogub.linkshortener.entity.RequestDetails;
import ru.lihogub.linkshortener.exception.AliasNotFoundException;
import ru.lihogub.linkshortener.service.AliasService;
import ru.lihogub.linkshortener.service.RequestService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s")
public class RedirectController {
    private final AliasService aliasService;
    private final RequestService requestService;

    @GetMapping("/{alias}")
    RedirectView redirect(HttpServletRequest request, @PathVariable String alias) throws AliasNotFoundException {
        RedirectView redirectView = new RedirectView(aliasService.getUrlByAlias(alias));
        RequestDetails requestDetails = new RequestDetails();
        requestDetails.setIp(request.getRemoteAddr());
        requestService.performRequest(alias, requestDetails);
        return redirectView;
    }

    @ExceptionHandler(AliasNotFoundException.class)
    RedirectView handleAliasNotFoundException() {
        return new RedirectView("/");
    }
}
