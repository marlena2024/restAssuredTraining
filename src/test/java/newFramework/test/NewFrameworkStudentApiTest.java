package newFramework.test;

import dto.StudentDto;
import newFramework.base.NewFrameworkTestBase;
import newFramework.client.ApiClient;
import newFramework.factories.StudentFactory;
import org.apache.http.HttpStatus;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NewFrameworkStudentApiTest extends NewFrameworkTestBase {
    private final String baseUrl = "https://thetestingworldapi.com";
    private ApiClient api;
    private StudentFactory studentFactory;

    @BeforeEach
    public void setUpClient() {
        api = createApiClient(baseUrl);
        studentFactory = new StudentFactory();
    }

    @Test
    public void shouldGetStudent() {
        //10093440
        assertThat(api.getRealStudent("10093440")
                .execute()
                .getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    public void shouldCreateNewStudent() {
        //        assertThat(api.postRealStudent(studentFactory.createRegularStudent())
        //                .execute()
        //                .getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);

        // fabryka studentów
        Student reqestedStudent = studentFactory.createRegularStudent();
        Student customStudent = studentFactory.createCustomizeRegularStudent("Darek", "Dawid", "Kowalski", "01/01/2000");
        StudentDto actualStudent = api.postRealStudent(reqestedStudent).saveStudentAsDto(); // to co przyszło z response

        assertThat(actualStudent).usingRecursiveComparison()
                .ignoringFieldsOfTypes(Integer.class) // ignorowanie id studenta
                .isEqualTo(reqestedStudent);

        assertThat(actualStudent).usingRecursiveComparison()
                .ignoringFieldsOfTypes(Integer.class) // ignorowanie id studenta
                .isEqualTo(customStudent);

//        assertThat(actualStudent.getId()).isNotZero();
//        assertThat(actualStudent.getMiddle_name().length()).isNotZero();
    }

}
