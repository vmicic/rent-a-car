INSERT INTO authorities ("name") VALUES
    ('ROLE_USER'),
    ('ROLE_AGENT'),
    ('ROLE_COMPANY'),
    ('ROLE_ADMINISTRATOR');

INSERT INTO users("date_registered", "email", "first_name", "last_name","last_password_reset_date", "password","enabled") VALUES
    ('2020-01-15', 'pera@gmail.com', 'petar', 'panin', '2012-01-01 18:47:52', '$2y$12$ZSaHrzb6qz.8nsdWgYUfaOugfoLPBIdOKiA9x4sjyVcf.06/F27Ca', 'true'),
    ('2020-01-16', 'admin@gmail.com', 'Admin', 'adminini', '2012-05-01 18:47:52', '$2y$12$ZSaHrzb6qz.8nsdWgYUfaOugfoLPBIdOKiA9x4sjyVcf.06/F27Ca', 'true'),
    ('2020-01-15', 'user@gmail.com', 'User', 'LastName', '2012-01-01 18:47:52', '$2y$12$ZSaHrzb6qz.8nsdWgYUfaOugfoLPBIdOKiA9x4sjyVcf.06/F27Ca', 'true'),
    ('2020-01-15', 'mirko@gmail.com', 'Mirko', 'Mirkovic', '2012-01-01 18:47:52', '$2y$12$ZSaHrzb6qz.8nsdWgYUfaOugfoLPBIdOKiA9x4sjyVcf.06/F27Ca', 'true');

INSERT INTO user_authority("user_id", "authority_id") VALUES
    ('1', '1'),
    ('2', '4'),
    ('3', '1'),
    ('4', '1');

INSERT INTO car_brand(name) VALUES
    ('BMW'),
    ('Audi'),
    ('Mercedes'),
    ('Tesla');

INSERT INTO car_class(name) VALUES
    ('SUV'),
    ('City car'),
    ('Old Timer');



INSERT INTO fuel_type(name) VALUES
    ('Diesel'),
    ('Gasoline'),
    ('Natural Gas');


INSERT INTO transmission_type(name) VALUES
    ('Automatic'),
    ('Manual'),
    ('Semi-automatic');

INSERT INTO car_model(name, car_brand_id) VALUES
    ('X1', '1'),
    ('X2', '1'),
    ('A4', '2');

INSERT INTO car(car_model_id, car_brand_id, fuel_type_id, transmission_type_id, car_class_id, seats_for_kids, user_id) VALUES
    ('1','1','1','1','1', '1','1'),
    ('2','1','1','2','2', '0','1'),
    ('2','1','1','2','2', '2','3');

INSERT INTO pickup_spot(street, city) VALUES
    ('Aerodrom Nikola Tesla', 'Beograd'),
    ('Zeleznicka stanica', 'Beograd'),
    ('Autobuska stanica', 'Novi Sad');


INSERT INTO advertisement(from_date, to_date, car_id, user_id) VALUES
    ('2020-11-10 12:00','2020-12-30 12:00', '1', '1'),
    ('2020-10-17 12:00','2020-12-27 12:00', '2', '1');

INSERT INTO advertisement_pickup_spots(advertisement_id, pickup_spot_id) VALUES
    ('1', '1'),
    ('1', '2'),
    ('2', '1'),
    ('2', '3');

INSERT INTO reservation(from_date, to_date, advertisement_id, state, user_requested_id, user_received_id) VALUES
    ('2020-12-18 12:00','2020-12-23 12:00', '1', 'PAID', '3', '1');


INSERT INTO reservation(from_date, to_date, state, user_requested_id, user_received_id) VALUES
    ('2020-05-20 12:00','2020-05-25 12:00', 'PAID', '3', '1');


INSERT INTO car_reservation(reservation_id, car_id) VALUES
    ('1', '1'),
    ('2', '1');
    
INSERT INTO address(city, country, number, postal_code, state, street) VALUES
	('Novi Sad', 'Serbia', '12', '21000', null, 'Bulevar Oslobodjenja');
	
INSERT INTO company(business_id_number, email, first_name, last_name, name, password, registration_state, address_id) VALUES
	('00012345', 'rent-a-car-agency@gmail.com', 'Marko', 'A', 'Rent-a-car-agency', '1324567', 0, 1);
