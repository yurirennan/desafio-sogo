package com.yuri.desafio.repository;

import com.yuri.desafio.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query( value = "SELECT * FROM tasks where tasks.user_id = ?1 ORDER BY status DESC",
            nativeQuery = true)
    List<Task> findAllbyUserIdOrderByPriority(Long userId);

    @Query(value = "SELECT * FROM tasks where tasks.user_id = ?1 and tasks.status='PENDING'",
            nativeQuery = true)
    List<Task> findAllPeding(Long userId);

    Optional<Task> findByTitle(String title);
}
