package ua.levelup.autowired;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.levelup.fieldstypes.AddressConstructor;
import ua.levelup.fieldstypes.EducationConstructor;

@Getter
@Setter
@NoArgsConstructor
public class PersonConstructor {
    private AddressConstructor addressConstructor;
    private EducationConstructor educationConstructor;

    public PersonConstructor(AddressConstructor addressConstructor) {
        this.addressConstructor = addressConstructor;
    }

    public PersonConstructor(AddressConstructor addressConstructor, EducationConstructor educationConstructor) {
        this.addressConstructor = addressConstructor;
        this.educationConstructor = educationConstructor;
    }
}
