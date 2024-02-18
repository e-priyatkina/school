package ru.hogwarts.school.service;

import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;

import java.util.List;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);

    Faculty findFaculty(long id);

    Faculty updateFaculty(long id, Faculty faculty);

    void deleteFaculty(long id);

    List<Faculty> findByColor(String color);

    List<Faculty> findByNameOrColor(String name);

    List<Student> findStudents(long id);
}
