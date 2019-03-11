package ua.levelup.autowired;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.levelup.fieldstypes.AddressByType;
import ua.levelup.fieldstypes.EducationByType;

@Getter
@Setter
@NoArgsConstructor
public class PersonByType {
    private AddressByType addressByType;
    private EducationByType educationByType;

    public PersonByType(AddressByType addressByType) {
        this.addressByType = addressByType;
    }

    public PersonByType(AddressByType addressByType, EducationByType educationByType) {
        this.addressByType = addressByType;
        this.educationByType = educationByType;
    }
}
