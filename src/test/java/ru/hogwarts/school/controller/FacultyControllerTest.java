package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.entity.Faculty;

import java.util.Collection;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.URI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.RequestEntity.delete;
import static org.springframework.http.RequestEntity.put;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void getFacultyInfoTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/info", String.class))
                .isNotNull();
    }

    @Test
    public void createFacultyTest() {

    }

    @Test
    public void editFacultyTest() throws Exception {

    }

    @Test
    public void deleteFacultyTest() {

    }

    @Test
    public void findByColorTest() throws Exception {

    }

    @Test
    public void findByyNameOrColorTest() throws Exception {

    }
}
