package tests;

import dataStorge.DataStore;
import dataStorge.StudentResponse;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.example.Student;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
//import org.junit.Test;

public class StudentDetailsTest {
    Student student;
    String requestBody = """
                    {
                        "first_name": "Angelina",
                        "middle_name": "Jolie",
                        "last_name": "Camila",
                        "date_of_birth": "01/03/1977"
                    }
            """;

    // 1
    @Test
    public void shouldPostNewStudent() {
        given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(requestBody)
                .post()
                .then()
                .statusCode(201)
                .log()
                .all();
    }


    // 5
    @Test
    public void shouldPostNewStudentAsObject() {
        // student = new Student();
        // lub specjalna klasa do tworzenia obiektów
        student = new Student("Salma", "Joanna", "Hayek", "10/01/1999");

        // builder - wzorzec projektowy
        Student student2 = Student.builder()
                .date_of_birth("01/01/2000")
                .first_name("Camilla")
                .middle_name("Seniorita")
                .last_name("Bla")
                .build();

        given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .header("Age", 30)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(student)
                .post()
                .then()
                .statusCode(201)
                .log()
                .all()
        ;
    }

    // 2
    @Test
    public void shouldGetNewStudent() {
        given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails/10093355")
                .contentType(ContentType.JSON)
                .log()
                .all()
                .get()
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    // 3
    @Test
    public void shouldGetNewStudentWithPathParam() {
        given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .pathParam("studentId", "10093440")
                .contentType(ContentType.JSON)
                .log()
                .all()
                .get("/{studentId}")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    // 4
    @Test
    public void shouldDeleteNewStudent() {
        String studentId = "10093355";
        given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .pathParam("studentId", studentId)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .delete("/{studentId}")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    // 6
    @Test
    public void shouldPostNewStudent2() {
        student = new Student("Salma", "Joanna", "Hayek", "10/01/1999");
        DataStore.StudentId = given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(student)
                .post()
                .then()
                .statusCode(201)
                .log()
                .all()
                .extract()
                .jsonPath()
                .get("id");

        given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .pathParam("studentId", DataStore.StudentId)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .get("/{studentId}")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    // 7
    @Test
    public void shouldGetNewStudentAndExtractAsTypeRef() {
        StudentResponse actualStudent = given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .pathParam("studentId", "10093440")
                .contentType(ContentType.JSON)
                .log()
                .all()
                .get("/{studentId}")
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract()
                .as(new TypeRef<StudentResponse>() {
                });
//              .as(StudentResponse.class); // to też zadziała

        System.out.println("Extracted student :" + actualStudent);
    }
}
