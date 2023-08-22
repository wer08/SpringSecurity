package com.wojtek.restapi.service;

import com.wojtek.restapi.dao.request.BlacklistTokenRequest;
import com.wojtek.restapi.model.BlacklistToken;

public interface BlacklistTokenService {
    void deleteAll();
    BlacklistToken addToken(BlacklistToken token);

    boolean checkToken(String token);
}
