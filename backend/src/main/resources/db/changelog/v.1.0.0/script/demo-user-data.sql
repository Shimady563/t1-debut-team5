-- Пароль у всех пользоваетелй 'pass1234'
INSERT INTO tech_radar_user (id, email, password, role, specialization_id)
VALUES (1, 'example@mail.com', '$2a$10$XBUYbW67T47V0DZk9x1jkO6fUVs7ayYWXp7TVy.RQNBAKjBCi8VaG', 'ADMIN', 9),
       (2, 'test@mail.com', '$2a$10$XBUYbW67T47V0DZk9x1jkO6fUVs7ayYWXp7TVy.RQNBAKjBCi8VaG', 'ADMIN', 9),
       (3, 'ex@mail.com', '$2a$10$XBUYbW67T47V0DZk9x1jkO6fUVs7ayYWXp7TVy.RQNBAKjBCi8VaG', 'USER', 1),
       (4, 'test1@mail.com', '$2a$10$XBUYbW67T47V0DZk9x1jkO6fUVs7ayYWXp7TVy.RQNBAKjBCi8VaG', 'USER', 2);
