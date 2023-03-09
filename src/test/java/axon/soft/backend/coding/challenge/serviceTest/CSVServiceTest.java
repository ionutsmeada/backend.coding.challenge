package axon.soft.backend.coding.challenge.serviceTest;

import axon.soft.backend.coding.challenge.domain.entity.PersonEntity;
import axon.soft.backend.coding.challenge.domain.repository.PeopleRepository;
import axon.soft.backend.coding.challenge.domain.repository.RoomRepository;
import axon.soft.backend.coding.challenge.helper.AppMapper;
import axon.soft.backend.coding.challenge.helper.CSVHelper;
import axon.soft.backend.coding.challenge.model.RoomDTO;
import axon.soft.backend.coding.challenge.service.CSVService;
import com.opencsv.exceptions.CsvException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static axon.soft.backend.coding.challenge.utils.TestUtils.getListPersonDTO;
import static axon.soft.backend.coding.challenge.utils.TestUtils.getListRoomDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CSVServiceTest {

    @InjectMocks
    private CSVService csvService;
    @Mock
    private CSVHelper csvHelper;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private PeopleRepository peopleRepository;
    @Mock
    private AppMapper appMapper;
    @Mock
    private BufferedReader mockReader;
    @Test
    public void readRoomSittingFromCSVTest() throws IOException, CsvException {
        when(mockReader.readLine())
                .thenReturn("1111,Dennis Fischer (dfischer)")
                .thenReturn(null);

        when(csvHelper.csvToJson(any())).thenReturn(getListRoomDTO());
        doNothing().when(peopleRepository).deleteAll();
        doNothing().when(roomRepository).deleteAll();
        doCallRealMethod().when(appMapper).mapToRoomEntity(eq(getListRoomDTO().get(0)));
        doCallRealMethod().when(appMapper).mapToPersonEntity(eq(getListPersonDTO().get(0)));
        when(roomRepository.save(any())).thenReturn(any());

        List<RoomDTO> response = csvService.readRoomSittingFromCSV(mockReader);
        assertEquals(response.get(0).getRoomNumber(), "1111");
        assertEquals(response.get(0).getPeople().get(0).getLastname(), "Fischer");
        assertEquals(response.get(0).getPeople().get(0).getLdapuser(), "dfischer");
        assertEquals(response.get(0).getPeople().get(0).getTitle(),("Dr."));
        assertEquals(response.get(0).getPeople().get(0).getNameAddition(), "von");
        assertEquals(response.get(0).getPeople().get(0).getFirstname(), "Dennis");
    }

    public PersonEntity getPersonEntity(){
        return PersonEntity.builder().build();
    }
}