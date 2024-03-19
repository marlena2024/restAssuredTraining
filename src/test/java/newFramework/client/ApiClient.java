package newFramework.client;

import io.restassured.builder.RequestSpecBuilder;
import newFramework.request.GetMockedStudent;
import newFramework.request.GetStudent;
import newFramework.request.PostMockedStudent;
import newFramework.request.PostStudent;
import model.Student;

import java.util.function.Supplier;

public class ApiClient { // stąd wyciągamy poszczególne met do wykonywania requestów

    private final Supplier<RequestSpecBuilder> requestSpecBuilderSupplier;

    public ApiClient(Supplier<RequestSpecBuilder> requestSpecBuilderSupplier) {
        this.requestSpecBuilderSupplier = requestSpecBuilderSupplier;
    }

    public GetStudent getRealStudent(String studentId) {
        return new GetStudent(studentId, requestSpecBuilderSupplier.get());
    }

    public PostStudent postRealStudent(Student student) {
        return new PostStudent(student, requestSpecBuilderSupplier.get());
    }

    public GetMockedStudent getMockedStudent(String studentName) {
        return new GetMockedStudent(studentName, requestSpecBuilderSupplier.get());
    }

    public PostMockedStudent postMockedStudent(String studentName) {
        return new PostMockedStudent(studentName, requestSpecBuilderSupplier.get());
    }

}
