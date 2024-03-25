--Составить первый JOIN-запрос, чтобы получить информацию обо всех студентах (достаточно получить только имя и возраст студента)
--школы Хогвартс вместе с названиями факультетов.
SELECT students.name, students.age, faculties.name
FROM students
INNER JOIN faculties ON students.faculty_id = faculties.id


--Составить второй JOIN-запрос, чтобы получить только тех студентов, у которых есть аватарки.
SELECT students.name, students.age, avatars.id, avatars.student_id
FROM students
INNER JOIN avatars ON students.id = avatars.student_id