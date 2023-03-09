package axon.soft.backend.coding.challenge.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
public class PersonDTO {
    private String firstname;
    private String lastname;
    private String title;
    private String nameAddition;
    private String ldapuser;

    public PersonDTO(String firstname, String lastname, String title, String nameAddition, String ldapuser) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.title = title;
        this.nameAddition = nameAddition;
        this.ldapuser = ldapuser;
    }

    @Override
    public String toString() {
        return "PeopleDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", title='" + title + '\'' +
                ", nameAddition='" + nameAddition + '\'' +
                ", ldapuser='" + ldapuser + '\'' +
                '}';
    }
}
