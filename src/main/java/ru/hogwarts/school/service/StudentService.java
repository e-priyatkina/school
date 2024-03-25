package ru.hogwarts.school.service;

import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    Student findStudent(long id);

    Student updateStudent(long id, Student student);

    Student deleteStudent(long id);

    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int startAge, int endAge);

    Faculty findFaculty(long id);

    int getCountOfStudent();

    double getAverageOfStudent();

    List<Student> getLastNStudent(int count);
}
