package com.wojtek.restapi.repository;

import com.wojtek.restapi.model.BlacklistToken;
import com.wojtek.restapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlacklistTokenRepository extends JpaRepository<BlacklistToken, Long> {
    boolean existsByToken(String token);
}
