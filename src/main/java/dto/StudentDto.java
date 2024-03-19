package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor // musi być bo default konstruktor znika
@Builder
public class StudentDto {
    public int id;
    public String first_name;
    public String middle_name;
    public String last_name;
    public String date_of_birth;
}
