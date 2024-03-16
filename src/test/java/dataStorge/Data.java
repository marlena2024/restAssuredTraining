package dataStorge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Data {
    private int id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String date_of_birth;
}
