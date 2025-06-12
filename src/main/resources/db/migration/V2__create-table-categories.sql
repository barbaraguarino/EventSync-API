CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE categories (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
);

INSERT INTO categories (id, name) VALUES
    (gen_random_uuid(), 'Música'),
    (gen_random_uuid(), 'Tecnologia'),
    (gen_random_uuid(), 'Esportes'),
    (gen_random_uuid(), 'Arte & Cultura'),
    (gen_random_uuid(), 'Comida & Bebida'),
    (gen_random_uuid(), 'Negócios & Networking'),
    (gen_random_uuid(), 'Saúde & Bem-estar'),
    (gen_random_uuid(), 'Educação'),
    (gen_random_uuid(), 'Games'),
    (gen_random_uuid(), 'Festas & Vida Noturna');