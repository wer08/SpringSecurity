package com.wojtek.restapi.service.implementation;

import com.wojtek.restapi.dao.request.BlacklistTokenRequest;
import com.wojtek.restapi.model.BlacklistToken;
import com.wojtek.restapi.repository.BlacklistTokenRepository;
import com.wojtek.restapi.service.BlacklistTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlacklistTokenServiceImpl implements BlacklistTokenService {

    private final BlacklistTokenRepository blacklistTokenRepository;
    @Override
    public void deleteAll() {
        blacklistTokenRepository.deleteAll();
    }

    @Override
    public BlacklistToken addToken(BlacklistToken token) {
        return blacklistTokenRepository.save(token);
    }

    @Override
    public boolean checkToken(String token) {
        return blacklistTokenRepository.existsByToken(token);
    }
}
