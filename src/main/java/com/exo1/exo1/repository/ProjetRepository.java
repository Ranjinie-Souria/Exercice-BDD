package com.exo1.exo1.repository;

import com.exo1.exo1.entity.Projet;
import com.exo1.exo1.entity.Task;
import com.exo1.exo1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjetRepository extends JpaRepository<Projet, Long> {

    @Query("SELECT DISTINCT p FROM Projet p " +
            "LEFT JOIN FETCH p.users u " +
            "LEFT JOIN FETCH p.tasks t")
    List<Projet> findAll();

    @Query("SELECT p FROM Projet t " +
            "LEFT JOIN FETCH p.user u " +
            "LEFT JOIN FETCH p.tasks t " +
            "WHERE t.id = :id"
    )
    Optional<Projet> findById(@Param("id") Long id);
}
