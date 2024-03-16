package tests;

import dataStorge.DataStore;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

public class WeatherTest {
    // 2
    private Cookies cookie;


    // hook
    @BeforeEach
    public void setup() {
        RestAssured.useRelaxedHTTPSValidation(); // nie bedzie problemów z https
        RestAssured.baseURI = "https://api.openweathermap.org";
        // RestAssured.basePath = "/data/2.5/weather";
        //  RestAssured.port = 8881 // przyda sie gdy korzystamy z mocka lub gdy chcemy dostać sie do contextu testowego (spring)
    }

    // 1
    @Test
    public void shouldGetWeatherForLocation() {
        given()
                .param("appid", "89a2ed8a594cc497a6273490e7ca59dd") // z dokumentajcji https://openweathermap.org/appid
                .param("id", "6695624")
                .log().all()
                .when()
                .get("/data/2.5/forecast")
                .then()
                .statusCode(200) // najpierw 401 = dodanie param z appid; potem 400 =  dodanie param z id
                .log().all();
    }

    // 2
    @Test
    public void shouldGetWeatherWithCookies() {
        cookie = shouldLoginToSystem(); //pobiera cookies
        given()
                .cookies(cookie)
                .param("appid", "89a2ed8a594cc497a6273490e7ca59dd") // z dokumentajcji https://openweathermap.org/appid
                .param("id", "6695624")
                .log().all()
                .when()
                .get("/data/2.5/forecast")
                .then()
                .statusCode(200) // najpierw 401 = dodanie param z appid; potem 400 =  dodanie param z id
                .log().all();
    }

    public Cookies shouldLoginToSystem() {
        String body = "payload";
        return RestAssured
                .given()
                .param("appid", "89a2ed8a594cc497a6273490e7ca59dd") // z dokumentajcji https://openweathermap.org/appid
                .param("id", "6695624")
                .body(body)
                .when()
                .post("/api/bbh")
                .getDetailedCookies();
    }

    // 3
    DataStore dataStore;

    @Test
    public void shouldGetWeatherForLocationWithResponseValidation() {
        DataStore.windDegree = given()
                .param("appid", "89a2ed8a594cc497a6273490e7ca59dd") // z dokumentajcji https://openweathermap.org/appid
                .param("q", "London,uk")
                .log().all()
                .when()
                .get("https://samples.openweathermap.org/data/2.5/weather")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .jsonPath()
                .get("wind.deg");

        System.out.println("Exctracted value for wind degree: " + DataStore.windDegree);

    }

    // 4
    @Test
    public void shouldGetWeatherForLocationWithResponseSpeedValidation() {
        given()
                .param("appid", "89a2ed8a594cc497a6273490e7ca59dd") // z dokumentajcji https://openweathermap.org/appid
                .param("q", "London,uk")
                .log().all()
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
