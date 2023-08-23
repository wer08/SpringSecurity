package com.wojtek.restapi.repository;

import com.wojtek.restapi.model.Post;
import com.wojtek.restapi.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    @Query("Select u from User u")
    List<User> findAllUsers(Pageable page);

    void deleteByEmail(String email);
}
