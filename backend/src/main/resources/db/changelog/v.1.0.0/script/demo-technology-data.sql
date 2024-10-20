INSERT INTO technology (id, name, moved, level, type, is_active)
VALUES
    (nextval('technology_id_seq'), 'Java', 'NOT_MOVED', 'ADOPT', 'LANGUAGES', true),
    (nextval('technology_id_seq'), 'JavaScript', 'UP', 'TRIAL', 'LANGUAGES', true),
    (nextval('technology_id_seq'), 'PostgreSQL', 'UP', 'ADOPT', 'DATABASES', true),
    (nextval('technology_id_seq'), 'Docker', 'NOT_MOVED', 'ASSESS', 'PLATFORMS', true),
    (nextval('technology_id_seq'), 'AWS', 'UP', 'TRIAL', 'PLATFORMS', false),
    (nextval('technology_id_seq'), 'Kubernetes', 'UP', 'ASSESS', 'PLATFORMS', true),
    (nextval('technology_id_seq'), 'MongoDB', 'DOWN', 'HOLD', 'DATABASES', true),
    (nextval('technology_id_seq'), 'Spring Boot', 'NOT_MOVED', 'ADOPT', 'LANGUAGES', true),
    (nextval('technology_id_seq'), 'React', 'UP', 'TRIAL', 'LANGUAGES', true),
    (nextval('technology_id_seq'), 'REST', 'NOT_MOVED', 'ASSESS', 'TOOLS', true);
