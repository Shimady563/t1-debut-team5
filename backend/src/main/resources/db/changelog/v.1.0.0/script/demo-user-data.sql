-- Пароль у всех пользоваетелй 'pass1234'
INSERT INTO tech_radar_user (id, email, password, role, specialization_id)
VALUES (nextval('tech_radar_user_id_seq'), 'example@mail.com', '$2a$10$XBUYbW67T47V0DZk9x1jkO6fUVs7ayYWXp7TVy.RQNBAKjBCi8VaG', 'ROLE_ADMIN', 9),
       (nextval('tech_radar_user_id_seq'), 'test@mail.com', '$2a$10$XBUYbW67T47V0DZk9x1jkO6fUVs7ayYWXp7TVy.RQNBAKjBCi8VaG', 'ROLE_ADMIN', 9),
       (nextval('tech_radar_user_id_seq'), 'ex@mail.com', '$2a$10$XBUYbW67T47V0DZk9x1jkO6fUVs7ayYWXp7TVy.RQNBAKjBCi8VaG', 'ROLE_USER', 1),
       (nextval('tech_radar_user_id_seq'), 'test1@mail.com', '$2a$10$XBUYbW67T47V0DZk9x1jkO6fUVs7ayYWXp7TVy.RQNBAKjBCi8VaG', 'ROLE_USER', 2);
