package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor // musi być bo default konstruktor znika
public class MockedStudent {
    public String first_name;
}
