INSERT INTO users (id, name, email, password, created_at, updated_at) VALUES
      (gen_random_uuid(), 'Ana Prado',
            'ana@gmail.com', '$2a$10$zji3PNvHVd.24yd4WwhT2uOhu.1NHhQyxI4GE1Lw3nIbHRX2VdUB2',
            NOW(), NOW()),
      (gen_random_uuid(), 'Fernando Viana',
       'fernando@gmail.com', '$2a$10$zji3PNvHVd.24yd4WwhT2uOhu.1NHhQyxI4GE1Lw3nIbHRX2VdUB2',
       NOW(), NOW()),
      (gen_random_uuid(), 'Luiza Maria Cardoso',
       'luiza@gmail.com', '$2a$10$zji3PNvHVd.24yd4WwhT2uOhu.1NHhQyxI4GE1Lw3nIbHRX2VdUB2',
       NOW(), NOW()),
      (gen_random_uuid(), 'Roberto Luiz Da Costa',
            'roberto@gmail.com', '$2a$10$zji3PNvHVd.24yd4WwhT2uOhu.1NHhQyxI4GE1Lw3nIbHRX2VdUB2',
            NOW(), NOW());

