CREATE TABLE cars (
    car_id SERIAL PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price INTEGER CHECK (price > 0)
);

CREATE TABLE person (
    person_id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    age INTEGER CHECK (age > 0),
    car TEXT NOT NULL,
    car_license BOOLEAN,
    car_id SERIAL REFERENCES cars (person_id)
);

SELECT person.name, person.age, person.car, person.car_license, cars.brand, cars.model, cars.price
FROM person
INNER JOIN cars ON person.car_id = cars.car_id
