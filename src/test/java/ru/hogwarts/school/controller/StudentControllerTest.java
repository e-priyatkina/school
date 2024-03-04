package ru.hogwarts.school.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private StudentRepository studentRepository;

    List<Student> students = new ArrayList<>();

    @AfterEach
    public void afterEach() {
        studentRepository.deleteAll();
    }

    @BeforeEach
    public void beforeEach() {

        Student student1 = new Student();
        student1.setName("Harry");
        student1.setAge(11);

        Student student2 = new Student();
        student2.setName("Ron");
        student2.setAge(12);

        students.add(student1);
        students.add(student2);

        studentRepository.saveAll(students);
    }

    @Test
    public void createStudentTest() {
        Student student = new Student();
        student.setName("Germiona");
        student.setAge(11);

        ResponseEntity<Student> responseEntity = testRestTemplate.postForEntity
                ("http://localhost/" + port + "/student",
                student,
                Student.class);

        Student created = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(created).isNotNull();
        assertThat(created).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(student);
        assertThat(created.getId()).isNotNull();

        Optional<Student> fromDb = studentRepository.findById(created.getId());

        assertThat(fromDb).isPresent();
        assertThat(fromDb.get())
                .usingRecursiveComparison()
                .isEqualTo(created);
    }

    @Test
    public void findByAgeBetweenTest() {
        int minAge = 11;
        int maxAge = 14;
        List<Student> expected = students.stream()
                .filter(student -> student.getAge() >= minAge && student.getAge() <= maxAge)
                .toList();

        ResponseEntity<List<Student>> responseEntity = testRestTemplate.exchange(
                "http://localhost/" + port + "student?minAge={minAge}&maxAge={maxAge}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                Map.of("minAge", minAge, "maxAge", maxAge)
        );

        List<Student> actual = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual).usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expected);
    }
}
