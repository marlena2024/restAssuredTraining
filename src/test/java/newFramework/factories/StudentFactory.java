package newFramework.factories;

import model.MockedStudent;
import model.Student;

public class StudentFactory {

    public Student createRegularStudent() {
        return new Student("Adam", "Jan", "Nowak", "01/01/2000");
    }

    public Student createCustomizeRegularStudent(String firstName, String middleName, String lastName, String dateOfBirth) {
        return new Student(firstName, middleName, lastName, dateOfBirth);
    }

    // randomizacja
    public MockedStudent getRandomStudent() {
        return new MockedStudent("");
    }
}
