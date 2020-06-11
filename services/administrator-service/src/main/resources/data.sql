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
