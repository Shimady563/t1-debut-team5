INSERT INTO vote (id, level, user_id, technology_id)
VALUES (nextval('vote_id_seq'), 'ASSESS', 3, 3),
       (nextval('vote_id_seq'), 'ADOPT', 3, 4),
       (nextval('vote_id_seq'), 'ASSESS', 3, 5),
       (nextval('vote_id_seq'), 'ADOPT', 3, 6),
       (nextval('vote_id_seq'), 'HOLD', 3, 4),
       (nextval('vote_id_seq'), 'ASSESS', 4, 1),
       (nextval('vote_id_seq'), 'TRIAL', 4, 2),
       (nextval('vote_id_seq'), 'ASSESS', 4, 8),
       (nextval('vote_id_seq'), 'HOLD', 4, 7);