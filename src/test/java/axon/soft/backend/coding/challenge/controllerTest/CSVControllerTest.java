package axon.soft.backend.coding.challenge.controllerTest;

import axon.soft.backend.coding.challenge.Application;
import axon.soft.backend.coding.challenge.service.CSVService;
import axon.soft.backend.coding.challenge.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.BufferedReader;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {Application.class}
)
@AutoConfigureMockMvc
public class CSVControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CSVService csvService;

    @Test
    public void addRoomDistribution_test() throws Exception {

        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", "test data".getBytes());
        when(csvService.readRoomSittingFromCSV(ArgumentMatchers.any())).thenReturn(TestUtils.getListRoomDTO());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/import")
                .file(file))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
