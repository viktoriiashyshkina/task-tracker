package com.example.task_tracker.store.repositories;

import com.example.task_tracker.store.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {

    Optional<ProjectEntity> findByName(String name);
    @Query("SELECT p FROM ProjectEntity p")
    Stream<ProjectEntity> streamAll();

    Stream<ProjectEntity> streamAllByNameStartsWithIgnoreCase(String prefixName);
}
