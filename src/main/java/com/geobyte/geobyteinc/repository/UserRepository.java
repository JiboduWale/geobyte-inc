package com.geobyte.geobyteinc.repository;

import com.geobyte.geobyteinc.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String username);
}
