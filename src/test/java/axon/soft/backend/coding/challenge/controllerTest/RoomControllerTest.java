package axon.soft.backend.coding.challenge.controllerTest;

import axon.soft.backend.coding.challenge.Application;
import axon.soft.backend.coding.challenge.exceptions.ErrorObject;
import axon.soft.backend.coding.challenge.exceptions.NotFoundException;
import axon.soft.backend.coding.challenge.helper.ErrorType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {Application.class}
)
@AutoConfigureMockMvc
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    private static final String GET_API_PATH_WITH_PARAM = "/api/room/{number}";
    private static final String GET_API_PATH_WITHOUT_PARAM = "/api/room";

    @Test
    public void getRoomDistribution_Test_Not_Found() throws Exception {

        mockMvc
            .perform(get(GET_API_PATH_WITH_PARAM, "8888"))
            .andExpect(status().is4xxClientError())
            .andExpect(
                result -> {
                    assertTrue(result.getResolvedException() instanceof NotFoundException);
                    ErrorObject actualError =
                       objectMapper.readValue(result.getResponse().getContentAsString(), ErrorObject.class);

                ErrorObject expectedError =
                    ErrorObject.builder()
                        .errorCode(ErrorType.ERROR_CODE_5.getCode())
                        .message(ErrorType.ERROR_CODE_5.getMessage())
                        .build();
                    Assertions.assertThat(actualError)
                        .usingRecursiveComparison()
                        .isEqualTo(expectedError);
                })
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void getRoomDistribution_Test_Ok() throws Exception {
        mockMvc
            .perform(get(GET_API_PATH_WITH_PARAM, "1111"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void getRoomDistribution_Test_For_GetAll_Room() throws Exception {
        mockMvc
            .perform(get(GET_API_PATH_WITHOUT_PARAM, ""))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

    }
}
