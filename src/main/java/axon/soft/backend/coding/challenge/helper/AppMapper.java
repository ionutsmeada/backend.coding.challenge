package axon.soft.backend.coding.challenge.helper;

import axon.soft.backend.coding.challenge.domain.entity.PersonEntity;
import axon.soft.backend.coding.challenge.domain.entity.RoomEntity;
import axon.soft.backend.coding.challenge.model.PersonDTO;
import axon.soft.backend.coding.challenge.model.RoomDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppMapper {


    // ----------------- Map to DTO ----------------- //
    public RoomDTO mapToRoomDTO(RoomEntity entity){
        List<PersonDTO> people = new ArrayList<>();
        for (PersonEntity personEntity : entity.getPersonEntities()) {
            people.add(mapToPersonDTO(personEntity));
        }
        return RoomDTO.builder().roomNumber(entity.getRoomNumber())
                .people(people)
                .build();
    }

    public PersonDTO mapToPersonDTO(PersonEntity entity){
        return PersonDTO.builder().firstname(entity.getFirstName())
                .lastname(entity.getLastName())
                .title(entity.getTitle())
                .nameAddition(entity.getNameAddition())
                .ldapuser(entity.getLdapuser())
                .build();
    }

    // ----------------- Map to Entity ----------------- //

    public RoomEntity mapToRoomEntity(RoomDTO roomDTO) {
        List<PersonEntity> personEntities = new ArrayList<>();
        for (PersonDTO personDTO : roomDTO.getPeople()) {
            personEntities.add(mapToPersonEntity(personDTO));
        }
        return RoomEntity.builder()
                .roomNumber(roomDTO.getRoomNumber())
                .personEntities(personEntities)
                .build();
    }
    public PersonEntity mapToPersonEntity(PersonDTO personDTO){
        return PersonEntity.builder().firstName(personDTO.getFirstname())
                .lastName(personDTO.getLastname())
                .title(personDTO.getTitle())
                .nameAddition(personDTO.getNameAddition())
                .ldapuser(personDTO.getLdapuser())
                .build();
    }
}
