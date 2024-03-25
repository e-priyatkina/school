package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Student createStudent(Student student) {
        logger.debug("method create was invoked");
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
        logger.debug("update create was invoked with parametrs id ={}, studentName = {}" + id, student.getName());
        return studentRepository.findById(id)
                .map(oldStudent -> {
                    oldStudent.setName(student.getName());
                    oldStudent.setAge(student.getAge());
                    if (student.getFaculty() != null && student.getFaculty().getId() != null) {
                        Faculty faculty = facultyRepository.findById(student.getFaculty().getId())
                                .orElseThrow(() -> new FacultyNotFoundException(student.getFaculty().getId()));
                        oldStudent.setFaculty(faculty);
                    }
                    return oldStudent;
                })
                .orElseThrow(() -> new StudentNotFoundException(id));

    }

    @Override
    public Student deleteStudent(long id) {
        logger.debug("update delete was invoked with parameter id ={}", id);
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
    public int getCountOfStudent() {
        return studentRepository.getCountOfStudents();
    }

    @Override
    public double getAverageOfStudent() {
        return studentRepository.getAverageOfStudent();
    }

    @Override
    public List<Student> getLastNStudent(int count) {
        return studentRepository.getLastNStudent(count);
    }
}
