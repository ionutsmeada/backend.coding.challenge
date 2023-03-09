package axon.soft.backend.coding.challenge.controller;

import axon.soft.backend.coding.challenge.model.RoomDTO;
import axon.soft.backend.coding.challenge.service.RoomService;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class RoomController {

    @Autowired
    private RoomService roomService;
    @GetMapping(value = { "/api/room/{number}" , "/api/room"} , produces = "application/json")
    public ResponseEntity<List<RoomDTO>> getRoomDistribution(
                @PathVariable(value = "number" ,required = false) @Size(min = 4, max = 4) String number){

        List<RoomDTO> response;
        if (StringUtils.isEmpty(number)) {
            response = roomService.getAllRoomsList();
        } else {
            response = roomService.getRoomList(number);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}