package com.yuri.desafio.repository;

import com.yuri.desafio.model.Task;
import com.yuri.desafio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
