ALTER TABLE students
ADD CONSTRAINT age_constraint CHECK (age > 16);

ALTER TABLE students
ADD PRIMARY KEY (name);

ALTER TABLE faculties
ADD CONSTRAINT name_color_unique UNIQUE (name, color);

CREATE TABLE students (
    age INTEGER DEFAULT 20,
);