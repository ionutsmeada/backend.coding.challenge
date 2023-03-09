package axon.soft.backend.coding.challenge.controller;

import axon.soft.backend.coding.challenge.exceptions.BadRequestException;
import axon.soft.backend.coding.challenge.exceptions.ErrorObject;
import axon.soft.backend.coding.challenge.helper.ErrorType;
import axon.soft.backend.coding.challenge.model.RoomDTO;
import axon.soft.backend.coding.challenge.service.CSVService;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@RestController
public class CSVController {

    private CSVService csvService;
    @Autowired
    public CSVController(CSVService csvService){
        this.csvService = csvService;
    }
    @PostMapping(value = "/api/import")
    public ResponseEntity<List<RoomDTO>> getRoomSitting(@RequestParam("file") MultipartFile multipartFile) throws IOException, CsvException {
        List<RoomDTO> response;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            response = csvService.readRoomSittingFromCSV(reader);
        } catch (IIOException e) {
            throw new BadRequestException(ErrorObject.builder()
                    .errorCode(ErrorType.ERROR_CODE_4.getCode())
                    .message(ErrorType.ERROR_CODE_4.getMessage())
                    .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
