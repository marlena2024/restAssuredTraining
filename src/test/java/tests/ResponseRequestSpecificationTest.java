package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

public class ResponseRequestSpecificationTest {

    @Test
    public void shouldGetWeatherForLocationWithRequestSpecification() {
// zbudowanie obiektu request specification
//        RequestSpecification requestSpecification =
//                given()
//                        .param("appid", "89a2ed8a594cc497a6273490e7ca59dd") // z dokumentajcji https://openweathermap.org/appid
//                        .param("q", "London,uk")
//                        .log().all();

//        ResponseSpecification responseSpecification = RestAssured.expect();
//        responseSpecification.contentType(ContentType.JSON);
//        responseSpecification.statusCode(200);
//        responseSpecification.body("wind.speed", greaterThan(3.0f));
//        responseSpecification.body("sys", hasEntry("country", "GB")) ;


// test z użyciem request spec
//        given().spec(getRequestSpec()) //spec wstrzykuje obiekt
//                .when()
//                .get("https://samples.openweathermap.org/data/2.5/weather")
//                .then()
//                .statusCode(200)
//                .log().all()
//                //    .body("wind.speed" , greaterThan(3.0f), "name" , equalTo("London") //is(4.1f) // asercja twarda tz. jak się wywali na pierwszym to nie spr kolejnego
//                .body("wind.speed", greaterThan(3.0f))
//                .body("name", equalTo("London"))
//                .body("sys", hasEntry("country", "GB")) // spr czy istnieje
//                .body("sys", not(empty()));

        given().spec(getRequestSpec()) //spec wstrzykuje obiekt
                .when()
                .get("https://samples.openweathermap.org/data/2.5/weather")
                .then()
                .log().all()
                .spec(getResponseSpec());
    }

    private RequestSpecification getRequestSpec() {
        return given()
                .param("appid", "89a2ed8a594cc497a6273490e7ca59dd") // z dokumentajcji https://openweathermap.org/appid
                .param("q", "London,uk")
                .log().all();
    }

    private ResponseSpecification getResponseSpec()
    {
        ResponseSpecification responseSpecification = RestAssured.expect();
        responseSpecification.contentType(ContentType.JSON);
        responseSpecification.statusCode(200);
        responseSpecification.body("wind.speed", greaterThan(3.0f));
        responseSpecification.body("sys", hasEntry("country", "GB")) ;

        return responseSpecification;
    }

    @Test
    public void shouldGetWeatherForLocationWithRequestSpecification2() {
// test z użyciem request spec
        given().spec(getRequestSpec()) //spec wstrzykuje obiekt
                .when()
                .get("https://samples.openweathermap.org/data/2.5/weather")
                .then()
                .statusCode(200)
                .log().all()
                //    .body("wind.speed" , greaterThan(3.0f), "name" , equalTo("London") //is(4.1f) // asercja twarda tz. jak się wywali na pierwszym to nie spr kolejnego
                .body("wind.speed", greaterThan(3.0f))
                .body("name", equalTo("London"))
                .body("sys", hasEntry("country", "GB")) // spr czy istnieje
                .body("sys", not(empty()));
    }

}
