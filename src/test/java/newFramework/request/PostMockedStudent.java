package newFramework.request;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import newFramework.client.ExecutableRequest;

import static io.restassured.RestAssured.given;

public class PostMockedStudent implements ExecutableRequest {
    private final RequestSpecBuilder requestSpecBuilder;

    public PostMockedStudent(String studentName, RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.setContentType(ContentType.JSON);
        this.requestSpecBuilder.setBody(studentName);
    }

    @Override
    public Response execute() {
        return given().spec(requestSpecBuilder.build()).when().post("/api/studentsDetails");
    }
}
