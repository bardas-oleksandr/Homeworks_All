package ua.levelup.autowired;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.levelup.fieldstypes.AddressByName;
import ua.levelup.fieldstypes.EducationByName;

@Getter
@Setter
@NoArgsConstructor
public class PersonByName {
    private AddressByName addressByName;
    private EducationByName educationByName;

    public PersonByName(AddressByName addressByName) {
        this.addressByName = addressByName;
    }

    public PersonByName(AddressByName addressByName, EducationByName educationByName) {
        this.addressByName = addressByName;
        this.educationByName = educationByName;
    }
}
