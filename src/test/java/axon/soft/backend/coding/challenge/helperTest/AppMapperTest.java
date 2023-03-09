package axon.soft.backend.coding.challenge.helperTest;

import axon.soft.backend.coding.challenge.domain.entity.PersonEntity;
import axon.soft.backend.coding.challenge.domain.entity.RoomEntity;
import axon.soft.backend.coding.challenge.helper.AppMapper;
import axon.soft.backend.coding.challenge.model.PersonDTO;
import axon.soft.backend.coding.challenge.model.RoomDTO;
import org.junit.Test;


import static axon.soft.backend.coding.challenge.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppMapperTest {

    private AppMapper appMapper = new AppMapper();
    @Test
    public void mapToRoomDTO_Test(){
        RoomDTO response = appMapper.mapToRoomDTO(getRoomEntity());
        assertEquals(response.getPeople().get(0).getLastname(), "Fischer");
        assertEquals(response.getPeople().get(0).getLdapuser(),("dfischer"));
        assertEquals(response.getPeople().get(0).getTitle(),("Dr."));
        assertEquals(response.getPeople().get(0).getNameAddition(), "von");
        assertEquals(response.getPeople().get(0).getFirstname(), "Dennis");
    }

    @Test
    public void mapToPersonDTO_Test(){
        PersonDTO response = appMapper.mapToPersonDTO(getPersonEntity().get(0));
        assertEquals(response.getLastname(), "Fischer");
        assertEquals(response.getLdapuser(),("dfischer"));
        assertEquals(response.getTitle(),("Dr."));
        assertEquals(response.getNameAddition(), "von");
        assertEquals(response.getFirstname(), "Dennis");
    }

    @Test
    public void mapToRoomEntity_Test(){
        RoomEntity response = appMapper.mapToRoomEntity(getRoomDTO());
        assertEquals(response.getPersonEntities().get(0).getLastName(), "Fischer");
        assertEquals(response.getPersonEntities().get(0).getLdapuser(),("dfischer"));
        assertEquals(response.getPersonEntities().get(0).getTitle(),("Dr."));
        assertEquals(response.getPersonEntities().get(0).getNameAddition(), "von");
        assertEquals(response.getPersonEntities().get(0).getFirstName(), "Dennis");
    }

    @Test
    public void mapToPersonEntity_Test(){
        PersonEntity response = appMapper.mapToPersonEntity(getPersonDTO().get(0));
        assertEquals(response.getLastName(), "Fischer");
        assertEquals(response.getLdapuser(),("dfischer"));
        assertEquals(response.getTitle(),("Dr."));
        assertEquals(response.getNameAddition(), "von");
        assertEquals(response.getFirstName(), "Dennis");
    }
}
