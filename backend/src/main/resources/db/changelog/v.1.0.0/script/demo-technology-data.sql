INSERT INTO technology (id, name, moved, level, type)
VALUES
    (nextval('technology_id_seq'), 'Java', 'NOT_MOVED', 'ADOPT', 'LANGUAGES'),
    (nextval('technology_id_seq'), 'JavaScript', 'UP', 'TRIAL', 'LANGUAGES'),
    (nextval('technology_id_seq'), 'PostgreSQL', 'UP', 'ADOPT', 'DATABASES'),
    (nextval('technology_id_seq'), 'Docker', 'NOT_MOVED', 'ASSESS', 'PLATFORMS'),
    (nextval('technology_id_seq'), 'AWS', 'UP', 'TRIAL', 'PLATFORMS'),
    (nextval('technology_id_seq'), 'Kubernetes', 'UP', 'ASSESS', 'PLATFORMS'),
    (nextval('technology_id_seq'), 'MongoDB', 'DOWN', 'HOLD', 'DATABASES'),
    (nextval('technology_id_seq'), 'Spring Boot', 'NOT_MOVED', 'ADOPT', 'LANGUAGES'),
    (nextval('technology_id_seq'), 'React', 'UP', 'TRIAL', 'LANGUAGES'),
    (nextval('technology_id_seq'), 'REST', 'NOT_MOVED', 'ASSESS', 'TOOLS');
