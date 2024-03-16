package tests;

import base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import specifications.WeatherRequest;
import specifications.WeatherResponse;

import static io.restassured.RestAssured.given;

public class WeatherAdvanceTest extends BaseTest {
    // test sparametryzowany
    @ParameterizedTest
    @CsvFileSource(resources = "/cities.csv")
    @Tag("regression")
    @Tag("SC-123")
    @DisplayName("check response with weather for selected city")
    public void shouldGetWeatherByCity(String city, String country, int id) {
        given().spec(WeatherRequest.getWeatherRequestSpec(id))
                .when().get()
                .then().spec(WeatherResponse.getWeatherResponseSpec(city, country, id));
    }
}