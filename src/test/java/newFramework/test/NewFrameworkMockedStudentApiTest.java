package newFramework.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import model.MockedStudent;
import newFramework.base.NewFrameworkTestBase;
import newFramework.client.ApiClient;
import newFramework.factories.StudentFactory;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wiremock.org.apache.commons.lang3.RandomStringUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class NewFrameworkMockedStudentApiTest extends NewFrameworkTestBase {
    private final String baseUrl = "https://thetestingworldapi.com";
    private final String basePathMockedGet = "/api/studentsDetails/.*";
    private final String basePathMockedPost = "/api/studentsDetails";
    private ApiClient api;
    private StudentFactory studentFactory;

    @BeforeEach
    public void setUpClient() {
        api = createApiClient(mock.baseUrl());
        studentFactory = new StudentFactory();
        // mockowanie metoda, ścieżka, co zwracane; urlPathMatching(basePathMocked) - path na którym wiremock ma odpowiadać
        mock.stubFor(post(urlPathMatching(basePathMockedGet)).willReturn(aResponse().withStatus(HttpStatus.SC_CREATED)));
    }

    @Test
    public void shouldCreateMockedStudent() throws JsonProcessingException {
        String studentName = RandomStringUtils.random(10, true, false).toLowerCase();
        MockedStudent studentMocked = new MockedStudent(studentName);

        mock.stubFor(get(urlPathMatching(basePathMockedGet)).willReturn(aResponse()
                .withStatus(HttpStatus.SC_OK)
                .withHeader("Content-Type", ContentType.JSON.toString())
                .withBody(writer.writeValueAsString(studentMocked))));

        assertThat(api.getMockedStudent(studentName).execute().getStatusCode()).isEqualTo(200);
    }

    @Test
    public void shouldPostMockedStudent()  {
    }
}
