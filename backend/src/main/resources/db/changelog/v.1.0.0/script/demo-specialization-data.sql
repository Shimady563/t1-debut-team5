-- Администратор системы - специальная роль для администраторов и не подразумевается для выбора
INSERT INTO specialization (id, name)
VALUES (nextval('specialization_id_seq'), 'Администратор системы'),
       (nextval('specialization_id_seq'), 'Backend-разработчик'),
       (nextval('specialization_id_seq'), 'Frontend-разработчик'),
       (nextval('specialization_id_seq'), 'Data Scientist'),
       (nextval('specialization_id_seq'), 'Мобильный разработчик'),
       (nextval('specialization_id_seq'), 'DevOps-инженер'),
       (nextval('specialization_id_seq'), 'QA-инженер'),
       (nextval('specialization_id_seq'), 'ML-Инженер'),
       (nextval('specialization_id_seq'), 'Системный аналитик');


