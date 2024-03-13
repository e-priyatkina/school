package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class FacultyServiceImpl implements FacultyService {
    
    private final FacultyRepository facultyRepository;

    private final StudentRepository studentRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(null);
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
    }

    @Override
    public Faculty updateFaculty(long id, Faculty faculty) {
        return facultyRepository.findById(id)
                .map(oldFaculty -> {
                    oldFaculty.setName(faculty.getName());
                    oldFaculty.setColor(faculty.getColor());
                    return oldFaculty;
                })
                .orElseThrow(() -> new FacultyNotFoundException(id));
    }

    @Override
    public Faculty deleteFaculty(long id) {
        if (!facultyRepository.existsById(id)) {
            throw new FacultyNotFoundException(id);
        }
        Faculty facultyDelete = findFaculty(id);
        facultyRepository.deleteById(id);
        return facultyDelete;
    }

    @Override
    public List<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    @Override
    public List<Faculty> findByNameOrColor(String nameOrColor) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor);
    }

    @Override
    public List<Student> findStudents(long id) {
        Faculty faculty = findFaculty(id);
        return studentRepository.findByFaculty_Id(faculty.getId());
    }
}

