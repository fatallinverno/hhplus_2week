package io.hhplus.tdd.architecture.tdd.infrastructure.repository.user;

import io.hhplus.tdd.architecture.tdd.domain.entity.Lecture;
import io.hhplus.tdd.architecture.tdd.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT l FROM User l WHERE l.userId = :userId")
    Optional<Lecture> findByUserId(String userId);
}
