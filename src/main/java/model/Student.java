package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor // musi być bo default konstruktor znika
@Builder
public class Student {
    private String first_name;
    private String middle_name;
    private String last_name;
    private String date_of_birth;

// tradycyjnie:
//    public Student() {
//    }
//
//    public Student(String first_name, String middle_name, String last_name, String date_of_birth) {
//        this.first_name = first_name;
//        this.middle_name = middle_name;
//        this.last_name = last_name;
//        this.date_of_birth = date_of_birth;
//    }
//
//    public String getFirst_name() {
//        return first_name;
//    }
//
//    public void setFirst_name(String first_name) {
//        this.first_name = first_name;
//    }
//
//    public String getMiddle_name() {
//        return middle_name;
//    }
//
//    public void setMiddle_name(String middle_name) {
//        this.middle_name = middle_name;
//    }
//
//    public String getLast_name() {
//        return last_name;
//    }
//
//    public void setLast_name(String last_name) {
//        this.last_name = last_name;
//    }
//
//    public String getDate_of_birth() {
//        return date_of_birth;
//    }
//
//    public void setDate_of_birth(String date_of_birth) {
//        this.date_of_birth = date_of_birth;
//    }
}
