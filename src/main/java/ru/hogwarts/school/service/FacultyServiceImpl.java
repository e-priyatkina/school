package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.*;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long lastId = 0;

    @Override
    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    @Override
    public boolean findFaculty(long id) {
        return faculties.containsKey(id);
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    @Override
    public Faculty deleteFaculty(long id) {
        return faculties.remove(id);
    }

    @Override
    public Collection<Faculty> getFacultiesByColor(String color) {
        List<Faculty> sortedFaculties = new ArrayList<>();
        faculties.forEach((id, faculty) -> {
            if (faculty.getColor().equals(color)) {
                sortedFaculties.add(faculty);
            }
        });
        return sortedFaculties;
    }
}

