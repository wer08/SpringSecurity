package com.wojtek.restapi.controller;

import com.wojtek.restapi.model.BlacklistToken;
import com.wojtek.restapi.service.BlacklistTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/blacklist")
@RequiredArgsConstructor
@Slf4j
public class BlacklistTokenController {
    private final BlacklistTokenService service;
    @PostMapping("/")
    public BlacklistToken addToken(@RequestBody BlacklistToken token){
        return service.addToken(token);
    }
}
