package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Tag(name = "Студенты")
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск студента по id")
    public Student getStudentInfo(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @PostMapping
    @Operation(summary = "Создание студента")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование студента")
    public Student editStudent(@PathVariable long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление студента")
    public Student deleteStudent(@PathVariable long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping(params = "age")
    @Operation(summary = "Фильтрация по возрасту")
    public ResponseEntity<List> findByAge(@RequestParam int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping(params = {"startAge", "endAge"})
    @Operation(summary = "Список студентов в возрастном промежутке")
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam int startAge,
                                                                @RequestParam int endAge) {
        if (startAge > 0 && endAge > 0) {
            return ResponseEntity.ok(studentService.findByAgeBetween(startAge, endAge));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/{id}/faculty")
    public Faculty findFaculty(@PathVariable long id) {
        return studentService.findFaculty(id);
    }

    @GetMapping("/count")
    public int getCountOfStudent() {
        return studentService.getCountOfStudent();
    }

    @GetMapping("/average-age")
    public double getAverageAgeOfStudent() {
        return studentService.getAverageOfStudent();
    }

    @GetMapping("/last")
    public List<Student> getLastNStudent(@RequestParam int count) {
        return studentService.getLastNStudent(count);
    }
}
