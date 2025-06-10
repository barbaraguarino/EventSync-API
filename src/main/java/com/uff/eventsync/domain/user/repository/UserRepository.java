package com.uff.eventsync.domain.user.repository;

import com.uff.eventsync.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<Object> findByEmail(String email);
    boolean existsByEmail(String email);
}
