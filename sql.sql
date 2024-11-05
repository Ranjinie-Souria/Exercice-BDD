-- Drop tables if they exist to avoid conflicts and dependencies
DROP TABLE IF EXISTS user_project CASCADE;
DROP TABLE IF EXISTS task CASCADE;
DROP TABLE IF EXISTS projet CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Create the User table
CREATE TABLE users (
    users_id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

-- Create the Projet table
CREATE TABLE projet (
    projet_id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    description TEXT
);

-- Create the Task table
CREATE TABLE task (
    task_id SERIAL PRIMARY KEY,
    title VARCHAR(100),
    status VARCHAR(50),
    projet_id BIGINT REFERENCES projet(projet_id),
    users_id BIGINT REFERENCES users(users_id)
);

-- Create the join table for the ManyToMany relationship between User and Project
CREATE TABLE user_project (
    users_id BIGINT REFERENCES users(users_id),
    projet_id BIGINT REFERENCES projet(projet_id),
    PRIMARY KEY (users_id, projet_id)
);

-- Add foreign key constraints for the ManyToOne and OneToOne relationships
ALTER TABLE task
    ADD CONSTRAINT fk_projet
    FOREIGN KEY (projet_id)
    REFERENCES projet(projet_id);

ALTER TABLE task
    ADD CONSTRAINT fk_user
    FOREIGN KEY (users_id)
    REFERENCES users(users_id);

-- Create indexes for performance optimization
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_projet_name ON projet(name);
CREATE INDEX idx_task_users_id ON task(users_id);
CREATE INDEX idx_task_projet_id ON task(projet_id);
CREATE INDEX idx_user_project_users_id ON user_project(users_id);
CREATE INDEX idx_user_project_projet_id ON user_project(projet_id);

CREATE MATERIALIZED VIEW task_count_per_project AS
SELECT
    p.projet_id AS projet_id,
    p.name AS projet_name,
    COUNT(t.task_id) AS task_count
FROM
    projet p
LEFT JOIN
    task t ON t.projet_id = p.projet_id
GROUP BY
    p.projet_id, p.name;
