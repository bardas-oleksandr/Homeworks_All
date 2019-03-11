package ua.levelup.autowired;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.levelup.fieldstypes.AddressDefault;
import ua.levelup.fieldstypes.EducationDefault;

@Getter
@Setter
@NoArgsConstructor
public class PersonDefault {
    private AddressDefault addressDefault;
    private EducationDefault educationDefault;

    public PersonDefault(AddressDefault addressDefault) {
        this.addressDefault = addressDefault;
    }

    public PersonDefault(AddressDefault addressDefault, EducationDefault educationDefault) {
        this.addressDefault = addressDefault;
        this.educationDefault = educationDefault;
    }
}
