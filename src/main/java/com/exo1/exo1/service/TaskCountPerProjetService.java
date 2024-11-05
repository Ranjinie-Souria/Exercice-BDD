package com.exo1.exo1.service;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskCountPerProjetService
{
    public JdbcTemplate jdbcTemplate;

    public void refreshMaterializedView() {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW task_count_per_project");
    }

}
