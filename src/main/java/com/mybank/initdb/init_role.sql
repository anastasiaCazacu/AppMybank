-- Creează tabela roles dacă nu există
CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

-- Inserează rolurile doar dacă nu există deja
INSERT INTO roles (name)
SELECT 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ADMIN');

INSERT INTO roles (name)
SELECT 'BANK'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'BANK');

INSERT INTO roles (name)
SELECT 'CLIENT'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'CLIENT');