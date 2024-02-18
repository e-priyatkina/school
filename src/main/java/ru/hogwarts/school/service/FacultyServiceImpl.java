package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyServiceImpl implements FacultyService {
    
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
    }

    @Override
    public Faculty updateFaculty(long id, Faculty faculty) {
        if (facultyRepository.findById(id).isEmpty()) {
            throw new FacultyNotFoundException(id);
        }
        Faculty old = facultyRepository.findById(id).get();
        old.setName(faculty.getName());
        old.setColor(faculty.getColor());
        return old;
    }

    @Override
    public void deleteFaculty(long id) {
        if (!facultyRepository.existsById(id)) {
            throw new FacultyNotFoundException(id);
        }
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    @Override
    public Faculty findByNameOrColor(String name, String color) {
        return facultyRepository.findByNameOrColorIgnoreCase(name, color);
    }
}

