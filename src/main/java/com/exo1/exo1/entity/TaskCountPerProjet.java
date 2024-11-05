package com.exo1.exo1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task_count_per_project")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCountPerProjet {
    @Id
    private Long projetId;
    private String projetName;
    private Long taskCount;
}
