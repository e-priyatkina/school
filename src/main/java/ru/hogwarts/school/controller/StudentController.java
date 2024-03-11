package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.service.StudentService;

import javax.websocket.OnOpen;
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
    public ResponseEntity<List> findByAgeBetween(@RequestParam int startAge,
                                                 @RequestParam int endAge) {
        if (startAge > 0 && endAge > 0) {
            return ResponseEntity.ok(studentService.findByAgeBetween(startAge, endAge));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/{id}/faculty")
    @Operation(summary = "Факультет студента")
    public Faculty findFaculty(@PathVariable long id) {
        return studentService.findFaculty(id);
    }

    @GetMapping("/count-all-students")
    @Operation(summary = "Количество всех студентов")
    public Integer countAllStudents() {
        return studentService.countAllStudents();
    }

    @GetMapping("/avg-age")
    @Operation(summary = "Средний возраст студентов")
    public Integer avgAgeStudents() {
        return studentService.avgAgeStudents();
    }

    @GetMapping("/last-five-students")
    @Operation(summary = "Пять последних студентов")
    public List<Student> lastFiveStudents() {
        return studentService.lastFiveStudents();
    }
}
