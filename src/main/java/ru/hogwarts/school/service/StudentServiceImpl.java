package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final FacultyRepository facultyRepository;

    public StudentServiceImpl(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Student createStudent(Student student) {
        student.setId(null);
        if (student.getFaculty() != null && student.getFaculty().getId() != null) {
            Faculty faculty = facultyRepository.findById(student.getFaculty().getId())
                    .orElseThrow(() -> new FacultyNotFoundException(student.getFaculty().getId()));
            student.setFaculty(faculty);
        }
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Override
    public Student updateStudent(long id, Student student) {
        if (!studentRepository.findById(id).isPresent()) {
            throw new StudentNotFoundException(id);
        }
        return studentRepository.save(student);

    }

    @Override
    public Student deleteStudent(long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        Student studentDelete = findStudent(id);
        studentRepository.deleteById(id);
        return studentDelete;
    }

    @Override
    public List<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    @Override
    public List<Student> findByAgeBetween(int startAge, int endAge) {
        return studentRepository.findByAgeBetween(startAge, endAge);
    }

    @Override
    public Faculty findFaculty(long id) {
        return findStudent(id).getFaculty();
    }

    @Override
    public Integer countAllStudents() {
        return studentRepository.countAllStudents();
    }

    @Override
    public Integer avgAgeStudents() {
        return studentRepository.avgAgeStudents();
    }

    @Override
    public List<Student> lastFiveStudents() {
        return studentRepository.lastFiveStudents();
    }
}
