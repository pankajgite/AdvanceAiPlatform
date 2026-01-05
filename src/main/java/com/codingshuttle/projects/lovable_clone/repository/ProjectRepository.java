package com.codingshuttle.projects.lovable_clone.repository;

import com.codingshuttle.projects.lovable_clone.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByOwnerId(Long userId);

    @Query("SELECT p FROM Project p " +
            "WHERE p.deletedAt IS NULL " +
            "AND (p.owner.id = :userId OR p.isPublic = true)")
    List<Project> findAllAccessibleProjectsByUser(@Param("userId") Long userId);

    Optional<Project> findByIdAndOwnerId(Long id, Long userId);
}
