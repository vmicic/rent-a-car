INSERT INTO authorities (id, name) VALUES
    ('1', 'ROLE_USER'),
    ('2', 'ROLE_AGENT'),
    ('3', 'ROLE_COMPANY'),
    ('4', 'ROLE_ADMINISTRATOR');

INSERT INTO user(id, date_registered, email, first_name, last_name, last_password_reset_date, password) VALUES
    ('1', '2020-01-15', 'pera@gmail.com', 'petar', 'panin', '2012-01-01 18:47:52', '$2y$12$ZSaHrzb6qz.8nsdWgYUfaOugfoLPBIdOKiA9x4sjyVcf.06/F27Ca'),
    ('2', '2020-01-16', 'admin@gmail.com', 'Admin', 'adminini', '2012-05-01 18:47:52', '$2y$12$ZSaHrzb6qz.8nsdWgYUfaOugfoLPBIdOKiA9x4sjyVcf.06/F27Ca');

INSERT INTO user_authority(user_id, authority_id) VALUES
    ('1', '1'),
    ('2', '4');
