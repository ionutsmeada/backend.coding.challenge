package axon.soft.backend.coding.challenge.helperTest;

import axon.soft.backend.coding.challenge.helper.CSVHelper;
import axon.soft.backend.coding.challenge.model.PersonDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVHelperTest {

    private CSVHelper csvHelper = new CSVHelper();

    @Test
    public void testSplitString() {
        String peopleInfo = "Dr. John von Doe (johndoe)";

        PersonDTO expectedPersonDTO = new PersonDTO();
        expectedPersonDTO.setLdapuser("johndoe");
        expectedPersonDTO.setTitle("Dr.");
        expectedPersonDTO.setLastname("Doe");
        expectedPersonDTO.setNameAddition("von");
        expectedPersonDTO.setFirstname("John");

        PersonDTO actualPersonDTO = csvHelper.splitPersonInfo(peopleInfo);

        assertEquals(expectedPersonDTO.getLdapuser(), actualPersonDTO.getLdapuser());
        assertEquals(expectedPersonDTO.getTitle(), actualPersonDTO.getTitle());
        assertEquals(expectedPersonDTO.getLastname(), actualPersonDTO.getLastname());
        assertEquals(expectedPersonDTO.getNameAddition(), actualPersonDTO.getNameAddition());
        assertEquals(expectedPersonDTO.getFirstname(), actualPersonDTO.getFirstname());
    }
}
