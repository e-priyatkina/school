-- liquibase formatted sql

-- changeset ekaterina:1
CREATE INDEX students_name_index ON students (name);

-- changeset ekaterina:2
CREATE INDEX fc_name_color_index ON faculties (name, color);