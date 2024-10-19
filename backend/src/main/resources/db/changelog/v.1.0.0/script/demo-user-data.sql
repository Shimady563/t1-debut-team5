-- password for all users is 'pass1234'
INSERT INTO tech_radar_user (id, email, password, role)
VALUES (1, 'example@mail.com', '$2a$10$XBUYbW67T47V0DZk9x1jkO6fUVs7ayYWXp7TVy.RQNBAKjBCi8VaG', 'ADMIN'),
       (2, 'test@mail.com', '$2a$10$XBUYbW67T47V0DZk9x1jkO6fUVs7ayYWXp7TVy.RQNBAKjBCi8VaG', 'ADMIN'),
       (3, 'ex@mail.com', '$2a$10$XBUYbW67T47V0DZk9x1jkO6fUVs7ayYWXp7TVy.RQNBAKjBCi8VaG', 'USER'),
       (4, 'test1@mail.com', '$2a$10$XBUYbW67T47V0DZk9x1jkO6fUVs7ayYWXp7TVy.RQNBAKjBCi8VaG', 'USER');
