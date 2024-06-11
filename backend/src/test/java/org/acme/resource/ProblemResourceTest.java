package org.acme.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ProblemResourceTest {

    @BeforeEach
    void init() {
        // init mock data to database with testcontainers
    }

    @Test
    void getAllProblems() {
        given().when().get("/problems")
                .then()
                .statusCode(200);
                // validating the body here
                // .body(is("valid body here"));

    }

    @Test
    void getProblemById() {
    }

    @Test
    void createProblem() {

    }

    @Test
    void fillDbWithTestProblems() {
    }
}