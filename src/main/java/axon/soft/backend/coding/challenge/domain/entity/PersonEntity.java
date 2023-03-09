package axon.soft.backend.coding.challenge.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "person", schema = "backend_coding_challenge")
@Builder
public class PersonEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private Integer idPerson;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "title")
    private String title;
    @Column(name = "name_addition")
    private String nameAddition;
    @Column(name = "ldap_user")
    private String ldapuser;
    @ManyToOne
    @JoinColumn(name = "id_room")
    private RoomEntity roomEntity;

    public PersonEntity(Integer idPerson, String firstName, String lastName, String title, String nameAddition, String ldapuser, RoomEntity roomEntity) {
        this.idPerson = idPerson;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.nameAddition = nameAddition;
        this.ldapuser = ldapuser;
        this.roomEntity = roomEntity;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", nameAddition='" + nameAddition + '\'' +
                ", ldapuser='" + ldapuser + '\'' +
                '}';
    }
}
