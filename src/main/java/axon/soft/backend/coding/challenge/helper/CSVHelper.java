package axon.soft.backend.coding.challenge.helper;

import axon.soft.backend.coding.challenge.exceptions.BadRequestException;
import axon.soft.backend.coding.challenge.exceptions.ErrorObject;
import axon.soft.backend.coding.challenge.model.PersonDTO;
import axon.soft.backend.coding.challenge.model.RoomDTO;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CSVHelper {

    Set<String> uniquePersonLdapuser = new HashSet<>();
    public List<RoomDTO> csvToJson(BufferedReader file) throws IOException, CsvException {
        CSVReader csvReader = new CSVReader(file);
        List<String[]> rows = csvReader.readAll();
        uniquePersonLdapuser.clear();
        List<RoomDTO> jsonList = new ArrayList<>();
        Set<String> uniqueRoomNumbers = new HashSet<>();

        for (String[] row : rows) {
            String roomNumber = row[0];

            if (uniqueRoomNumbers.contains(roomNumber)) {
                throw new BadRequestException(ErrorObject.builder()
                        .message(ErrorType.ERROR_CODE_2.getMessage())
                        .errorCode(ErrorType.ERROR_CODE_2.getCode())
                        .build());
            } else {
                uniqueRoomNumbers.add(roomNumber);
            }

            List<PersonDTO> peopleList =new ArrayList<>();
            for(int i = 1; i < row.length; i++) {
                String name = row[i];
                if(!name.isEmpty()) {
                    peopleList.add(splitPersonInfo(name));
                }
            }
            jsonList.add(new RoomDTO(roomNumber, peopleList));
        }
        return jsonList;
    }

    public PersonDTO splitPersonInfo(String peopleInfo) {
        String[] nameParts = peopleInfo.split(" ");

        if(nameParts.length <= 1){
            throw new BadRequestException(ErrorObject.builder()
                    .errorCode(ErrorType.ERROR_CODE_4.getCode())
                    .message(ErrorType.ERROR_CODE_4.getMessage())
                    .build());
        }

        PersonDTO personDTO = new PersonDTO();
        personDTO.setLdapuser(findLdapuser(peopleInfo));
        personDTO.setLastname(nameParts[nameParts.length - 2]);

        List<String> firstnameParts = Arrays.stream(nameParts)
                .filter(part -> !List.of("Dr.", "van", "von", "de").contains(part))
                .filter(part -> !part.equals(personDTO.getLastname()))
                .filter(part -> !part.equals("(" + personDTO.getLdapuser() + ")"))
                .collect(Collectors.toList());

        personDTO.setFirstname(String.join(" ", firstnameParts));

        for (String namePart : nameParts) {
            if (namePart.equalsIgnoreCase("Dr.")) {
                personDTO.setTitle(namePart);
            }
            if (List.of("van", "von", "de").contains(namePart.toLowerCase())) {
                personDTO.setNameAddition(namePart);
            }
        }
        if (personDTO.getTitle() == null){
            personDTO.setTitle("");
        }
        if (personDTO.getNameAddition() == null){
            personDTO.setNameAddition("");
        }
        return personDTO;
    }

    private String findLdapuser(String peopleInfo){
        String ldapuser = null;
        Pattern pattern = Pattern.compile(("\\((.*?)\\)"));
        Matcher matcher = pattern.matcher(peopleInfo);
        if(matcher.find()) {
            ldapuser = matcher.group(1);
            if (uniquePersonLdapuser.contains(ldapuser)) {
                throw new BadRequestException(ErrorObject.builder()
                        .message(ErrorType.ERROR_CODE_3.getMessage())
                        .errorCode(ErrorType.ERROR_CODE_3.getCode())
                        .build());
            } else {
                uniquePersonLdapuser.add(ldapuser);
            }
        }
        return ldapuser != null ? ldapuser : "";
    }
}