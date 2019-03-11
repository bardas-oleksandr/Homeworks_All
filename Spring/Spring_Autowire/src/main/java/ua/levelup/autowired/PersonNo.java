package ua.levelup.autowired;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.levelup.fieldstypes.AddressNo;
import ua.levelup.fieldstypes.EducationNo;

@Getter
@Setter
@NoArgsConstructor
public class PersonNo {
    private AddressNo addressNo;
    private EducationNo educationNo;

    public PersonNo(AddressNo addressNo) {
        this.addressNo = addressNo;
    }

    public PersonNo(AddressNo addressNo, EducationNo educationNo) {
        this.addressNo = addressNo;
        this.educationNo = educationNo;
    }
}
