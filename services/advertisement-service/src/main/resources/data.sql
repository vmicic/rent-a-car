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

INSERT INTO image(name, data ,image_id) VALUES
	('Picture1', ' [B@37bba400', '1');

INSERT INTO car(car_model_id, car_brand_id, fuel_type_id, transmission_type_id, car_class_id, image_id) VALUES
    ('1','1','1','1','1','1'),
    ('2','1','1','2','2','1');

INSERT INTO pickup_spot(street, city) VALUES
    ('Aerodrom Nikola Tesla', 'Beograd'),
    ('Autobuska stanica', 'Novi Sad');

INSERT INTO advertisement(from_date, to_date, car_id) VALUES
    ('2020-12-20 12:00','2020-12-30 12:00', '1'),
    ('2020-12-17 12:00','2020-12-27 12:00', '2');

INSERT INTO advertisement_pickup_spots(advertisement_id, pickup_spot_id) VALUES
    ('1', '1'),
    ('1', '2'),
    ('2', '1');


INSERT INTO user(date_registered, email, first_name, last_name) VALUES
    ('2020-01-15', 'pera@gmail.com', 'petar', 'panin'),
    ('2020-01-16', 'admin@gmail.com', 'Admin', 'adminini'),
    ('2020-01-15', 'user@gmail.com', 'User', 'LastName');
