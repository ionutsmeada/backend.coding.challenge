package axon.soft.backend.coding.challenge.serviceTest;

import axon.soft.backend.coding.challenge.domain.repository.RoomRepository;
import axon.soft.backend.coding.challenge.exceptions.NotFoundException;
import axon.soft.backend.coding.challenge.helper.AppMapper;
import axon.soft.backend.coding.challenge.model.RoomDTO;
import axon.soft.backend.coding.challenge.service.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static axon.soft.backend.coding.challenge.utils.TestUtils.getListPersonEntity;
import static axon.soft.backend.coding.challenge.utils.TestUtils.getListRoomEntity;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private AppMapper appMapper;
    @Test
    public void getRoomListTest_Ok(){

        when(roomRepository.findByRoomNumber(any())).thenReturn(getListRoomEntity());

        //Mock private method from Room Service before call the method tested
        doCallRealMethod().when(appMapper).mapToRoomDTO(eq(getListRoomEntity().get(0)));
        doCallRealMethod().when(appMapper).mapToPersonDTO(eq(getListPersonEntity().get(0)));

        List<RoomDTO> response = roomService.getRoomList("1111");
        assertEquals(response.get(0).getPeople().get(0).getLastname(), "Fischer");
        assertEquals(response.get(0).getPeople().get(0).getLdapuser(),("dfischer"));
        assertEquals(response.get(0).getPeople().get(0).getTitle(),("Dr."));
        assertEquals(response.get(0).getPeople().get(0).getNameAddition(), "von");
        assertEquals(response.get(0).getPeople().get(0).getTitle(),("Dr."));
        assertEquals(response.get(0).getPeople().get(0).getFirstname(), "Dennis");
    }

    @Test
    public void getRoomListTest_Exception(){
        when(roomRepository.findByRoomNumber(any())).thenReturn(List.of());
        assertThatThrownBy(() -> roomService.getRoomList("1111"))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    public void getAllRoomListTest_Ok(){

        when(roomRepository.findAll()).thenReturn(getListRoomEntity());

        //Mock private method from Room Service before call the method tested
        doCallRealMethod().when(appMapper).mapToRoomDTO(eq(getListRoomEntity().get(0)));
        doCallRealMethod().when(appMapper).mapToPersonDTO(eq(getListPersonEntity().get(0)));

        List<RoomDTO> response = roomService.getAllRoomsList();
        assertEquals(response.get(0).getPeople().get(0).getLastname(), "Fischer");
        assertEquals(response.get(0).getPeople().get(0).getLdapuser(),("dfischer"));
        assertEquals(response.get(0).getPeople().get(0).getTitle(),("Dr."));
        assertEquals(response.get(0).getPeople().get(0).getNameAddition(), "von");
        assertEquals(response.get(0).getPeople().get(0).getFirstname(), "Dennis");
    }
}
