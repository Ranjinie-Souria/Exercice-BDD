package com.exo1.exo1.repository;

import com.exo1.exo1.entity.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProjetRepository extends JpaRepository<Projet, Long> {

    @Query("SELECT DISTINCT p FROM Projet p " +
            "LEFT JOIN FETCH p.users u " +
            "LEFT JOIN FETCH p.tasks t")
    Page<Projet> findAllProjets(Pageable pageable);

    @Query("SELECT p FROM Projet p " +
            "LEFT JOIN FETCH p.users u " +
            "LEFT JOIN FETCH p.tasks t " +
            "WHERE p.id = :id"
    )
    Optional<Projet> findProjetById(@Param("id") Long id);
}
