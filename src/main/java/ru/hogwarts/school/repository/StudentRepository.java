package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int startAge, int endAge);

    List<Student> findByFaculty_Id(long id);

    @Query(value = "SELECT COUNT(name) FROM students", nativeQuery = true)
    Integer countAllStudents();

    @Query(value = "SELECT AVG(age) as avg FROM students", nativeQuery = true)
    Integer avgAgeStudents();

    @Query(value = "SELECT * FROM students ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> lastFiveStudents();
}
