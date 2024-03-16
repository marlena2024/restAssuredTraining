package specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.CoreMatchers.is;

public class WeatherResponse {
    public static ResponseSpecification getWeatherResponseSpec(String name, String country, int id) {
        return new ResponseSpecBuilder()
                .expectBody("name", is(name))
                .expectBody("sys.country", is(country))
                .expectBody("id", is(id))
                .expectStatusCode(200)
                .build();
    }
}
