package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hogwarts.school.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int startAge, int endAge);

    List<Student> findByFaculty_Id(long id);

    @Query(value = "SELECT count(id) FROM students", nativeQuery = true)
    int getCountOfStudents();

    @Query(value = "SELECT avg(age) FROM students", nativeQuery = true)
    double getAverageOfStudent();


    @Query(value = "SELECT * FROM students ORDER BY id DESC LIMIT : count", nativeQuery = true)
    List<Student> getLastNStudent(@Param("count") int count);
}
