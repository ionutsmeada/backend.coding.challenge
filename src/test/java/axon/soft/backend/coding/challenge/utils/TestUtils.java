package axon.soft.backend.coding.challenge.utils;

import axon.soft.backend.coding.challenge.domain.entity.PersonEntity;
import axon.soft.backend.coding.challenge.domain.entity.RoomEntity;
import axon.soft.backend.coding.challenge.model.PersonDTO;
import axon.soft.backend.coding.challenge.model.RoomDTO;

import java.util.List;

public class TestUtils {

    public static RoomDTO getRoomDTO() {
        return RoomDTO.builder()
                .roomNumber("1111")
                .people(getPersonDTO())
                .build();
    }

    public static  List<PersonDTO> getPersonDTO(){
        return List.of(PersonDTO.builder()
                .firstname("Dennis")
                .lastname("Fischer")
                .title("Dr.")
                .nameAddition("von")
                .ldapuser("dfischer")
                .build());
    }

    public static RoomEntity getRoomEntity() {
        return RoomEntity.builder()
                .roomNumber("1111")
                .personEntities(getPersonEntity())
                .build();
    }

    public static List<PersonEntity> getPersonEntity(){
        return List.of(PersonEntity.builder()
                .firstName("Dennis")
                .lastName("Fischer")
                .title("Dr.")
                .nameAddition("von")
                .ldapuser("dfischer")
                .build());
    }

    public static List<RoomEntity> getListRoomEntity() {
        return List.of(RoomEntity.builder()
                .roomNumber("1111")
                .personEntities(getListPersonEntity())
                .build());
    }

    public static List<PersonEntity> getListPersonEntity() {
        return List.of(PersonEntity.builder()
                .firstName("Dennis")
                .lastName("Fischer")
                .title("Dr.")
                .nameAddition("von")
                .ldapuser("dfischer")
                .build());
    }

    public static List<RoomDTO> getListRoomDTO() {
        return List.of(RoomDTO.builder()
                .roomNumber("1111")
                .people(getListPersonDTO()).build());
    }

    public static List<PersonDTO> getListPersonDTO() {
        return List.of(PersonDTO.builder()
                .firstname("Dennis")
                .lastname("Fischer")
                .title("Dr.")
                .nameAddition("von")
                .ldapuser("dfischer")
                .build());
    }


}
